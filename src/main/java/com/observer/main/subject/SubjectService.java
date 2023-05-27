package com.observer.main.subject;

import com.observer.entity.Observer;
import com.observer.entity.Subject;
import com.observer.main.observer.ObserverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final ObserverRepository observerRepository;
    private final SubjectRepository subjectRepository;

    public void subscribe(Long observerId) {
        Subject beforeSubject = subjectRepository.findBySubjectId(1L);
        Observer findObserver = observerRepository.findByObserverId(observerId);
        beforeSubject.subscribe(findObserver);
        Subject afterSubject = subjectRepository.save(beforeSubject);
    }

    public String upload() {
        Subject findSubject = subjectRepository.findBySubjectId(1L);
        return findSubject.upload();
    }
}
