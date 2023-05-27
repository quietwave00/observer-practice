package com.observer.main.observer;

import com.observer.entity.Observer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObserverRepository extends JpaRepository<Observer, Long> {


    Observer findByObserverId(Long observerId);
}
