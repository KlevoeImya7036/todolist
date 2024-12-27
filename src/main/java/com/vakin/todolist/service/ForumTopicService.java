package com.vakin.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vakin.todolist.dto.ForumTopicDto;
import com.vakin.todolist.exception.ForumTopicNotFoundException;
import com.vakin.todolist.model.ForumTopic;
import com.vakin.todolist.repository.ForumTopicRepository;
import com.vakin.todolist.repository.UserRepository;

@Service
public class ForumTopicService {
    @Autowired
    private ForumTopicRepository forumTopicRepository;

    @Autowired
    private ForumMessageService forumMessageService;

    @Autowired
    private UserRepository userRepository;
    

    public List<ForumTopic> getAllForumTopics() {
        return forumTopicRepository.findAll();
    }

    public ForumTopic getForumTopicById(Long id) {
        return forumTopicRepository.findById(id).orElseThrow(() -> new ForumTopicNotFoundException("Forum Topic not found"));
    }
    
    @Transactional
    public ForumTopic saveForumTopic(ForumTopicDto forumTopic, String username) {
        ForumTopic forumTopicEntity = new ForumTopic();
        forumTopicEntity.setName(forumTopic.getName());
        forumTopicEntity.setDescription(forumTopic.getDescription());
        forumTopicEntity.setAuthor(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
        return forumTopicRepository.save(forumTopicEntity);
    }

    @Transactional
    public void deleteForumTopic(Long id) {
        if (!forumTopicRepository.existsById(id)) {
            throw new ForumTopicNotFoundException("Forum Topic not found");
        }
        forumMessageService.deleteAllByForumTopicId(id);
        forumTopicRepository.deleteById(id);
    }

    @Transactional
    public ForumTopic editForumTopic(Long id, ForumTopicDto forumTopic) {
        ForumTopic forumTopicEntity = getForumTopicById(id);
        forumTopicEntity.setName(forumTopic.getName());
        forumTopicEntity.setDescription(forumTopic.getDescription());
        return forumTopicRepository.save(forumTopicEntity);
    }

    public boolean isForumTopicOwner(Long id, String username) {
        return getForumTopicById(id).getAuthor().getUsername().equals(username);
    }

    public boolean isTopicOwner(Long id, String name) {
        return forumTopicRepository.findById(id).get().getAuthor().getUsername().equals(name);
    }
}