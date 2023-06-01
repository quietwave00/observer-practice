package com.observer.main.subject.storage;

import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class EmitterStorage {

    private static final Map<Long, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    public static SseEmitter getOneEmitter(Long userId) {
        return emitterMap.get(userId);
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

}
