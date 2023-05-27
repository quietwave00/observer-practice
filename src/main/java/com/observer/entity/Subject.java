package com.observer.entity;

import com.observer.action.SubjectAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject implements SubjectAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long subjectId;

    public String name;

    @OneToMany
    public static List<Observer> observerList = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unSubscribe(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyToObserver(String msg) {
        for(Observer observer : observerList) {
            observer.update(msg);
        }
    }

    public String upload() {
        System.out.println("영상 제작 중...");
        String msg = "영상이 올라갈 거예요";
        notifyToObserver(msg);
        return msg;
    }

    public void complete() {
        System.out.println("영상 업로드 완료...");
        notifyToObserver("영상이 새로 추가되었어요");
    }
}
