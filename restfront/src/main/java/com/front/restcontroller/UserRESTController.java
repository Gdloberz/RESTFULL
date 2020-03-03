package com.front.restcontroller;

import com.front.model.UserDto;
import com.front.restclient.RestClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRESTController {

    private RestClient restClient;

    public UserRESTController(RestClient restClient) {
        this.restClient = restClient;
    }

    @CrossOrigin
    @RequestMapping(value = "/admin/users",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll(){
        return restClient.getAllUser();
    }

    @CrossOrigin
    @RequestMapping(value = "/admin/users", method = RequestMethod.PUT)
    public void updateUser(@ModelAttribute("userdto") UserDto userDto){
        restClient.updateUser(userDto);

    }
    @CrossOrigin
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public void addUser(@ModelAttribute("userdto") UserDto userDto){
       restClient.addUser(userDto);
    }
    @CrossOrigin
    @RequestMapping(value = "/admin/users", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam Long id){
        restClient.deleteUser(id);
    }

}
