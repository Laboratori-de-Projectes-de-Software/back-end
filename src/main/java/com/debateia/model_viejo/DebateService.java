package com.debateia.model_viejo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debateia.dto_viejo.DebateRequest;
import com.debateia.repository_viejo.DebateRepository;
import com.debateia.repository_viejo.MessageRepository;
@Service
public class DebateService {
    @Autowired
    private DebateRepository debateRepository;
    
    @Autowired
    private MessageRepository messageRepository;
    public Debate createDebate(DebateRequest request) {
        Debate debate = new Debate();
        debate.setTopic(request.getTopic());
        // ... más lógica
        return debateRepository.save(debate);
    }

    public Message processMessage(Long debateId, Message message) {
        // Lógica para procesar el mensaje y obtener respuesta de la IA
        return messageRepository.save(message);
    }
}