package com.observer.entity;

import com.observer.action.SubjectAction;
import com.observer.main.sse.EmitterStorage;
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

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
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
    public void notifyToObserver(String msg){
        int size = EmitterStorage.getMapSize();
        for (long i = 1L; i <= size; i++) {
            try {
                EmitterStorage.emitterDtoMap.get(i).getSseEmitter().send(SseEmitter.event().name("message").data(msg));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void upload(){
        System.out.println("영상 제작 중...");
        String msg = "영상이 올라갈 거예요";
        notifyToObserver(msg);
    }

    public void complete(){
        System.out.println("영상 업로드 완료...");
        notifyToObserver("영상이 새로 추가되었어요");
    }
}
