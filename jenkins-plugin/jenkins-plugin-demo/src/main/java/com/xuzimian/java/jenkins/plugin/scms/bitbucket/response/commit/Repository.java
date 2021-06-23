package com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.commit;

import lombok.Data;

@Data
public class Repository {
    private String fullName;
    private String name;
    private Links links;
    private String type;
    private String uuid;
}