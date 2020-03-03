package com.restbackend.controller;

import com.restbackend.model.Role;
import com.restbackend.model.User;
import com.restbackend.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController("/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> SomeInf(){
        return userService.getAllUsers();
    }


    @RequestMapping(value = "/users", method = RequestMethod.PUT, produces = "application/json")
    public void updateUser(@RequestBody @Validated User user){
        Set<Role> roles = user.getRoles();
        if (user.getRole().contains("user")) {
            roles.add(Role.user);
        }
        if (user.getRole().contains("admin")) {
            roles.add(Role.admin);
        }
        user.setRoles(roles);
        userService.updateUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes="application/json")
    public void addUser(@RequestBody @Validated User user){
       Set<Role> roles = user.getRoles();
        if (user.getRole().contains("user")) {
            roles.add(Role.user);
        }
        if (user.getRole().contains("admin")) {
            roles.add(Role.admin);
        }
        user.setRoles(roles);
        userService.addUser(user);
    }
    @RequestMapping(value = "/users", method = RequestMethod.DELETE, consumes="application/json")
    public void deleteUser( @RequestBody String dd) {
        userService.deleteUser(userService.getUserById(Long.parseLong(dd)));
         }
    @RequestMapping(value = "/users/autorize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestBody String email){
        String s = email.replaceAll("\"","");
        return userService.getUserByEmail(s);
    }
}
