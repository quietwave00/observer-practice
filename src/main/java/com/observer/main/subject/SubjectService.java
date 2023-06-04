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

    public SseEmitter subscribe(Long observerId) throws IOException {
        Subject beforeSubject = subjectRepository.findBySubjectId(1L);
        Observer beforeObserver = observerRepository.findByObserverId(observerId);
        beforeObserver.addSubject(beforeSubject);
        Observer afterObserver = observerRepository.save(beforeObserver);
        beforeSubject.subscribe(afterObserver);
        Subject afterSubject = subjectRepository.save(beforeSubject);
        System.out.println("dd: " + afterSubject.getSubjectId());
        

        SseEmitter emitter = new SseEmitter();
        emitter.send(SseEmitter.event().name("init"));
//        emitter.onCompletion(() -> afterSubject.getObserverList().remove(findObserver));
        emitter.onCompletion(() -> EmitterStorage.removeEmitter(afterObserver.observerId));

        EmitterStorage.addEmitter(observerId, emitter);
        EmitterStorage.addObserverId(emitter, observerId);

        return emitter;
    }

    public void upload(){
        Subject findSubject = subjectRepository.findBySubjectId(1L);
        findSubject.upload();
    }


}
