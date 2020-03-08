package com.zc.util;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author 小帅气
 * @create 2019-09-09-21:23
 */
@Accessors(chain = true)
public class Page implements Serializable {
    // 总页数
    private int totalPageCount = 0;
    // 页面大小，即每页显示记录数
    private int pageSize = 10;
    // 记录总数
    private int totalCount;
    // 当前页码
    private int currPageNo = 1;
    private int currPage;

    private List<Object> objectList;

    @Override
    public String toString() {
        return "Page{" +
                "totalPageCount=" + totalPageCount +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", currPageNo=" + currPageNo +
                ", currPage=" + currPage +
                ", objectList=" + objectList +
                '}';
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getCurrPageNo() {
        if (totalPageCount == 0) {
            return 0;
        }
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        if (currPageNo > 0) {
            this.currPageNo = currPageNo;
            this.currPage = (currPageNo - 1) * pageSize;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            // 计算总页数
            totalPageCount = this.totalCount % pageSize == 0 ? (this.totalCount / pageSize)
                    : (this.totalCount / pageSize + 1);
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

}