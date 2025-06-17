package com.mosesidowu.urlShortnerService.services;

import com.mosesidowu.urlShortnerService.data.repository.UrlShortnerRepository;
import com.mosesidowu.urlShortnerService.dtos.request.ShortenUrlRequest;
import com.mosesidowu.urlShortnerService.dtos.response.ShortenUrlResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlShortnerRepository urlShortnerRepository;
    private String baseUrl;

    @Override
    public ShortenUrlResponse shortenUrl(ShortenUrlRequest request) {

        return null;
    }

    @Override
    public String getLongUrl(String shortUrlKey) {
        return "";
    }






    public String resolveBaseUrl(HttpServletRequest request) {
        if (baseUrl == null || baseUrl.isBlank()) {
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        }
        return baseUrl;
    }

}
