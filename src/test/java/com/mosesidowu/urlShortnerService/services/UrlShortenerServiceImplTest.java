package com.mosesidowu.urlShortnerService.services;


import com.mosesidowu.urlShortnerService.data.models.UrlShortner;
import com.mosesidowu.urlShortnerService.data.repository.UrlShortnerRepository;
import com.mosesidowu.urlShortnerService.dtos.request.ShortenUrlRequest;
import com.mosesidowu.urlShortnerService.dtos.response.ShortenUrlResponse;
import com.mosesidowu.urlShortnerService.exception.UrlDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlShortenerServiceImplTest {

    @Autowired
    private UrlShortenerServiceImpl service;

    @Autowired
    private UrlShortnerRepository repository;

    @BeforeEach
    public void clearDb() {
        repository.deleteAll();
    }

    @Test
    public void testShortenUrl_withGeneratedUrl() {
        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setLongUrl("https://example.com");

        ShortenUrlResponse response = service.shortenUrl(request);

        assertNotNull(response.getShortUrl());
        assertEquals("https://example.com", response.getLongUrl());
        assertTrue(repository.existsByShortUrlId(response.getShortUrl()));
    }

    @Test
    public void testShortenUrl_withPreferredUrl() {
        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setLongUrl("https://app.netlify.com/projects/expensecustomertracker/overview");
        request.setPreferredUrl("mySite");

        ShortenUrlResponse response = service.shortenUrl(request);

        assertEquals("mySite", response.getShortUrl());
        assertEquals("https://app.netlify.com/projects/expensecustomertracker/overview", response.getLongUrl());

        UrlShortner saved = repository.findByShortUrlId("mySite");
        assertNotNull(saved);
    }

    @Test
    public void testGetLongUrl_validUrl() {
        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setLongUrl("https://app.netlify.com/projects/expensecustomertracker/overview");
        ShortenUrlResponse response = service.shortenUrl(request);

        String shortUrl = response.getShortUrl();
        String original = service.getLongUrl(shortUrl);

        assertEquals("https://app.netlify.com/projects/expensecustomertracker/overview", original);

        UrlShortner updated = repository.findByShortUrlId(shortUrl);
        assertEquals(1, updated.getClickCounter());
    }

    @Test
    public void testGetLongUrl_invalidUrl() {
        assertThrows(UrlDoesNotExistException.class,  () ->
        service.getLongUrl("notExistingKey"));
    }
}
