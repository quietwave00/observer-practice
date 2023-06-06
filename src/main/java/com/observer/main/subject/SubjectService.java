package com.observer.main.subject;

import com.observer.entity.Observer;
import com.observer.entity.Subject;
import com.observer.main.observer.ObserverRepository;
import com.observer.main.sse.EmitterStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final ObserverRepository observerRepository;
    private final SubjectRepository subjectRepository;

    public SseEmitter subscribe(Long observerId, String lastEventId) throws IOException {
        Subject beforeSubject = subjectRepository.findBySubjectId(1L);
        Observer beforeObserver = observerRepository.findByObserverId(observerId);
        beforeObserver.addSubject(beforeSubject);
        Observer afterObserver = observerRepository.save(beforeObserver);
        beforeSubject.subscribe(afterObserver);
        Subject afterSubject = subjectRepository.save(beforeSubject);

        SseEmitter emitter = new SseEmitter();
        emitter.send(SseEmitter.event().name("init"));
        emitter.onCompletion(() -> EmitterStorage.removeEmitter(afterObserver.observerId));
        emitter.onTimeout(() -> EmitterStorage.removeEmitter(afterObserver.observerId));

        String emitterId = observerId + "_" + System.currentTimeMillis();
        //503 에러 방지
        sendToObserver(emitter, emitterId, observerId);

        EmitterStorage.addEmitter(observerId, emitter);
        EmitterStorage.addObserverId(emitter, observerId);

        return emitter;
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
