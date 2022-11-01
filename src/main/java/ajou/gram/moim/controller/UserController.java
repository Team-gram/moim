package ajou.gram.moim.controller;

import ajou.gram.moim.Service.UserService;
import ajou.gram.moim.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public int validateId(@PathVariable("id") long id) {

        return 1;
    }
}
