package com.xuzimian.java.jenkins.plugin.parameter.spike;

import hudson.Extension;
import hudson.Util;
import hudson.model.ParameterDefinition;
import hudson.model.ParameterValue;
import hudson.model.SimpleParameterDefinition;
import hudson.model.StringParameterValue;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.DoNotUse;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpikeSelectorParameterDefinition extends SimpleParameterDefinition {

    private final boolean trim;
    private String defaultValue;
    private List<String> options = new ArrayList<>();

    @DataBoundConstructor
    public SpikeSelectorParameterDefinition(String name, String defaultValue, String description, boolean trim) {
        super(name, description);
        this.defaultValue = defaultValue;
        this.trim = trim;
        initOptionData();
    }

    public SpikeSelectorParameterDefinition(String name, String defaultValue, String description) {
        this(name, defaultValue, description, false);
    }


    public SpikeSelectorParameterDefinition(String name, String defaultValue) {
        this(name, defaultValue, null, false);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getOptions() {
        return options;
    }

    private void initOptionData() {
        options.add("abc-1");
        options.add("bbc-2");
        options.add("2bc-3");
        options.add("4bc-4");
    }

    @Restricted(DoNotUse.class) // Jelly
    public String getDefaultValue4Build() {
        if (isTrim()) {
            return Util.fixNull(defaultValue).trim();
        }
        return defaultValue;
    }

    /**
     * @return trim - {@code true}, if trim options has been selected, else return {@code false}.
     * Trimming will happen when creating {@link StringParameterValue}s,
     * the value in the config will not be changed.
     * @since 2.90
     */
    public boolean isTrim() {
        return trim;
    }

    @Override
    public StringParameterValue getDefaultParameterValue() {
        StringParameterValue value = new StringParameterValue(getName(), defaultValue, getDescription());
        if (isTrim()) {
            value.doTrim();
        }
        return value;
    }

    @Override
    public ParameterDefinition copyWithDefaultValue(ParameterValue defaultValue) {
        if (defaultValue instanceof SpikeSelectorValue) {
            SpikeSelectorValue value = (SpikeSelectorValue) defaultValue;
            return new SpikeSelectorParameterDefinition(getName(), value.value, getDescription());
        } else {
            return this;
        }
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        SpikeSelectorValue value = req.bindJSON(SpikeSelectorValue.class, jo);
        if (isTrim() && value != null) {
            value.doTrim();
            value.setDescription(getDescription());
        }
        return value;
    }

    @Override
    public ParameterValue createValue(String str) {
        SpikeSelectorValue value = new SpikeSelectorValue(getName(), str);
        if (isTrim()) {
            value.doTrim();
        }
        return value;
    }

    @Override
    public int hashCode() {
        if (SpikeSelectorParameterDefinition.class != getClass()) {
            return super.hashCode();
        }
        return Objects.hash(getName(), getDescription(), defaultValue, trim);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SpikeSelectorParameterDefinition other = (SpikeSelectorParameterDefinition) obj;
        if (!Objects.equals(getName(), other.getName()))
            return false;
        if (!Objects.equals(getDescription(), other.getDescription()))
            return false;
        if (!Objects.equals(defaultValue, other.defaultValue))
            return false;
        return trim == other.trim;
    }

    @Extension
    @Symbol({"spikeSelector", "spikeSelector"})
    public static class DescriptorImpl extends ParameterDescriptor {
        @Override
        public String getDisplayName() {
            return "spikeSelector";
        }
    }
}
