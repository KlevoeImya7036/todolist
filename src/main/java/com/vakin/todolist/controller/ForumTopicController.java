package com.vakin.todolist.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vakin.todolist.dto.ForumMessageDto;
import com.vakin.todolist.dto.ForumTopicDto;
import com.vakin.todolist.exception.ForumTopicNotFoundException;
import com.vakin.todolist.model.ForumTopic;
import com.vakin.todolist.model.User;
import com.vakin.todolist.service.ForumMessageService;
import com.vakin.todolist.service.ForumTopicService;
import com.vakin.todolist.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("forum")
public class ForumTopicController {
    
    @Autowired
    private ForumTopicService forumTopicService;

    @Autowired
    private ForumMessageService forumMessageService;

    @Autowired
    private UserService userService;


    @GetMapping
    public String showForumTopicList(Model model) {
        model.addAttribute("topics", forumTopicService.getAllForumTopics());
        return "forumTopicList";
    }

    @GetMapping("{id}")
    public String showForumThreadDetails(@PathVariable Long id, Model model, Principal principal) {
        try {
            model.addAttribute("isForumTopicOwner", forumTopicService.isForumTopicOwner(id, principal.getName()));
            model.addAttribute("topic", forumTopicService.getForumTopicById(id));
            model.addAttribute("forumMessages", forumMessageService.getAllByForumTopic(forumTopicService.getForumTopicById(id)));
            return "forumTopicDetails";
        } catch (ForumTopicNotFoundException e) {
            return "redirect:/forum";
        }
    }

    @GetMapping("create")
    public String showTopicCreationForm(Model model) {
        try {
            model.addAttribute("topic", new ForumTopicDto());
            return "forumTopicCreate";
        } catch (Exception e) {
            return "redirect:/forum";
        }
    }

    @PostMapping("create")
    public String createTopic(@Valid @ModelAttribute("topic") ForumTopicDto forumTopicDto, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "forumTopicCreate";
        }

        try {
            forumTopicService.saveForumTopic(forumTopicDto, principal.getName());
            return "redirect:/forum";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при создании темы: " + e.getMessage());
            return "forumTopicCreate";
        }
    }

    @GetMapping("{id}/edit")
    public String showEditTopicForm(@PathVariable Long id, Model model, Principal principal) {
        try {
            if (!forumTopicService.isTopicOwner(id, principal.getName())) {
                return "redirect:/forum";
            }

            ForumTopicDto topic = new ForumTopicDto();
            model.addAttribute("topicIdToEdit", id);
            model.addAttribute("topic", new ForumTopicDto());
            return "forumTopicEdit";
        } catch (ForumTopicNotFoundException e) {
            return "redirect:/forum";
        }
    }

    @PostMapping("{id}/edit")
    public String editTopic(@PathVariable Long id, @Valid @ModelAttribute("topic") ForumTopicDto forumTopicDto, BindingResult bindingResult, Model model, Principal principal) {

        if (!forumTopicService.isTopicOwner(id, principal.getName())) {
            return "redirect:/forum";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("topicIdToEdit", id);
            return "forumTopicEdit";
        }

        try {
            forumTopicService.editForumTopic(id, forumTopicDto);
            return "redirect:/forum";
        } catch (ForumTopicNotFoundException e) {
            return "redirect:/forum";
        }
    }

    @PostMapping("{id}/delete")
    public String deleteTopic(@PathVariable Long id, Principal principal, Model model) {
        if (!forumTopicService.isTopicOwner(id, principal.getName())) {
            return "redirect:/forum";
        }
        try {
            forumTopicService.deleteForumTopic(id);
            return "redirect:/forum";
        } catch (ForumTopicNotFoundException e) {
            return "redirect:/forum";
        }
    }

    @GetMapping("{id}/messages/create")
    public String showMessageCreationForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("message", new ForumMessageDto());
            model.addAttribute("topic", forumTopicService.getForumTopicById(id));
            return "messageCreate";
        } catch (ForumTopicNotFoundException e) {
            return "redirect:/forum";
        }
    }

        @PostMapping("{id}/messages/create")
    public String createMessage(@PathVariable Long id, @Valid @ModelAttribute("message") ForumMessageDto forumMessageDto, BindingResult bindingResult, Model model, Principal principal) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("topic", forumTopicService.getForumTopicById(id));
                return "messageCreate";
            }

            User user = userService.getUserByUsername(principal.getName());
            ForumTopic topic = forumTopicService.getForumTopicById(id);
            forumMessageService.save(forumMessageDto, user, topic);
            return "redirect:/forum/{id}";
        } catch (ForumTopicNotFoundException e) {
            return "redirect:/forum";
        }
    }
}
