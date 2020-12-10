package ru.krisnovitskaya.kris.market.dto;


import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageDto<E> {

    private List<E> content;

    private long totalElements;

    private int totalPages;


    public PageDto() {
    }

    public PageDto(List<E> content, Pageable pageable, long total) {
        this.content = content;
        this.totalElements = total;
        this.totalPages = (int) total/pageable.getPageSize();
    }


    public PageDto(List<E> content){
        this.content = content;
        this.totalElements = content.size();
        this.totalPages = 0;
    }

    public List<E> getContent() {
        return content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
