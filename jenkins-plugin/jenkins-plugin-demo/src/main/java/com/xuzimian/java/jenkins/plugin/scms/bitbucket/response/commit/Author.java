package com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.commit;

import lombok.Data;

@Data
public class Author {
    private String raw;
    private String type;
    private User user;
}