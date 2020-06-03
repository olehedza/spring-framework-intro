package spring.intro.service.impl;

import org.springframework.stereotype.Service;
import spring.intro.dao.UserDao;
import spring.intro.model.User;
import spring.intro.service.UserService;
import spring.intro.util.HashUtil;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil
                .getPasswordDigest(user.getPassword(), user.getSalt()));
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
