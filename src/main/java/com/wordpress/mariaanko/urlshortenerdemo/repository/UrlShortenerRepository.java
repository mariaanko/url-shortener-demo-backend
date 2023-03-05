package com.wordpress.mariaanko.urlshortenerdemo.repository;

import com.wordpress.mariaanko.urlshortenerdemo.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortenerRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByGeneratedUrl(String generatedUrl);
}
