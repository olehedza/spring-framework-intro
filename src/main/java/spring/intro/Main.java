package spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("elonmusk", "19710628"));
        userService.add(new User("linustorvalds", "19691228"));
        userService.add(new User("dennisritchie", "19410909"));
        userService.add(new User("guidovanrossum", "19560131"));

        userService.listUsers().forEach(System.out::println);
    }
}
