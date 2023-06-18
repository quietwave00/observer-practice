package com.observer.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Getter
@AllArgsConstructor
public class EmitterDto {

    SseEmitter sseEmitter;
    Long userId;
  //  boolean result;

    // 시간지낫으면 false, 안지났으면 true
 //   boolean timeout;



//    public void setResult(boolean result) {
//        this.result = result;
//    }
//
//
//    public void setTimeout(boolean timeout) {
//        this.timeout = timeout;
//    }

    public void setSseEmitter(SseEmitter sseEmitter) {
        this.sseEmitter = sseEmitter;
    }
}
