package com.xuzimian.globaldemo.common.xml.jackson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Collection;

@JsonSerialize(using = com.xuzimian.globaldemo.common.xml.jackson.CustomDateSerializer.class)
public class PagedResponse<T> {
    private long totalElements;
    private int totalPages;
    private int pageSize;
    private int pageNumber;
    private int numberOfElements;


    //@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,include = JsonTypeInfo.As.PROPERTY,property = "@Clazz")
    private Collection<T> content;

    public PagedResponse(long totalElements, int pageSize, int pageNumber, Collection<T> content) {
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / (double) pageSize);
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.numberOfElements = content.size();
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Collection<T> getContent() {
        return content;
    }

    public void setContent(Collection<T> content) {
        this.content = content;
    }
}