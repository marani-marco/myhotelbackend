package com.marco.myhotelbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marco.myhotelbackend.models.User;
import com.marco.myhotelbackend.repository.UserRepository;
import com.marco.myhotelbackend.utilities.SecurityManagement;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User addOrEdit(User user) throws Exception{
		
		if(user.getSalt().isEmpty()) 
			userRepository.save(signUp(user));	
		else		
			userRepository.save(user);
		
		return user;
	}
	
	public void activateDeactivate(User user, int isActive) throws Exception {
		
		if(isActive == 1){
			user.setIsActive(1);
			addOrEdit(user);
		} else {
			user.setIsActive(0);
			addOrEdit(user);
		}
		
	}
	
	public User login(String username, String password) throws Exception {
		
		User user = userRepository.getUserByUsername(username);
		
		if(user == null) {
			throw new Exception();
		} else {
            String salt = user.getSalt();
            String calculatedHash = SecurityManagement.getEncryptedString(password, salt);
            if (calculatedHash.equals(user.getPassword())) {
                return user;
            } else {
            	throw new Exception();
            }
		}
	    
	}
	
	private User signUp(User user) throws Exception {
		
        String salt = SecurityManagement.getNewSalt();
        String encryptedPassword = SecurityManagement.getEncryptedString(user.getPassword(), salt);

        user.setPassword(encryptedPassword);
        user.setSalt(salt);
        
        return user;
        
	}
	
	
	
	
	
	
	
}
