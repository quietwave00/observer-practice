package com.observer.main.subject;

import com.observer.entity.Observer;
import com.observer.entity.Subject;
import com.observer.main.observer.ObserverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final ObserverRepository observerRepository;
    private final SubjectRepository subjectRepository;

    public SseEmitter subscribe(Long observerId) {
        SseEmitter emitter = new SseEmitter();
        SubjectSseEmitterStack.addStack(emitter);
        SubjectSseEmitterQueue.addQueue(emitter);
        System.out.println("subscribe : " + emitter);
        Subject beforeSubject = subjectRepository.findBySubjectId(1L);
        Observer findObserver = observerRepository.findByObserverId(observerId);
        beforeSubject.subscribe(findObserver);
        Subject afterSubject = subjectRepository.save(beforeSubject);

        emitter.onCompletion(() -> afterSubject.getObserverList().remove(findObserver));
     //   System.out.println("subscribe : " + emitter);
        return emitter;
    }

    public String upload() throws IOException {
        SseEmitter sseEmitter = SubjectSseEmitterQueue.getOne();
        SseEmitter sseEmitter2 = SubjectSseEmitterQueue.getOne();
        SseEmitter sseEmitter3 = SubjectSseEmitterQueue.getOne();
//        System.out.println("upload : " + sseEmitter);
//        System.out.println("upload : " + sseEmitter2);
//        System.out.println("upload : " + sseEmitter3);
        Subject findSubject = subjectRepository.findBySubjectId(1L);
        return findSubject.upload();
    }


}
