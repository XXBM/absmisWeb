package com.absmis.service.authority;


import com.absmis.domain.authority.User;
import com.absmis.repository.authority.UserRepository;
import com.absmis.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends BasicService<User,Long> {
    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
    public User findById(Long id){
        return userRepository.findOne(id);
    }

    public  User addUser(User user){
        return this.userRepository.save(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}