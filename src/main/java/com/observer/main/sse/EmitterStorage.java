package com.observer.main.sse;

import com.observer.main.EmitterDto;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.emitter.Emitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmitterStorage {
    public static Map<Long, EmitterDto> emitterDtoMap = new ConcurrentHashMap<>();




    public static int getMapSize() {
        return emitterDtoMap.size();
    }

    public static SseEmitter getEmitterByObserverId(Long observerId) {
        return emitterDtoMap.get(observerId).getSseEmitter();
    }


    public static void addEmitter(Long userId, EmitterDto dto) {
        emitterDtoMap.put(userId, dto);
    }

    public static void removeEmitter(Long userId) {
        emitterDtoMap.remove(userId);
    }


}
