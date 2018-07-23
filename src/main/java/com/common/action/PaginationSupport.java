package com.common.action;

import java.util.ArrayList;
import java.util.List;


public class PaginationSupport<T> {
    // Fields
    public static int DEFAULT_PAGE_SIZE = 20;
    private int totalRows = 0; // 记录总数

    private int totalPages = 0; // 总页数

    private int pageSize = 20; // 每页显示数据条数，默认为20条记录

    private int currentPage = 1; // 当前页数

    private int previousPage;// 前一页

    private int nextPage;// 后一页

    private int viewPage = 1;// 要访问的页

    private boolean hasPrevious = false; // 是否有上一页

    private boolean hasNext = false; // 是否有下一页

    private List<T> results;

    public PaginationSupport() {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
    }

    public PaginationSupport(int currentPage, int totalRows, int pageSize,
                             List<T> results) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRows = totalRows;
        this.results = results;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getPreviousPage() {
        return currentPage - 1;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return currentPage + 1;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getViewPage() {
        return viewPage;
    }

    public void setViewPage(int viewPage) {
        this.viewPage = viewPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
