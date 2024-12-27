package com.vakin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vakin.todolist.model.ForumTopic;

public interface ForumTopicRepository extends JpaRepository<ForumTopic, Long> {

}
