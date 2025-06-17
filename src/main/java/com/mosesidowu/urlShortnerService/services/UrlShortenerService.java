package com.mosesidowu.urlShortnerService.services;

import com.mosesidowu.urlShortnerService.dtos.request.ShortenUrlRequest;
import com.mosesidowu.urlShortnerService.dtos.response.ShortenUrlResponse;


public interface UrlShortenerService {

    ShortenUrlResponse shortenUrl(ShortenUrlRequest request);
    String getLongUrl(String shortUrlKey);

}
