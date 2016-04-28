package com.adara.pixeldataengineui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
* This is a trivial controller which will serve our main page.
*
* @author YI ZHAO[yi.zhao@adara.com]
* */

@Controller
@RequestMapping("/")
public class IndexController {
    private static final Log LOG = LogFactory.getLog(IndexController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getIndexPage");
        return "index";
    }
}