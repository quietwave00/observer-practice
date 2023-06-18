package com.observer.main.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService service;




    @GetMapping(value = "/subscribe/{observerId}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable("observerId") Long observerId,
                                                   @RequestHeader(value = "Last-Event_Id", required = false, defaultValue = "") String lastEventId) throws IOException {
        System.out.println("subject 알림 활성화 요청 들어옴");
        return service.subscribe(observerId);
    }

    @GetMapping(value = "/alerts/{observerId}", produces = "text/event-stream")
    public SseEmitter alert(@PathVariable("observerId") Long observerId) throws InterruptedException, ExecutionException, IOException {
        System.out.println("observer 요청 들어옴");
        return service.alert(observerId);
    }


    @GetMapping("/upload")
    public void upload() throws IOException {
        service.upload();
    }
}
