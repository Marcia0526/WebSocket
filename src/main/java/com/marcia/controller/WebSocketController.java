package com.marcia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class WebSocketController {
    @RequestMapping("/websocket/{username}")
    public String webSocket(@PathVariable String username, Model model) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        try {
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username", username);
            return "websocket";
        } catch (Exception e) {
            logger.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }
    }
//    @RequestMapping("/websocket")
//    public String webSocket(Model model) {
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//        try {
//            logger.info("跳转到websocket的页面上");
//            model.addAttribute("username", "jingcai");
//            return "websocket";
//        } catch (Exception e) {
//            logger.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
//            return "error";
//        }
//    }

}
