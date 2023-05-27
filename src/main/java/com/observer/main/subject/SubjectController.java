package com.observer.main.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService service;

    @GetMapping("/subscribe/{observerId}")
    public ResponseEntity<?> subscribe(@PathVariable("observerId") Long observerId) {
        System.out.println(observerId);
        service.subscribe(observerId);
        return null;
    }

    @GetMapping("/upload")
    public String upload() {
        return service.upload();
    }
}
