package com.adel.vaadin.services;

import com.adel.vaadin.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final Sinks.Many<Message> messages = Sinks.many().multicast().directBestEffort();
    private final Flux<Message> messagesFlux = messages.asFlux();

    public Flux<Message> join(){
        return this.messagesFlux;
    }

    public void add(String message){
        this.messages.tryEmitNext(
                new Message("adel", message, Instant.now())
        );
    }

}
