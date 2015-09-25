/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.common.IConsts;

/**
 * Home controller, for processing common mapping, redirect, etc.
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/")
    public ModelAndView home() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("home");
            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/500")
    public ModelAndView internalServerError() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("500");
            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/404")
    public ModelAndView pageNotFound() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("404");
            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
