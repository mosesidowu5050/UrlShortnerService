package com.mosesidowu.urlShortnerService.data.repository;

import com.mosesidowu.urlShortnerService.data.models.UrlShortner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UrlShortnerRepository extends MongoRepository<UrlShortner, String> {

    boolean existsByShortUrlId(String shortUrlKey);
    UrlShortner findByShortUrlId(String shortUrlKey);

    Optional<UrlShortner> findUrlShortnersByLongUrl(String preferredUrl);
}
