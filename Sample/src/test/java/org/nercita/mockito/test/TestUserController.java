package org.nercita.mockito.test;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
public class TestUserController {
	
    @Autowired  
    private TestUserService testUserService;  
      
      
    @RequestMapping(value = "/user/getUserName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)  
    @ResponseBody  
    public UserResponse getUserName(@Valid UserRequest userRequest) {  
        long userId = userRequest.getId();  
        String userName = testUserService.getUserName(userId);            
        UserResponse response = new UserResponse();  
        if (!StringUtils.isEmpty(userName)) {  
            response.setName(userName);  
        }  
          
        return response;  
    }  
      
    @RequestMapping(value = "/user/updateUserName", method = RequestMethod.POST,  
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)  
    @ResponseBody  
    public UserResponse updateUserName(@Valid @RequestBody UserRequest userRequest) { // JSON request body map  
        UserResponse response = new UserResponse();            
        long userId = userRequest.getId();  
        String userName = userRequest.getUserName();  
        Boolean result = this.testUserService.updateUserName(userId, userName);  
        if (!result ) {  
            response.setResult(0);  
            response.setResultMessage("update operation is fail");  
        }  
          
        return response;  
    }


}
