package ajou.gram.moim.controller;

import ajou.gram.moim.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/validate/{id}")
    public long validateId(@PathVariable("id") long id) {
        return userService.validateDuplicateUser(id).getId();
    }
}
