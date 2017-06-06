package Api.service;

import Api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Api.repository.UserRepository;

@Service("userService")
public class UserService implements UserServiceImpl{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public long findByName(String name) {
        for(User user : userRepository.findAll()){
            if(user != null && user.getName().equalsIgnoreCase(name)){
                return user.getId();
            }
        }
        return -1;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(User user) {
        return (findByName(user.getName()) > 0);
    }

    @Override
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}
