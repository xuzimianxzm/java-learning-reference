package com.xuzimian.java.jenkins.plugin.scms.bitbucket.response;

import com.google.common.collect.Lists;
import com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.commit.ValuesItem;
import lombok.Data;

import java.util.List;

@Data
public class CommitResponse {
    private String next;
    private List<ValuesItem> values = Lists.newArrayListWithCapacity(0);
    private int pagelen;
}