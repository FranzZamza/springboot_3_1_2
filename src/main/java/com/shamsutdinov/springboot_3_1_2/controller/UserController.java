package com.shamsutdinov.springboot_3_1_2.controller;

import com.shamsutdinov.springboot_3_1_2.model.User;
import com.shamsutdinov.springboot_3_1_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @GetMapping("/{id}")
    private String show(@PathVariable("id") Long id, Model model) {
        if (userService.getUserById(id).isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userService.getUserById(id));
            return "/show";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        if (userService.getUserById(id).isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userService.getUserById(id));
            return "/edit";
        }
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {
            userService.delete(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}