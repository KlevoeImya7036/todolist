package com.vakin.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vakin.todolist.model.ForumMessage;
import com.vakin.todolist.model.ForumTopic;

public interface ForumMessageRepository extends JpaRepository<ForumMessage, Long> {
    public List<ForumMessage> findByForumTopic(ForumTopic forumTopic);
    public void deleteByForumTopicId(Long forumTopicId);
}
