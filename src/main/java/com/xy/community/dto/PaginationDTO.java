package com.xy.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private long page;
    private List<Long> pages = new ArrayList();
    private long totalPage;


    public void setPagination(long totalCount, long page, Integer size) {

        long totalPage = (totalCount%size == 0)?(totalCount/size):(totalCount/size+1);
        this.totalPage = totalPage;
        if (page > totalPage) page = totalPage;
        if (page < 1) page = 1;

        pages.add(page);
        this.page = page;

        for (int i = 1; i <= 3; i++) {
            if (page - i>0){
                pages.add(page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page+i);
            }
        }

        this.showPrevious = (page == 1l)?(false):(true);
        this.showNext = (page == totalPage)?(false):(true);
        this.showFirstPage = ((pages.contains(1l))?(false):(true));
        this.showEndPage = (pages.contains(totalPage))?(false):(true);


    }
}
