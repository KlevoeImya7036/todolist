package com.vakin.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vakin.todolist.dto.ForumMessageDto;
import com.vakin.todolist.model.ForumMessage;
import com.vakin.todolist.model.ForumTopic;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.ForumMessageRepository;

@Service
public class ForumMessageService {

    @Autowired
    private ForumMessageRepository forumMessageRepository;

    
    @Transactional
    public void save(ForumMessageDto forumMessageDto, User user, ForumTopic forumTopic) {
        ForumMessage forumMessageEntity = new ForumMessage();
        forumMessageEntity.setText(forumMessageDto.getText());
        forumMessageEntity.setAuthor(user);
        forumMessageEntity.setForumTopic(forumTopic);
        forumMessageRepository.save(forumMessageEntity);
    }

    @Transactional
    public void delete(ForumMessage forumMessage) {
        forumMessageRepository.delete(forumMessage);
    }

    public List<ForumMessage> getAllByForumTopic(ForumTopic forumTopic) {
        return forumMessageRepository.findByForumTopic(forumTopic);
    }

    @Transactional
    public void deleteAllByForumTopicId(Long id) {
        forumMessageRepository.deleteByForumTopicId(id);
    }
}
