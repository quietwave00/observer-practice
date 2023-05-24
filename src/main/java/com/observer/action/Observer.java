package com.observer.action;

//Subject의 상태값 변화를 계속해서 알림받을 객체
public interface Observer {
    void update(String msg); //Subject의 상태 변화 값
}
