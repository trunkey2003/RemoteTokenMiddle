package com.RemoteTokenMiddle.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docs")
public class SwaggerForwardController {
    @GetMapping
    public String index() {
        return "redirect:/swagger-ui/index.html";
    }
}
