package com.observer.main.sse;

import com.observer.main.EmitterDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class EmitterStorage {
    public static Map<Long, EmitterDto> emitterDtoMap = new ConcurrentHashMap<>();




    public static int getMapSize() {
        return emitterDtoMap.size();
    }

    public static SseEmitter getEmitterByObserverId(Long observerId) {

        return emitterDtoMap.get(observerId).getSseEmitter();
    }

    public static EmitterDto getEmitterDtoByObserverId(Long observerId){
        return emitterDtoMap.get(observerId);
    }

    public static SseEmitter addEmitter(Long observerId) throws IOException {
        SseEmitter emitter = new SseEmitter();
        EmitterDto dto = new EmitterDto(emitter, observerId);
        emitter.send(SseEmitter.event().name("init").data("EventStream Created"));
        emitter.onCompletion(() -> {
            System.out.println("emitter.onCompletion() Run");
            EmitterStorage.removeEmitter(observerId);
            SseEmitter emitter1 = new SseEmitter();
            dto.setSseEmitter(emitter1);
            try {
                emitter1.send(SseEmitter.event().name("init").data("EventStream Created"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        emitter.onTimeout(() -> {
            System.out.println("emitter.onTimeout() Run");
            EmitterStorage.removeEmitter(observerId);
        });
        emitterDtoMap.put(observerId, dto);
        return emitter;
    }

    public static void removeEmitter(Long userId) {
        emitterDtoMap.remove(userId);
    }


}
