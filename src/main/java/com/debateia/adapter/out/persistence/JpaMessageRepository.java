package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.MessageEntity;
import com.debateia.application.ports.out.persistence.MessageRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kjorda
 */
public class JpaMessageRepository implements MessageRepository {
    public interface MessageRepo extends JpaRepository<MessageEntity, Integer> {
    
    }

}
