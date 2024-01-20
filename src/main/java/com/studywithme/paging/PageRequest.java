package com.studywithme.paging;

import com.studywithme.sort.Sorter;

public class PageRequest implements Pageable {

    private Integer page;
    private Integer maxPageItem;
    private Sorter sorter;
    @Override
    public Sorter getSorter() {
        if ( this.sorter != null) {
            return this.sorter;
        }
        return null;
    }


    public PageRequest() {
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setMaxPageItem(Integer maxPageItem) {
        this.maxPageItem = maxPageItem;
    }

    @Override
    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    @Override
    public Integer getMaxPageItem() {
        return this.maxPageItem;
    }


    public PageRequest(Integer page, Integer maxPageItem, Sorter sorter){
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.sorter = sorter;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null && this.maxPageItem != null){
            return (this.page-1)*this.maxPageItem;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItem;
    }


}
