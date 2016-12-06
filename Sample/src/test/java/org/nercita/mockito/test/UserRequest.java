package org.nercita.mockito.test;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest  extends BaseRequest{
	
    @JsonProperty("id")  
    @NotNull(message = "id param is null")  
    @Min(value = 1, message = "id param must be great or equal than \\{{value}\\}") // 4.3. Message interpolation -《JSR 303: Bean Validation》  
    protected long id;  
    
    
    @JsonProperty("name")  
    @NotNull(message = "name param is null")  
    @Size(min = 1, message = "name param is empty")  
    protected String userName; // 变量名与请求参数名不一样，在@RequestBody中用到  
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }

  
    public long getId() {  
        return id;  
    }  
  
    public void setId(long id) {  
        this.id = id;  
    } 
    
    
  
    @Override  
    public String toString() {  
        return "UserInfoRequest [userName=" + userName + ", id=" + id + "]";   
    }


}
