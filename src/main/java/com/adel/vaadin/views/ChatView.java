package com.adel.vaadin.views;

import com.adel.vaadin.services.ChatService;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;

import java.util.ArrayList;

@Route("")
public class ChatView extends VerticalLayout {

    public ChatView(
            ChatService chatService
    ) {
        final var messageList = new MessageList();
        final var textInput = new MessageInput();

        setSizeFull();
        add(messageList, textInput);
        expand(messageList);
        textInput.setWidthFull();

        chatService.join().subscribe(message -> {
            final var newList = new ArrayList<>(messageList.getItems());
            newList.add(
                    new MessageListItem(message.text(), message.time(), message.username())
            );
            getUI().ifPresent(ui -> ui.access((Command) () -> messageList.setItems(newList)));
        });

        textInput.addSubmitListener(submitEvent -> chatService.add(submitEvent.getValue()));

    }
}
