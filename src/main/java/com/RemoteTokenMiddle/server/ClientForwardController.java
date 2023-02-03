package com.RemoteTokenMiddle.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientForwardController {
    // Match all paths 
    @GetMapping(value = "/**/{[path:[^\\.]*}")
    public String forward() { 
        return "forward:/";
    }
}
