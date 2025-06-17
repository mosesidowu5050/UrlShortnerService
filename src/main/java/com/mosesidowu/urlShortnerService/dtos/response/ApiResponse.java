package com.mosesidowu.urlShortnerService.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private Object response;
    private boolean success;
}
