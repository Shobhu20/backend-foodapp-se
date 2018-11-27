package com.windsor.foodapp.service;

import com.windsor.foodapp.dao.UserDao;
import com.windsor.foodapp.enums.CLIENT_ROLE;
import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.util.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Resource
    UserDao userDao;


    public Map<String, Object> authenticateUserAndGetToken(String email, String password) throws Exception {
        try {
            Map<String,Object> user = new HashMap<>();
            user = userDao.authenticateUserAndGetToken(email, password);
            if (!passwordEncoder.matches(password, user.get("password").toString()))
                throw new Exception("wrong password");
            else {
                CLIENT_ROLE userRole = CLIENT_ROLE.getByValue(Integer.parseInt(user.get("user_role").toString()));
                user.replace("password","N/A");
                user.put("user_role", userRole.toString());
                user.put("user_status", CLIENT_STATUS_ENUM.getByValue(Integer.parseInt(user.get("user_status").toString())));
                String token = genAndSaveToken(email);
                user.put("token", token);
                if(userRole == CLIENT_ROLE.VENDOR) {
                    int restaurantId = userDao.getRestaurantIdForVendor(Integer.parseInt(user.get("id").toString()));
                    user.put("restaurant_id", restaurantId);
                }

                return user;
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
            String encryptedPassword = encryptPassword(clientUser.getPassword());
            clientUser.setPassword(encryptedPassword);
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
        try {
            if(!StringUtils.isEmpty(password)) {
                String newEncryptedPassword = encryptPassword(password);
                userDao.updateProfile(email, firstName, lastName, phoneNum, newEncryptedPassword);
            }
            userDao.updateProfile(email, firstName, lastName, phoneNum, null);
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }

    private String encryptPassword(String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        return encryptedPassword;
    }

}
