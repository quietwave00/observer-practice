package com.observer.entity;

import com.observer.action.ObserverAction;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Observer implements ObserverAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long observerId;

    public String name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID")
    public Subject subject;

    //연관관계 메소드
    public void addSubject(Subject subject) {
        this.subject = subject;
    }

    //기능
    @Override
    public void update(String msg) {
        System.out.println("New Alert: " + msg);
    }
}
