package com.zc.vo;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = -3720998571176536865L;
    private List<T> content = new ArrayList<>();
    private long total;
    private int pageNum;
    private int pageSize;
    private BigDecimal countAmount;

    public Page() {
    }

    public Page(List<T> content, long total, int pageNum, int pageSize) {
        this.content = content;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    public Page(List<T> content, long total, int pageNum, int pageSize, BigDecimal countAmount) {
        this.content = content;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.countAmount = countAmount;
    }
    private BigDecimal totalWithdrawAmount;

    private Integer successNum;

    private Integer failureNum;
    /**
     * Returns if there is a previous page.
     *
     * @return if there is a previous page.
     */
    public boolean hasPrevious() {
        return getPageNum() > 1;
    }

    /**
     * Returns if there is a next page.
     *
     * @return if there is a next page.
     */
    public boolean hasNext() {
        return getPageNum() < getTotalPage();
    }

    /**
     * Returns whether the current page is the first one.
     *
     * @return whether the current page is the first one.
     */
    public boolean isFirst() {
        return !hasPrevious();
    }

    /**
     * Returns whether the current  page is the last one.
     *
     * @return whether the current  page is the last one.
     */
    boolean isLast() {
        return !hasNext();
    }

    /**
     * Returns the total amount of elements of all pages.
     *
     * @return the total amount of elements of all pages.
     */
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * Returns the number of total pages.
     *
     * @return the number of total pages
     */
    public int getTotalPage() {
        return getPageSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getPageSize());
    }

    /**
     * Returns the page content as unmodifiable {@link List}.
     *
     * @return Returns the page content as unmodifiable {@link List}
     */
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * Returns whether the current page has content.
     *
     * @return whether the current page has content.
     */
    public boolean hasContent() {
        return getContentSize() > 0;
    }

    /**
     * Returns the number of elements on current page.
     *
     * @return the number of elements on current page.
     */
    public int getContentSize() {
        return content.size();
    }

    /**
     * Returns the number of items of each page.
     *
     * @return the number of items of each page
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns the number of current page. (Zero-based numbering.)
     *
     * @return the number of current page.
     */
    public int getPageNum() {
        return pageNum == 0 ? 1 : pageNum;
    }

    /**
     * Set the number of current page. (Zero-based numbering.)
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public Iterator<T> iterator() {
        return getContent().iterator();
    }

    public BigDecimal getCountAmount() {
        return countAmount;
    }

    public void setCountAmount(BigDecimal countAmount) {
        this.countAmount = countAmount;
    }

    public BigDecimal getTotalWithdrawAmount() {
        return totalWithdrawAmount;
    }

    public void setTotalWithdrawAmount(BigDecimal totalWithdrawAmount) {
        this.totalWithdrawAmount = totalWithdrawAmount;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(Integer failureNum) {
        this.failureNum = failureNum;
    }
}
