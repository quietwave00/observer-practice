package com.observer.main.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService service;


    @GetMapping("/subscribe/{observerId}")
    public SseEmitter subscribe(@PathVariable("observerId") Long observerId) {
        return service.subscribe(observerId);
    }

    @GetMapping("/upload")
    public String upload() throws IOException {
        return service.upload();
    }
}
