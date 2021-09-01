package com.challenge.quasar.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class MessageService {
    public String decodeMessage(List<String[]> messagesList) throws Exception {
        String[] decodedMessage = new String[messagesList.stream().findFirst().get().length];
        messagesList.forEach( (final String[] message) -> {
            for (int i=0; i<message.length; i++) {
                if (!message[i].isBlank()) {
                    decodedMessage[i] = message[i];
                }
            }
        });
        if (Arrays.stream(decodedMessage).anyMatch(element -> element==null)) {
            throw new Exception("No se logró decodificar por completo el mensaje");
        }
        return String.join(" ", decodedMessage);
    }
}
