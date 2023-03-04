package com.wordpress.mariaanko.urlshortenerdemo.controller;

import com.wordpress.mariaanko.urlshortenerdemo.model.Link;
import com.wordpress.mariaanko.urlshortenerdemo.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    UrlShortenerRepository urlShortenerRepository;
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Link> create(
            @RequestBody Link inputUrl, WebRequest request){

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        inputUrl.setGeneratedUrl(generatedString);

        Link generatedLink = new Link(inputUrl.getOriginalUrl(),inputUrl.getGeneratedUrl());
        urlShortenerRepository.save(generatedLink);
        return ResponseEntity.created(URI.create(inputUrl.getGeneratedUrl())).body(generatedLink);
    }
}
