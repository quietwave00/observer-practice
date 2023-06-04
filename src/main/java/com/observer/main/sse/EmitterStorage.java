package com.observer.main.sse;

import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class EmitterStorage {

    private static final Map<Long, SseEmitter> emitterMap = new ConcurrentHashMap<>();
    private static final Map<SseEmitter, Long> observerMap = new ConcurrentHashMap<>();

    //emitterMap Method
    public static SseEmitter getEmitterByObserverId(Long observerId) {
        return emitterMap.get(observerId);
    }

    public static List<SseEmitter> getEmitterList() {
        List<SseEmitter> emitters = new ArrayList<>();
        for (Map.Entry<Long, SseEmitter> entry : emitterMap.entrySet()) {
            SseEmitter emitter = entry.getValue();
            emitters.add(emitter);
        }
        return emitters;
    }

    public static void addEmitter(Long userId, SseEmitter emitter) {
        emitterMap.put(userId, emitter);
    }

    public static void removeEmitter(Long userId) {
        emitterMap.remove(userId);
    }

    //observerMap Method
    public static Long getObserverIdByEmitter(SseEmitter emitter) {
        return observerMap.get(emitter);
    }

    public static void addObserverId(SseEmitter emitter, Long userId) {
        observerMap.put(emitter, userId);
    }

}
