package com.tangcheng.wx.mpdemo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

    @RequestMapping("/test")
    public String init() {
        return "11148622114871172443";
    }

}
