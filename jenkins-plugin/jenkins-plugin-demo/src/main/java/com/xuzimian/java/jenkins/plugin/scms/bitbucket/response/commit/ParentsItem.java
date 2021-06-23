package com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.commit;

import lombok.Data;

@Data
public class ParentsItem {
    private Links links;
    private String type;
    private String hash;
}