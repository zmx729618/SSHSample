package org.nercita.mockito.test;

public class TestUserService {
	 
    public String getUserName(long userId) {  
    	User user = new User(userId, "xiaoming");   
        return user != null ? user.getUserName() : "";  
    }  
  
  
    public Boolean updateUserName(long userId, String userName) {  
        try {
			new User(userId, userName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}    
        return true;  
    }


}
