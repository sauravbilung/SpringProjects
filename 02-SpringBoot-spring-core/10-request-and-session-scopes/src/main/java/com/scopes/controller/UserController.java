package com.scopes.controller;

import com.scopes.model.User;
import com.scopes.session.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private SessionUser sessionUser; // Session-scoped bean (1 per user session)

    private static List<User> allowedUsers = List.of(
            new User("admin", "pass"),
            new User("jerry", "abcd"),
            new User("alice", "1234")
    );

    @GetMapping("/")
    public String showLoginPage(Model model) {
        // Add empty user object to the model for form binding
        model.addAttribute("user", new User());
        // Returning "login" tells Spring to render login.html using Thymeleaf view resolver
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        // @ModelAttribute binds form input (username, password) to the User object
        // HttpSession is automatically created if it doesn't exist
        // Stores username manually in the session
        boolean isValid = allowedUsers.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()));

        if (isValid) {
            session.setAttribute("username", user.getUsername());

            // Set username in Spring's session-scoped bean (auto-managed per session)
            sessionUser.setUsername(user.getUsername());

            // Redirect to profile page (prevents form resubmission on refresh)
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        // Retrieve username from traditional session and session-scoped bean
        String usernameFromSession = (String) session.getAttribute("username");
        String usernameFromBean = sessionUser.getUsername();

        if (usernameFromSession == null || usernameFromBean == null) {
            return "redirect:/";
        }

        model.addAttribute("username", usernameFromBean);
        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
