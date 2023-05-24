package com.observer.impl;

import com.observer.action.Observer;
import com.observer.action.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject {

    List<Observer> observerList = new ArrayList<>();

    //Subject의 기능
    public void upload() {
        System.out.println("영상 제작 중...");
        notifyToObserver("영상이 올라갈 거예요");
    }

    public void complete() {
        System.out.println("영상 업로드 완료...");
        notifyToObserver("영상이 새로 추가되었어요");
    }

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
        observerList.forEach(observer -> observer.update(msg));
    }
}
