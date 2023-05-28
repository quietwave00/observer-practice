package com.observer.entity;

import com.observer.action.SubjectAction;
import com.observer.main.subject.SubjectSseEmitterQueue;
import com.observer.main.subject.SubjectSseEmitterStack;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Subject implements SubjectAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long subjectId;

    public String name;

    @OneToMany
    public List<Observer> observerList = new ArrayList<>();


    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unSubscribe(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyToObserver(String msg) throws IOException {
        for(Observer observer : observerList) {
            SseEmitter emitter = SubjectSseEmitterStack.getOne();
            System.out.println("notify : " + emitter);
            emitter.send(SseEmitter.event().data(msg));
            observer.update(msg);
        }
    }

    public SseEmitter getSseEmitter() {
        return SubjectSseEmitterStack.getOne();
    }

    public String upload() throws IOException {
        System.out.println("영상 제작 중...");
        String msg = "영상이 올라갈 거예요";
        notifyToObserver(msg);
        return msg;
    }

    public void complete() throws IOException {
        System.out.println("영상 업로드 완료...");
        notifyToObserver("영상이 새로 추가되었어요");
    }
}
