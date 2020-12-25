package com.example.demo.repo;

import com.example.demo.modeles.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageRepo extends CrudRepository<Message, Long> {
    Iterable<Message> findByTag(String tag);

}
