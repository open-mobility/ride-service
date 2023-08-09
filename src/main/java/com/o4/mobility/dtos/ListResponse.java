package com.o4.mobility.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResponse extends ListRequest {
    private Long totalRecords;
    private Integer currentRecordsCount;
    private Integer totalPages;
}
