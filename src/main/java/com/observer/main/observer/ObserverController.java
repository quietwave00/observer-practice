package com.observer.main.observer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class ObserverController {

    private final ObserverService service;

//    @GetMapping(value = "/alerts/{observerId}", produces = "text/event-stream")
//    public CompletableFuture<SseEmitter> alerts(@PathVariable("observerId") Long observerId) throws InterruptedException, ExecutionException {
//        System.out.println("observer eventSource 요청 들어옴: " + observerId);
//        return service.alerts(observerId);
//    }

}
