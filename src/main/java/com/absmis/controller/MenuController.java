package com.absmis.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MenuController {
    //菜单页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }
    @RequestMapping("/index")
    public String index() {
        System.out.print("controller");
        return "index";
    }
}
