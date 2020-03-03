package com.front.controller;

import com.front.model.Role;
import com.front.restclient.RestClient;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

   RestClient restClient;

    public UserController(RestClient restClient) {
        this.restClient = restClient;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String allUsers(ModelAndView modelAndView) {
        return "admin-page";
    }

    @RequestMapping("/login")
    public ModelAndView getLogin(Authentication authentication, HttpServletRequest request, ModelAndView model, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        if (authentication != null) {
            if (authentication.getAuthorities().contains(Role.admin)) {
                httpServletResponse.sendRedirect("/admin");
            } else {
                httpServletResponse.sendRedirect("/user");
            }
        }
        if (request.getParameterMap().containsKey("error")) {
            model.setViewName("user-login");
            model.addObject("status", "Не верный Email или Password");
            return model;
        }
        model.setViewName("user-login");
        return model;
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView home(Authentication authentication, ModelAndView model) {
        model.addObject("user", restClient.getUserToAutorithe(authentication.getName()));
        model.setViewName("user-page");
        return model;
    }

}
