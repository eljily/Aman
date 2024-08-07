package com.sidibrahim.Aman.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseMessage {
    private String message;
    private Object data;
    private Integer status;
    private PaginationData meta;
}
