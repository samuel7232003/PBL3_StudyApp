package com.studywithme.paging;

import com.studywithme.sort.Sorter;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
    void setSorter(Sorter sorter);
    void setMaxPageItem(Integer maxPageItem);
    void setPage(Integer page);
    Integer getMaxPageItem();
}
