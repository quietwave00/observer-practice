package com.observer.action;

//observer들이 상태값을 받을 객체
public interface Subject {
    void subscribe(Observer observer);
    void unSubscribe(Observer observer);
    void notifyToObserver(String msg);
}
