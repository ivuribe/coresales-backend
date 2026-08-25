package com.coresales.service.user.auth.service;

import com.coresales.service.user.auth.model.User;
import com.coresales.service.user.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepsitory;

    public UserService(UserRepository userRepository){
        this.userRepsitory = userRepository;
    }

    public List<User> listar(){
        return userRepsitory.findAll();
    }

    public Optional<User> buscarPorId(Long id){
        return userRepsitory.findById(id);
    }

    public User crear(User user){
        return userRepsitory.save(user);
    }

    public User actualizar(User user){
        return userRepsitory.save(user);
    }

    public void delete(Long id){
        userRepsitory.deleteById(id);
    }

    public Optional<User> findByUsername(String username){
       return  userRepsitory.findByUsername(username);
    }
}
