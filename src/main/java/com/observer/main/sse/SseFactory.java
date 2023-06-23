package com.observer.main.sse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Component
public class SseFactory {

    private static final SseEmitter emitter = new SseEmitter();




}
