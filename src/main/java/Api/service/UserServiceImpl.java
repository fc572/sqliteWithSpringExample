package Api.service;

import Api.model.User;

public interface UserServiceImpl {

    User findById(long id);

    long findByName(String name);

    User saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    Iterable<User> findAllUsers();

    void deleteAllUsers();

    boolean isUserExist(User user);
}
