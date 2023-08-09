package com.o4.mobility.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListRequest {
    private String keyword;
    private Integer pageNo;
    private Integer recordPerPage;
}
