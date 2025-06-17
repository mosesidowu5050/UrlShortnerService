package com.mosesidowu.urlShortnerService.services;

import com.mosesidowu.urlShortnerService.data.models.UrlShortner;
import com.mosesidowu.urlShortnerService.data.repository.UrlShortnerRepository;
import com.mosesidowu.urlShortnerService.dtos.request.ShortenUrlRequest;
import com.mosesidowu.urlShortnerService.dtos.response.ShortenUrlResponse;
import com.mosesidowu.urlShortnerService.exception.UrlDoesNotExistException;
import com.mosesidowu.urlShortnerService.exception.UrlRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.mosesidowu.urlShortnerService.utils.Mapper.*;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlShortnerRepository urlShortnerRepository;



    @Override
    public ShortenUrlResponse shortenUrl(ShortenUrlRequest request) {
        validatePreferredUrl(request.getPreferredUrl());
        String shortUrlKey = request.getPreferredUrl();
        if (shortUrlKey == null || shortUrlKey.isBlank()) {
            do {
                shortUrlKey = generateKey();
            } while (urlShortnerRepository.existsByShortUrlId(shortUrlKey));
        }
        return getShortenUrlResponse(request, shortUrlKey);
    }


    private ShortenUrlResponse getShortenUrlResponse(ShortenUrlRequest request, String shortUrlKey) {
        UrlShortner urlMapping = mapToEntity(request, shortUrlKey);
        urlMapping.setCreatedAt(LocalDateTime.now());
        urlMapping.setClickCounter(0);
        urlShortnerRepository.save(urlMapping);

        return mapToResponse(shortUrlKey, request.getLongUrl());
    }


    @Override
    public String getLongUrl(String shortKey) {
        UrlShortner longUrl = urlShortnerRepository.findByShortUrlId(shortKey);
        validateLongUrl(shortKey, longUrl);
        longUrl.setClickCounter(longUrl.getClickCounter() + 1);
        urlShortnerRepository.save(longUrl);

        return longUrl.getLongUrl();
    }


    private static void validateLongUrl(String shortKey, UrlShortner longUrl) {
        if(longUrl == null) throw new UrlDoesNotExistException("Url doesn't exist");
    }


    private void validatePreferredUrl(String preferredUrl){
        Optional<UrlShortner> urlExist = urlShortnerRepository.findUrlShortnersByLongUrl(preferredUrl);
                if(urlExist.isPresent()) throw new UrlDoesNotExistException("Preferred Url already exists");
    }
}
