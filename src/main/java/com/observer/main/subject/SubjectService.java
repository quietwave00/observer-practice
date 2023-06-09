package com.observer.main.subject;

import com.observer.entity.Observer;
import com.observer.entity.Subject;
import com.observer.main.observer.ObserverRepository;
import com.observer.main.sse.EmitterStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {

    private final ObserverRepository observerRepository;
    private final SubjectRepository subjectRepository;

//    @TransactionalEventListener
//    @Async
//    public CompletableFuture<SseEmitter> subscribe(Long observerId) throws IOException {
//        System.out.println("실행체크");
//
//
//        try {
//            SseEmitter emitter2 = EmitterStorage.getObserverMapByObserverId(observerId);
//            emitter2.onCompletion(() -> {
//                System.out.println("옵저버맵 완료");
//                EmitterStorage.removeEmitter(emitter2);
//            });
//            emitter2.onTimeout(() -> {
//                System.out.println("옵저버맵 타임아웃");
//                EmitterStorage.removeEmitter(emitter2);
//
//            });
//        } catch (NullPointerException e) {
//            System.out.println("처음요청");
//        }
//
//
//
//        Long savedObserverId = dataSetting(observerId);
//        SseEmitter emitter = new SseEmitter();
//
//        emitter.send(SseEmitter.event().name("init"));
//        emitter.onCompletion(() -> {
//            System.out.println("연속체크 1");
//            System.out.println("emitter.onCompletion() Run");
//            EmitterStorage.removeEmitter(savedObserverId);
//        });
//        emitter.onTimeout(() -> {
//            System.out.println("연속체크 2");
//            System.out.println("emitter.ontimeout() Run");
//
//            EmitterStorage.removeEmitter(savedObserverId);
//
//        });
//
//
//
//        EmitterStorage.addEmitter(observerId, emitter);
//        EmitterStorage.addObserverId(emitter, observerId);
//        System.out.println("emitterSize :" + EmitterStorage.getEmitterMapSize());
//        System.out.println("observerSize : " + EmitterStorage.getObserverMapSize());
//
//        return CompletableFuture.completedFuture(emitter);
//    }

    @TransactionalEventListener
    @Async
    public SseEmitter subscribe(Long observerId) throws IOException {
        System.out.println("실행체크");


        try {
            SseEmitter emitter =EmitterStorage.getEmitterByObserverId(observerId);


            emitter.onCompletion(() -> {

                System.out.println("emitter.onCompletion() Run");
                EmitterStorage.removeEmitter(emitter);
                EmitterStorage.removeEmitter(observerId);
            });
            emitter.onTimeout(() -> {

                System.out.println("emitter.ontimeout() Run");
                EmitterStorage.removeEmitter(emitter);
                EmitterStorage.removeEmitter(observerId);

            });
            return emitter;

        } catch (NullPointerException e) {
            System.out.println("처음요청");
            dataSetting(observerId);
            SseEmitter emitter = new SseEmitter();
            emitter.send(SseEmitter.event().name("init"));
            EmitterStorage.addEmitter(observerId, emitter);
            EmitterStorage.addObserverId(emitter, observerId);
            return emitter;
        } finally {
            System.out.println("emitterSize :" + EmitterStorage.getEmitterMapSize());
            System.out.println("observerSize : " + EmitterStorage.getObserverMapSize());
        }


    }
    @Transactional
    public Long dataSetting(final Long observerId) {
        Subject beforeSubject = subjectRepository.findBySubjectId(1L);
        Observer beforeObserver = observerRepository.findByObserverId(observerId);
        beforeObserver.addSubject(beforeSubject);
        Observer afterObserver = observerRepository.save(beforeObserver);
        beforeSubject.subscribe(afterObserver);
        Subject afterSubject = subjectRepository.save(beforeSubject);
        return afterSubject.getSubjectId();
    }

    private void sendToObserver(SseEmitter emitter, String emitterId, Long observerId) {
        try {
            emitter.send(SseEmitter.event()
                    .id(emitterId)
                    .name("sse")
                    .data("EventStream Created"));
        } catch(IOException e) {
            EmitterStorage.removeEmitter(observerId);
            throw new RuntimeException("Connection Error");
        }
    }

    public void upload(){
        Subject findSubject = subjectRepository.findBySubjectId(1L);
        findSubject.upload();
    }


}
