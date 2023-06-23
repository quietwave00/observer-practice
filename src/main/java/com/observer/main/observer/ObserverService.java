package com.observer.main.observer;

import com.observer.main.sse.EmitterStorage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ObserverService {

//    @TransactionalEventListener
//    @Async
//    public CompletableFuture<SseEmitter> alerts(Long observerId) throws InterruptedException, ExecutionException {
//        CompletableFuture<SseEmitter> emitter = EmitterStorage.getEmitterByObserverId(observerId);
//
//        System.out.println("Observer MapSize: " + EmitterStorage.getMapSize());
//        return emitter;
//    }
}
