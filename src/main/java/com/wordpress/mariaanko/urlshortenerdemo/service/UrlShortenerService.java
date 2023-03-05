package com.wordpress.mariaanko.urlshortenerdemo.service;

import com.wordpress.mariaanko.urlshortenerdemo.model.Link;
import com.wordpress.mariaanko.urlshortenerdemo.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlShortenerService {
    @Autowired
    UrlShortenerRepository urlShortenerRepository;

    public Optional<Link> getByGeneratedUrl(String generatedUrl){
        return urlShortenerRepository.findByGeneratedUrl(generatedUrl);
    }

    public Link save(Link link){
        return urlShortenerRepository.save(link);
    }
}
