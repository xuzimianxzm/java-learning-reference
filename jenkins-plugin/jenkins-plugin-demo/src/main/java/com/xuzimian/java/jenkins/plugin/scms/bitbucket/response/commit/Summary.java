package com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.commit;

import lombok.Data;

@Data
public class Summary {
    private String markup;
    private String raw;
    private String html;
    private String type;
}