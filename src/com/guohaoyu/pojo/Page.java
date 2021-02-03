package com.guohaoyu.pojo;

import java.util.List;

/**
 * pageNum当前页码数
 * pageTotal总页码数
 * pageSize分页长度
 * pageCount总记录数
 * items page对象
 */
public class Page<T> {
    private static final Integer Page_Size=4;
    private Integer pageNum;
    private Integer pageTotal;
    private Integer pageSize;
    private Integer pageCount;
    private List<T> items;

    public Page() {
    }

    public Page(Integer pageNum, Integer pageTotal, Integer pageSize, Integer pageCount, List<T> items) {
        this.pageNum = pageNum;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.items = items;
    }


    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", items=" + items +
                '}';
    }

    public static Integer getPage_Size() {
        return Page_Size;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum>0&&pageNum<=pageTotal){
            this.pageNum = pageNum;
        }else if (pageNum<1){
            this.pageNum=1;
        }else {
            this.pageNum=pageTotal;
        }
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
