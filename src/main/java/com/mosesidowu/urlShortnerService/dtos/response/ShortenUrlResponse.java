package com.mosesidowu.urlShortnerService.dtos.response;

import lombok.Data;

@Data
public class ShortenUrlResponse {

    private String shortUrl;
    private String longUrl;

}
