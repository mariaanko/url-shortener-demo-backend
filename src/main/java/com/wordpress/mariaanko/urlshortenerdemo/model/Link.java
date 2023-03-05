package com.wordpress.mariaanko.urlshortenerdemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shortened_urls")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "generated_url")
    private String generatedUrl;

    public Link() {
    }

    public Link(String originalUrl, String generatedUrl) {
        this.originalUrl = originalUrl;
        this.generatedUrl = generatedUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getGeneratedUrl() {
        return generatedUrl;
    }

    public void setGeneratedUrl(String generatedUrl) {
        this.generatedUrl = generatedUrl;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", generatedUrl='" + generatedUrl + '\'' +
                '}';
    }
}
