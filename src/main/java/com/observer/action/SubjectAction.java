package com.observer.action;

import com.observer.entity.Observer;

//observer들이 상태값을 받을 객체
public interface SubjectAction {
    void subscribe(Observer observer);
    void unSubscribe(Observer observer);
    void notifyToObserver(String msg);
}
