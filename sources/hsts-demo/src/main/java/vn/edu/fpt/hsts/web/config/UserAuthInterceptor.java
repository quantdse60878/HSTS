/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/5/2015.
 */
package vn.edu.fpt.hsts.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthInterceptor.class);

    /**
     *
     */
    private static final List<String> ADDITION_IGNORED_PATHS = Arrays
            .asList(new String[]{"/bower/**",
                    "/js/**",
                    "/css/**",
                    "/img/**",
                    "/pages/**",
                    "/sign-up/**",
                    "text!/",
                    "/",
                    //* Error path
                    "/error/**",
                    //* Need solve error by use path /path haven't got menu right
                    "/path/**"});


    /**
     *
     */
    private Set<String> ignoredPatterns;

    /**
     *
     */
    private PathMatcher pathMatcher = new AntPathMatcher();

    /**
     *
     */
    private UrlPathHelper pathHelper = new UrlPathHelper();

    /**
     * The value read from application.properties file
     */
    @Value("${hsts.web.ignoredUrlPatterns}")
    private String ignoredUrlPatterns;

    @Autowired
    protected void buildIgnoredPatterns() {
        String ignoredPropStr = ignoredUrlPatterns;
        ignoredPatterns = new HashSet<String>(
                Arrays.asList(ignoredPropStr.split(",")));
        ignoredPatterns.addAll(ADDITION_IGNORED_PATHS);
    }

    /**
     * The {@link UserSession}.
     */
    @Autowired
    private UserSession userSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * If the url pattern in the ignore case
         * -> return true
         * Else -> return false and send redirect to /login
         */
        String lookupPath = this.pathHelper.getLookupPathForRequest(request);
        boolean shouldIgnored = false;
        for (String pattern : this.ignoredPatterns) {
            if (isMatched(pattern, lookupPath)) {
                shouldIgnored = true;
                break;
            }
        }
        if(!shouldIgnored && StringUtils.isEmpty(userSession.getUsername())) {
            response.sendRedirect("login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response,
                           final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            ModelMap modelMap = modelAndView.getModelMap();
            modelMap.addAttribute("user", userSession);
        }
    }

    private boolean isMatched(final String pattern, final String lookupPath) {
        if (pattern.equals(lookupPath)) {
            return true;
        }
        if (this.pathMatcher.match(pattern, lookupPath)) {
            return true;
        }
        if (!pattern.endsWith("/")
                && this.pathMatcher.match(pattern + "/", lookupPath)) {
            return true;
        }
        return false;
    }
}
