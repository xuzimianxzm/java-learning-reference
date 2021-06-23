package com.xuzimian.java.jenkins.plugin.parameter.spike;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.ParameterValue;
import hudson.model.Run;
import hudson.util.VariableResolver;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

import java.util.Locale;

public class SpikeSelectorValue extends ParameterValue {
    @Exported(visibility = 1)
    @Restricted(NoExternalUse.class)
    public String value;

    @DataBoundConstructor
    public SpikeSelectorValue(String name, String value) {
        super(name, value);
    }

    @Override
    public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        env.put(name, value);
        env.put(name.toUpperCase(Locale.ENGLISH), value); // backward compatibility pre 1.345
    }

    @Override
    public VariableResolver<String> createVariableResolver(AbstractBuild<?, ?> build) {
        return new VariableResolver<String>() {
            public String resolve(String name) {
                return SpikeSelectorValue.this.name.equals(name) ? value : null;
            }
        };
    }

    @Override
    public String getValue() {
        return value;
    }

    /**
     * Trimming for value
     *
     * @since 2.90
     */
    public void doTrim() {
        if (value != null) {
            value = value.trim();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        SpikeSelectorValue other = (SpikeSelectorValue) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "(GitCommitSelectorValue) " + getName() + "='" + value + "'";
    }

    @Override
    public String getShortDescription() {
        return name + '=' + value;
    }
}
