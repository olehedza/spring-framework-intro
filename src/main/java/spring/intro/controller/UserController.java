package spring.intro.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final String SUCCESS_MESSAGE = "4 new users was successfully added";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponseDto get(@PathVariable Long userId) {
        return new UserResponseDto(userService.getById(userId));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/inject", method = RequestMethod.GET)
    public String inject() {
        userService.add(new User("elonmusk", "19710628"));
        userService.add(new User("linustorvalds", "19691228"));
        userService.add(new User("dennisritchie", "19410909"));
        userService.add(new User("guidovanrossum", "19560131"));
        return SUCCESS_MESSAGE;
    }
}
