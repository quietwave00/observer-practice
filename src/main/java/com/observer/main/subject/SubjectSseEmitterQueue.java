package com.observer.main.subject;


import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.LinkedList;
import java.util.Queue;

public class SubjectSseEmitterQueue {

    private static final Queue<SseEmitter> sseEmitterQueue = new LinkedList<>();

    private SubjectSseEmitterQueue() {

    }

    public static synchronized void addQueue(final SseEmitter sseEmitter) {
        sseEmitterQueue.add(sseEmitter);
    }

    public static synchronized SseEmitter getOne() {
        return sseEmitterQueue.poll();
    }
}
