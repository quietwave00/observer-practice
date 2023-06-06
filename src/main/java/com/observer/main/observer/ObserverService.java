package com.observer.main.observer;

import com.observer.main.sse.EmitterStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class ObserverService {

    public SseEmitter alerts(Long observerId) {
        return EmitterStorage.getEmitterByObserverId(observerId);
    }
}
