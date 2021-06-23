package com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.commit;

import lombok.Data;

@Data
public class User {
    private String accountId;
    private String nickname;
    private Links links;
    private String displayName;
    private String type;
    private String uuid;
}