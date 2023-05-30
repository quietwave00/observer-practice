package com.observer.main.subject.storage;

import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class EmitterStorage {

    private static final Map<Long, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    public static SseEmitter getEmitter(Long userId) {
        return emitterMap.get(userId);
    }

    public static void addEmitter(Long userId, SseEmitter emitter) {
        emitterMap.put(userId, emitter);
    }

    public static void removeEmitter(Long userId) {
        emitterMap.remove(userId);
    }
}
