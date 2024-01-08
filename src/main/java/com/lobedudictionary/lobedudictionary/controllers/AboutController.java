package com.lobedudictionary.lobedudictionary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lobedudictionary.lobedudictionary.data.AppInfo;

@RestController
public class AboutController {

    @Autowired
    private AppInfo info;

    @GetMapping("/about")
    public AppInfo about() {
        System.out.println("invoked");
        return info;
    }

}
