package tech.jhipster.web.util;

import java.util.ArrayList;
import java.util.List;

public class PageRecord<T> {
    private long total = 0;

    private long page = 0;

    private long size = 15;

    private List<T> records = new ArrayList<>();

    public PageRecord() {
    }

    public PageRecord(long total, List<T> records, long page, long size) {
        this.total = total;
        this.records = records;
        this.page = page;
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public PageRecord<T> records(List<T> records) {
        this.records = records;
        return this;
    }

    public PageRecord<T> total(long total) {
        this.total = total;
        return this;
    }

    public PageRecord<T> size(long size) {
        this.size = size;
        return this;
    }

    public PageRecord<T> page(long page) {
        this.page = page;
        return this;
    }
}
