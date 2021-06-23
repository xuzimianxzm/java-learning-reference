package com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.commit;

import lombok.Data;

import java.util.List;

@Data
public class ValuesItem {
    private Summary summary;
    private String date;
    private Rendered rendered;
    private Author author;
    private Links links;
    private Repository repository;
    private String message;
    private String type;
    private String hash;
    private List<ParentsItem> parents;
}