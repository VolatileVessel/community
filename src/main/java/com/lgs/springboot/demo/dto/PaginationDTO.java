package com.lgs.springboot.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    //展示分页全部的内容
    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage=totalPage;
        //总页数 =totalCount/size



        this.page=page;
        pages.add(page);
        //i=1代表page的前一位，i=2代表前两位，3代表前三位
        //前三位，后三位
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }

        }

        //什么时候展示前一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //什么时候展示后一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //什么时候展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //什么时候展示第二页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}
