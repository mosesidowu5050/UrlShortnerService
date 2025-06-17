package com.mosesidowu.urlShortnerService.utils;

import com.mosesidowu.urlShortnerService.data.models.UrlShortner;
import com.mosesidowu.urlShortnerService.dtos.request.ShortenUrlRequest;
import com.mosesidowu.urlShortnerService.dtos.response.ShortenUrlResponse;

import java.util.Random;

public class Mapper {

    public static UrlShortner mapToEntity(ShortenUrlRequest request, String shortUrlKey) {
        UrlShortner url = new UrlShortner();
        url.setShortUrlId(shortUrlKey);
        url.setLongUrl(request.getLongUrl());
        return url;
    }


    public static ShortenUrlResponse mapToResponse(String shortUrlKey, String longUrl) {
        ShortenUrlResponse response = new ShortenUrlResponse();
        response.setShortUrl(shortUrlKey);
        response.setLongUrl(longUrl);
        return response;
    }


    public static String generateKey() {
        String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int LENGTH = 6;

        char[] keyChars = new char[LENGTH];
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            keyChars[i] = CHAR_SET.charAt(random.nextInt(CHAR_SET.length()));
        }
        return new String(keyChars);
    }

}

