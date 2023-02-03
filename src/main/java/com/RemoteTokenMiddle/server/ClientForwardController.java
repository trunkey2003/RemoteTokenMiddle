package com.RemoteTokenMiddle.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientForwardController {
    
    // Get mapping all requests to the root path except for the swagger-ui.html
    @GetMapping(value = {"/{path:^(?!swagger-ui).*$}/**/{path:[^\\.]*}"})
    public String forward() { 
        return "forward:/";
    }
}
