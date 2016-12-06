package org.nercita.mockito.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse extends BaseResponse{
    @JsonProperty("name")  
    protected String name = "";  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
    
    
    @JsonProperty("ret")  
    protected int result;  
    
    @JsonProperty("ret_msg")  
    protected String resultMessage;  
  
 
  
    public int getResult() {  
        return result;  
    }  
  
    public void setResult(int result) {  
        this.result = result;  
    }  
  
    public String getResultMessage() {  
        return resultMessage;  
    }  
  
    public void setResultMessage(String resultMessage) {  
        this.resultMessage = resultMessage;  
    }  
  
    @Override  
    public String toString() {  
        return "UserResultResponse [result=" + result + ", resultMessage="  
                + resultMessage + "]";  
    } 



}
