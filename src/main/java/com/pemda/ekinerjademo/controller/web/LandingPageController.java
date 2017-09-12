package com.pemda.ekinerjademo.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by bagus on 09/09/17.
 */
@Controller
public class LandingPageController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LandingPageController.class);

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }

}
