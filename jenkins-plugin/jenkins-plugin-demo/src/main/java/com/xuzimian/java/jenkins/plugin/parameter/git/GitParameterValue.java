package com.xuzimian.java.jenkins.plugin.parameter.git;

import hudson.model.StringParameterValue;
import org.kohsuke.stapler.DataBoundConstructor;

public class GitParameterValue extends StringParameterValue {
    private static final long serialVersionUID = -8244244942726975701L;

    @DataBoundConstructor
    public GitParameterValue(String name, String value) {
        super(name, value);
    }
}
