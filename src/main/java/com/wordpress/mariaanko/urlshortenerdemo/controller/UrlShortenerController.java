package com.wordpress.mariaanko.urlshortenerdemo.controller;

import com.wordpress.mariaanko.urlshortenerdemo.model.Link;
import com.wordpress.mariaanko.urlshortenerdemo.repository.UrlShortenerRepository;
import com.wordpress.mariaanko.urlshortenerdemo.service.UrlShortenerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @GetMapping(value = "/get/{generatedUrl}")
    public ResponseEntity<Link> get(@PathVariable String generatedUrl){

        Optional<Link> link = urlShortenerService.getByGeneratedUrl(generatedUrl);
        if (link.isPresent()) {
            return new ResponseEntity<>(link.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Link> create(
            @RequestBody Link inputUrl){

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
        urlShortenerService.save(generatedLink);
        return ResponseEntity.created(URI.create(inputUrl.getGeneratedUrl())).body(generatedLink);
    }
}
