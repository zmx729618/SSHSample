package org.nercita.mockito.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;

@WebAppConfiguration("src/test/java") // 集成Web应用上下文  
public abstract class AbstractControllerTestNGTest extends AbstractTestNGTest {  
  
    /** 
     * MVC mock 
     */  
    protected MockMvc mockMvc;  
      
    /** 
     * Gets the tested controller. 
     *  
     * @return the controller that is tested 
     */  
    protected abstract Object getController();  
      
    /** 
     * Setups the tested controller in MVC Mock environment. 
     */  
    @BeforeClass(alwaysRun = true)  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.getController()).build();  
    }  
      
    /** 
     * Mocks the GET request. 
     *  
     * @param url 
     * @param params 
     * @param expectedContent 
     * @throws Exception 
     */  
     protected void getMock(String url, Object[] params, String expectedContent) throws Exception {  
        // 2. 构造GET请求  
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url, params);  
          
        this.jsonRequestMock(requestBuilder, expectedContent);  
    }  
      
    /** 
     * Mocks the POST request. 
     *  
     * @param url 
     * @param paramsJson 
     * @param expectedContent 
     * @throws Exception 
     */  
    protected void postMock(String url, String paramsJson, String expectedContent) throws Exception {  
        // 2. 构造POST请求  
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders  
                .post(url)  
                .content(paramsJson); // 设置request body请求体，服务于"@RequestBody"                   
                this.jsonRequestMock(requestBuilder, expectedContent);  
    }  
      
    /** 
     * Mocks the request for "application/json;charset=UTF-8" media type (Content-Type). 
     *  
     * @param requestBuilder 
     * @param expectedContent 
     * @throws Exception 
     */  
    private void jsonRequestMock(MockHttpServletRequestBuilder requestBuilder, String expectedContent) throws Exception {  
        // 2. 设置HTTP请求属性  
        requestBuilder.contentType(MediaType.APPLICATION_JSON)  
                .accept(MediaType.APPLICATION_JSON)  
                .characterEncoding(CharEncoding.UTF_8);            
        // 3. 定义期望响应行为  
        this.mockMvc.perform(requestBuilder)  
                .andDo(print()) // 打印整个请求与响应细节  
                .andExpect(status().isOk())  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  
                .andExpect(content().string(expectedContent)); // 校验是否是期望的结果  
                  
       }  

}