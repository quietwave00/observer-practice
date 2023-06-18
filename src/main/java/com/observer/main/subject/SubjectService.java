package com.observer.main.subject;

import com.observer.entity.Observer;
import com.observer.entity.Subject;
import com.observer.main.EmitterDto;
import com.observer.main.observer.ObserverRepository;
import com.observer.main.sse.EmitterStorage;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.emitter.Emitter;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {

    private final ObserverRepository observerRepository;
    private final SubjectRepository subjectRepository;
    private final CompletableFuture<SseEmitter> future = new CompletableFuture<>();
    public SseEmitter subscribe(Long observerId) throws IOException {
        dataSetting(observerId);

        System.out.println("emitterSize :" + EmitterStorage.getMapSize());
        System.out.println("observerSize : " + EmitterStorage.getMapSize());
        return EmitterStorage.addEmitter(observerId);
    }

    public SseEmitter alert(Long observerId) throws IOException, InterruptedException {

        try {
            return EmitterStorage.getEmitterDtoByObserverId(observerId).getSseEmitter();

        } catch (NullPointerException e) {
            EmitterStorage.addEmitter(observerId);
            return EmitterStorage.getEmitterDtoByObserverId(observerId).getSseEmitter();
        }



//        if(!dto.isTimeout()) {
//            EmitterStorage.removeEmitter(observerId);
//            dto.setTimeout(true);
//            dto.setResult(false);
//            EmitterStorage.addEmitter(observerId, dto);
//        }
//
//        if(!dto.isResult()) {
//            try {
//                return subscribe(observerId).join();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            return dto.getSseEmitter();
//        }


//        return future.thenApplyAsync(result -> {
//            SseEmitter emitter = EmitterStorage.getEmitterByObserverId(observerId);
//            if (emitter != null) {
//                return emitter;
//            } else {
//                try {
//                    return subscribe(observerId).join();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });

    }
    @Transactional
    public void dataSetting(final Long observerId) {
        Subject beforeSubject = subjectRepository.findBySubjectId(1L);
        Observer beforeObserver = observerRepository.findByObserverId(observerId);
        beforeObserver.addSubject(beforeSubject);
        Observer afterObserver = observerRepository.save(beforeObserver);
        beforeSubject.subscribe(afterObserver);
        Subject afterSubject = subjectRepository.save(beforeSubject);
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
