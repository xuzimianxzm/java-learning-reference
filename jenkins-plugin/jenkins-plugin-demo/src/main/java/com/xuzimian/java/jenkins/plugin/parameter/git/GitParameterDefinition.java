package com.xuzimian.java.jenkins.plugin.parameter.git;

import com.xuzimian.java.jenkins.plugin.Messages;
import com.xuzimian.java.jenkins.plugin.parameter.git.model.ItemsErrorModel;
import com.xuzimian.java.jenkins.plugin.scms.bitbucket.BitbucketService;
import hudson.Extension;
import hudson.cli.CLICommand;
import hudson.model.ParameterDefinition;
import hudson.model.ParameterValue;
import hudson.model.ParametersDefinitionProperty;
import hudson.util.FormValidation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.xuzimian.java.jenkins.plugin.Messages.GitParameterDefinition_checkConfiguration;
import static com.xuzimian.java.jenkins.plugin.Messages.GitParameterDefinition_error;
import static com.xuzimian.java.jenkins.plugin.Messages.GitParameterDefinition_lookAtLog;
import static hudson.util.FormValidation.error;
import static hudson.util.FormValidation.ok;
import static hudson.util.FormValidation.warning;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.lang.ArrayUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

@Getter
@Setter
@Slf4j
public class GitParameterDefinition extends ParameterDefinition {
    private static final long serialVersionUID = 9157832967140868122L;

    private ItemsErrorModel model;

    private final UUID uuid;
    private String branchFilter;
    private String useRepository;

    @DataBoundConstructor
    public GitParameterDefinition(String name,
                                  String description,
                                  String branchFilter,
                                  String useRepository) {
        super(name, description);
        this.uuid = UUID.randomUUID();
        this.branchFilter = isNotEmpty(branchFilter) ? branchFilter : "master";
        this.useRepository = useRepository;
        model = ItemsErrorModel.EMPTY;
    }

    @Override
    public ParameterValue createValue(StaplerRequest request) {
        String[] value = request.getParameterValues(getName());
        if (isEmpty(value) || isBlank(value[0])) {
            return getDefaultParameterValue();
        }
        return new GitParameterValue(getName(), value[0]);
    }

    @Override
    public ParameterValue createValue(StaplerRequest request, JSONObject jsonObject) {
        String value = jsonObject.getString("value");
        if (isNotEmpty(value)) {
            return new GitParameterValue(jsonObject.getString("name"), value);
        }

        return getDefaultParameterValue();
    }

    @Override
    public ParameterValue createValue(CLICommand command, String value) throws IOException, InterruptedException {
        if (isNotEmpty(value)) {
            return new GitParameterValue(getName(), value);
        }

        return getDefaultParameterValue();
    }

    @Override
    public ParameterValue getDefaultParameterValue() {
        if (model == null || isEmpty(model.values)) {
            return new GitParameterValue(getName(), null);
        }

        return model.values.stream()
                .findFirst()
                .map(value -> new GitParameterValue(getName(), value.value))
                .orElse(null);
    }

    public String getDivUUID() {
        return getName().replaceAll("\\W", "_") + "-" + uuid;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Symbol("gitParameter")
    @Extension
    public static class DescriptorImpl extends ParameterDescriptor {
        private final BitbucketService bitbucketService = new BitbucketService();

        public DescriptorImpl() {
            load();
        }

        @Override
        public String getDisplayName() {
            return Messages.GitParameterDefinition_DisplayName();
        }

        public ItemsErrorModel doFillValueItems(@AncestorInPath WorkflowJob job, @QueryParameter String param) {
            ParametersDefinitionProperty prop = job.getProperty(ParametersDefinitionProperty.class);
            if (prop != null) {
                ParameterDefinition parameterDefinition = prop.getParameterDefinition(param);
                if (parameterDefinition instanceof GitParameterDefinition) {
                    GitParameterDefinition gitParameterDefinition = (GitParameterDefinition) parameterDefinition;
                    ItemsErrorModel model = generateGitCommitSelectorContent(job, gitParameterDefinition.branchFilter, gitParameterDefinition.getUseRepository());
                    gitParameterDefinition.setModel(model);
                    return model;
                }
            }
            return ItemsErrorModel.EMPTY;
        }

        public FormValidation doCheckDefaultValue(@QueryParameter String defaultValue) {
            return isBlank(defaultValue) ? warning(Messages.GitParameterDefinition_requiredDefaultValue()) : ok();
        }

        public FormValidation doCheckBranchFilter(@QueryParameter String value) {
            String errorMessage = Messages.GitParameterDefinition_invalidBranchPattern(value);
            return validationRegularExpression(value, errorMessage);
        }

        public FormValidation doCheckUseRepository(@QueryParameter String value) {
            String errorMessage = Messages.GitParameterDefinition_invalidUseRepositoryPattern(value);
            return validationRegularExpression(value, errorMessage);
        }

        private FormValidation validationRegularExpression(String value, String errorMessage) {
            try {
                Pattern.compile(value); // Validate we've got a valid regex.
            } catch (PatternSyntaxException e) {
                log.error(errorMessage, e);
                return error(errorMessage);
            }
            return ok();
        }

        private ItemsErrorModel generateGitCommitSelectorContent(WorkflowJob job, String branchFilter, String repositoryName) {
            try {
                return bitbucketService.getCommits("xuzimian", branchFilter, repositoryName, job);
            } catch (IOException | InterruptedException e) {
                log.error(job.getName() + " " + Messages.GitParameterDefinition_unexpectedError(), e);
                return ItemsErrorModel.createByErrors(GitParameterDefinition_error(), e.getMessage(), GitParameterDefinition_lookAtLog(), GitParameterDefinition_checkConfiguration());
            }
        }
    }
}
