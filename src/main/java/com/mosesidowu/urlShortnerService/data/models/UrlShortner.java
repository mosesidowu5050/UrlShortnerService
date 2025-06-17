package com.mosesidowu.urlShortnerService.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("UrlShortner")
public class UrlShortner {

    @Id
    private String shortKey;
    private String longUrl;
    private LocalDateTime createdAt;
    private int clickCount;

}
