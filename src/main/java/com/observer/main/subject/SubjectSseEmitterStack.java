package com.observer.main.subject;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Stack;

public class SubjectSseEmitterStack {

    private static final Stack<SseEmitter> sseEmitterStack = new Stack<>();

    private SubjectSseEmitterStack() {

    }

    public static synchronized void addStack(final SseEmitter sseEmitter) {
        sseEmitterStack.push(sseEmitter);
    }

    public static SseEmitter getOne() {
        return sseEmitterStack.pop();
    }
}
