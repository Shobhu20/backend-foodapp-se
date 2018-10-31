package com.windsor.foodapp.foodapp.service;

import com.windsor.foodapp.foodapp.dao.UserDao;
import com.windsor.foodapp.foodapp.util.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    public String authenticateUserAndGetToken(String email, String password) throws Exception {
        try {
            Boolean result = userDao.authenticateUserAndGetToken(email, password);
            if(!result)
                throw new Exception("wrong password");
            else {
                String token = genAndSaveToken(email);
                return token;
            }

        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private String genAndSaveToken(String email) {
        //todo : save this for user
        return RandomStringUtils.getRandomString(10);
    }
}
