package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/16/2015.
 */
@Controller
public class UserController {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/abc")
    @ResponseBody
    public String ok() {
        return "ok";
    }
}
