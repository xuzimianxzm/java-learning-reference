package com.xuzimian.java.jenkins.plugin.scms.bitbucket;

import com.cloudbees.plugins.credentials.CredentialsProvider;
import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;
import com.xuzimian.java.jenkins.plugin.parameter.git.model.ItemsErrorModel;
import com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.CommitResponse;
import hudson.plugins.git.GitSCM;
import hudson.plugins.git.UserRemoteConfig;
import hudson.scm.SCM;
import lombok.NoArgsConstructor;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;

import java.io.IOException;
import java.util.Collection;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

@NoArgsConstructor
public class BitbucketService {

    private final BitbucketProvider provider = new BitbucketProvider();

    public ItemsErrorModel getCommits(String owner, String branchName, String repositoryName, WorkflowJob job) throws IOException, InterruptedException {
        String credentialsId = getCredentialsId(job.getSCMs());
        StandardUsernamePasswordCredentials credentials = findCredentialsByCredentialsId(credentialsId, job);

        CommitResponse response = provider.searchCommits(owner, repositoryName, branchName, credentials);
        return toModel(response);
    }

    private StandardUsernamePasswordCredentials findCredentialsByCredentialsId(String credentialsId, WorkflowJob job) {
        return CredentialsProvider.findCredentialById(credentialsId,
                StandardUsernamePasswordCredentials.class, job.getFirstBuild());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private String getCredentialsId(Collection<? extends SCM> scms) {
        if (isEmpty(scms)) {
            return null;
        }

        SCM scm = scms.stream()
                .findFirst()
                .get();

        return scm instanceof GitSCM ? ((GitSCM) scm).getUserRemoteConfigs()
                .stream()
                .findFirst()
                .map(UserRemoteConfig::getCredentialsId)
                .orElse(null) : null;
    }

    private static ItemsErrorModel toModel(CommitResponse commitResponse) {
        ItemsErrorModel model = new ItemsErrorModel();
        commitResponse.getValues()
                .forEach(valuesItem -> model.add(valuesItem.getMessage(), valuesItem.getHash()));

        return model;
    }
}
