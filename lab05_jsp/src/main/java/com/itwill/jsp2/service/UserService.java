package com.itwill.jsp2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.User;
import com.itwill.jsp2.dto.UserSignInDto;
import com.itwill.jsp2.dto.UserSignUpDto;
import com.itwill.jsp2.repository.UserDao;

public class UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    private final UserDao userDao = UserDao.getInstance();
    
    // singleton
    private static UserService instance = null;
    
    private UserService() {}
    
    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }// end of UserService Singleton
    
    public boolean signUp(UserSignUpDto dto) {
        log.debug("signUp(dto={})",dto);
         
        int result = userDao.insert(dto.toUser());
        
        if(result == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public User signIn(UserSignInDto dto) {
        log.debug("signIn(dto={})", dto);
        /* userid와 password가 일치하면 User 객체, 그렇지 않으면 null를 리턴 */
        User user = userDao.selectByUserIdAndPassword(dto);
        
        log.debug("signIn User user = {}", user);
        
        return user;
    }
    
    
}/* end of UserService */
