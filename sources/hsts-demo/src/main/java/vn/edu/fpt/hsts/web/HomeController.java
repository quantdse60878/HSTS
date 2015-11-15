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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.service.DataValidationService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Home controller, for processing common mapping, redirect, etc.
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * The {@link UserSession}
     */
    @Autowired
    private UserSession userSession;

    /**
     * The {@link DataValidationService}.
     */
    @Autowired
    private DataValidationService dataValidationService;

    @RequestMapping(value = "/")
    public String home() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (StringUtils.isEmpty(userSession.getUsername())) {
               return "redirect:/login";
            }
            return "home";
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "home")
    public String homePage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (StringUtils.isEmpty(userSession.getUsername())) {
                return "redirect:/login";
            }
            return "home";
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

    @RequestMapping(value = "/validateData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean validateData(final HttpServletRequest request) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final boolean result = dataValidationService.validateRequestParam(request);
            return result;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/validateStr", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean validateString(final HttpServletRequest request) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final boolean result = dataValidationService.validateRequestString(request);
            return result;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/currentUTCTime", method = RequestMethod.GET)
    @ResponseBody
    public Long currentUTCTime() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            return cal.getTimeInMillis();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
