package com.itwill.jsp2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.dto.UserSignUpDto;

public class UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    // singleton
    private static UserService instance = null;
    
    private UserService() {}
    
    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }//end of UserService Singleton
    
    public boolean signUp(UserSignUpDto dto) {
        // 
        
        return false;
    }
    
    
}// end of UserService
