package com.basmingo.m1.repository;

import com.basmingo.m1.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Message save(Message message);
}
