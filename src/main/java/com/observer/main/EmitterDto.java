package com.observer.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Getter
@AllArgsConstructor
public class EmitterDto {

    SseEmitter sseEmitter;
    Long userId;


}
