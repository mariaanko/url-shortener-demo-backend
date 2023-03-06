package com.wordpress.mariaanko.urlshortenerdemo.service;

import com.wordpress.mariaanko.urlshortenerdemo.model.Link;
import com.wordpress.mariaanko.urlshortenerdemo.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlShortenerService {

    private static final int leftLimit = 97; // letter 'a'
    private static final int rightLimit = 122; // letter 'z'
    private static final int targetStringLength = 10;
    @Autowired
    UrlShortenerRepository urlShortenerRepository;

    public Optional<Link> getByGeneratedUrl(String generatedUrl){
        return urlShortenerRepository.findByGeneratedUrl(generatedUrl);
    }

    public Link save(Link link){
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        link.setGeneratedUrl(generatedString);

        Link generatedLink = new Link(link.getOriginalUrl(),link.getGeneratedUrl());
        return urlShortenerRepository.save(generatedLink);
    }
}
