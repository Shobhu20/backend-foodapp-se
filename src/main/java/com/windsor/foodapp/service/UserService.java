package com.windsor.foodapp.service;

import com.windsor.foodapp.dao.UserDao;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.util.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    public Map<String, Object> authenticateUserAndGetToken(String email, String password) throws Exception {
        try {
            ClientUser clientUser = userDao.authenticateUserAndGetToken(email, password);
            if (!clientUser.getPassword().equals(password))
                throw new Exception("wrong password");
            else {
                clientUser.setPassword("N/A");
                Map<String, Object> map = new HashMap<>();
                //List<String> details= new ArrayList<>();
                String token = genAndSaveToken(email);
                map.put("token", token);
                map.put("user", clientUser);

                return map;
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private String saveName(String email){
        String name = userDao.getNameForEmail(email);
        return name;
    }

    private String genAndSaveToken(String email) {
        String token = userDao.getTokenForEmail(email);

        if (token != null)
            return token;
        token = RandomStringUtils.getRandomString(10);
        userDao.saveTokenForEmail(email, token);
        return token;
    }

    public void registerUser(ClientUser clientUser) throws Exception {
        try {
            userDao.registerUser(clientUser);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void logout(String email, String token) throws Exception{
        try {
            userDao.logout(email,token);
        } catch (Exception e) {
            throw new  Exception(e.getMessage());
        }
    }

    public Boolean validateToken(String email, String token) {
        String tokenFromDb = userDao.getTokenForEmail(email);
        if (tokenFromDb != null && tokenFromDb.equals(token))
            return true;
        else return false;
    }

    public ClientUser getUserForEmail(String email) {
        return userDao.getUserForEmail(email);
    }

    public void updateProfile(String email, String firstName, String lastName, String phoneNum, String password) throws Exception {
        userDao.updateProfile(email, firstName, lastName, phoneNum, password);
    }
}
