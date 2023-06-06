package com.observer.main.observer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class ObserverController {

    private final ObserverService service;

    @GetMapping(value = "/alerts/{observerId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter alerts(@PathVariable("observerId") Long observerId) {
        System.out.println("observer eventSource 요청 들어옴");
        return service.alerts(observerId);
    }

}
