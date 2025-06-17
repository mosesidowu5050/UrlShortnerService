package com.mosesidowu.urlShortnerService.controller;

import com.mosesidowu.urlShortnerService.dtos.request.ShortenUrlRequest;
import com.mosesidowu.urlShortnerService.dtos.response.ApiResponse;
import com.mosesidowu.urlShortnerService.dtos.response.ShortenUrlResponse;
import com.mosesidowu.urlShortnerService.exception.UrlRuntimeException;
import com.mosesidowu.urlShortnerService.services.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin("*")
public class UrlShortnerController {

    @Autowired
    private UrlShortenerService service;


    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(
            @RequestBody ShortenUrlRequest request,
            HttpServletRequest httpRequest) {

        ShortenUrlResponse response = service.shortenUrl(request);
        String baseUrl = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort();
        try {
            response.setShortUrl(baseUrl + "/" + response.getShortUrl());
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.OK);
        } catch (UrlRuntimeException error) {
            return new ResponseEntity<>(new ApiResponse(error.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/longUrl/{shortKey}")
    public ResponseEntity<?> redirectToLongUrl(@PathVariable("shortKey") String shortKey) {
        String longUrl = service.getLongUrl(shortKey);
        try {
            return new ResponseEntity<>(new ApiResponse(longUrl, true), HttpStatus.OK);
        } catch (UrlRuntimeException error) {
            return new ResponseEntity<>(new ApiResponse(error.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}
