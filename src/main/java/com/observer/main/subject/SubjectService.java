package com.observer.main.subject;

import com.observer.entity.Observer;
import com.observer.entity.Subject;
import com.observer.main.observer.ObserverRepository;
import com.observer.main.subject.storage.EmitterStorage;
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
        Subject beforeSubject = subjectRepository.findBySubjectId(1L);
        Observer findObserver = observerRepository.findByObserverId(observerId);
        beforeSubject.subscribe(findObserver);
        Subject afterSubject = subjectRepository.save(beforeSubject);

        SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(() -> afterSubject.getObserverList().remove(findObserver));
        EmitterStorage.addEmitter(observerId, emitter);

        return emitter;
    }

    public String upload() throws IOException {
        SseEmitter emitter = EmitterStorage.getEmitter(userId);

        Subject findSubject = subjectRepository.findBySubjectId(1L);
        return findSubject.upload();
    }


}
