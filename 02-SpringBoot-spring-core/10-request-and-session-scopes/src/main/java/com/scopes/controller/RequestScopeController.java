package com.scopes.controller;

import com.scopes.request.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestScopeController {

    @Autowired
    private RequestInfo requestInfo;

    @GetMapping("/request-info")
    public String requestInfoPage(Model model) {
        model.addAttribute("timestamp", requestInfo.getTimestamp());
        return "requestInfo"; // Thymeleaf template
    }
}
