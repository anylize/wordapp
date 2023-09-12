package com.sangeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


//跳转页面

@Controller
@RequestMapping("/toPage")
public class PageController {


    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page) {
        return page;
    }
}
