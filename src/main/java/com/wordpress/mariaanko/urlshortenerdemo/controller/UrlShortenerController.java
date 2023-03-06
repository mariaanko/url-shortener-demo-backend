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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Link> create(
            @RequestBody Link inputUrl){
        Link generatedLink = urlShortenerService.save(inputUrl);
        return ResponseEntity.created(URI.create(inputUrl.getGeneratedUrl())).body(generatedLink);
    }
}
