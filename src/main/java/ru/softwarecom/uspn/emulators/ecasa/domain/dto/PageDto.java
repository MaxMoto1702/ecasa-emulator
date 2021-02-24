package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class PageDto<T> implements Page<T> {
    private int totalPages;
    private long totalElements;
    private int number;
    private int size;
    private int numberOfElements;
    private List<T> content;
    private Sort sort;
    private boolean first;
    private boolean last;
    private boolean next;
    private boolean previous;

    @Override
    public int getTotalPages() {
        return this.totalPages;
    }

    @Override
    public long getTotalElements() {
        return this.totalElements;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    @Override
    public List<T> getContent() {
        return this.content;
    }

    @Override
    public boolean hasContent() {
        return this.content != null && !this.content.isEmpty();
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public boolean isFirst() {
        return this.first;
    }

    @Override
    public boolean isLast() {
        return this.last;
    }

    @Override
    public boolean hasNext() {
        return this.next;
    }

    @Override
    public boolean hasPrevious() {
        return this.previous;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
