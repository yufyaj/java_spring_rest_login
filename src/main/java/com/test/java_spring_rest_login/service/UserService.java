package com.test.java_spring_rest_login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.java_spring_rest_login.repository.UserRepository;
import com.test.java_spring_rest_login.model.UserSessionEntity;
import com.test.java_spring_rest_login.model.UserEntity;
import com.test.java_spring_rest_login.model.UserInterface;
import com.test.java_spring_rest_login.exception.NoUserException;
import com.test.java_spring_rest_login.exception.MissMatchPassword;

@Service
public class UserService {
    @Autowired
    UserRepository userRepos;
    @Autowired
    UserSessionService sessionService;

    public String doLogin(UserInterface reqUser) throws NoUserException, MissMatchPassword{
        Optional<UserEntity> findUser = userRepos.findByUserId(reqUser.getUserId());
        if (findUser.isEmpty()){
            throw new NoUserException();
        }
        if (matchPassword(findUser.get(), reqUser)) {
            UserSessionEntity session = sessionService.create(reqUser.getUserId());
            return session.getSessionId();
        } else {
            throw new MissMatchPassword();
        }
    }

    private boolean matchPassword(UserInterface user1, UserInterface user2){
        return user1.getPassword().equals(user2.getPassword());
    }
}
