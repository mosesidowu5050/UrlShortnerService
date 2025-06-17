package com.mosesidowu.urlShortnerService.dtos.request;

import lombok.Data;

@Data
public class ShortenUrlRequest {

    private String longUrl;
    private String preferredUrl;

}
