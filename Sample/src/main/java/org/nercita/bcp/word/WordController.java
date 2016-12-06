package org.nercita.bcp.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nercita.bcp.user.domain.User;
import org.nercita.bcp.utils.TempltUtil;
import org.nercita.bcp.word.util.WordExportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WordController {
	
	 private boolean getData(HttpServletResponse response,HttpServletRequest request,Map<String,Object> dataMap) {  
   	        
		    List<User> userList = new ArrayList<User>();
   	        for(int i=0;i<10;i++){
   	        	User u = new User();
   	        	u.setName("ZhangSan"+i);
   	        	u.setRealName("张三"+i);
   	        	u.setCompany("xx公司"+i);
                u.setPhone("130xxxxxxxx"+i); 
                userList.add(u);
   	        }
	        dataMap.put("title", "用户信息");  
	        dataMap.put("userList", userList);
	        dataMap.put("attention", "请注意确保所有信息的正确性");
	        TempltUtil.toPreview(request, TempltUtil.WORD_TEMPLATE, dataMap); 
	        return true;
	    
	     }  
	
    @RequestMapping("/test/word/export1")
    public void excWord(HttpServletResponse response,HttpServletRequest request) throws IOException{
			
    	try { 
    		Map<String,Object> dataMap = new HashMap<String,Object>();	    		
    		if (getData(response,request,dataMap)) { 
	    		File previewFile = new File(request.getSession().getServletContext().getRealPath(TempltUtil.PREVIEW_DOC)); 
	    		InputStream is = new FileInputStream(previewFile); 
	    		response.reset(); 
	    		response.setContentType("application/vnd.ms-word;charset=UTF-8"); 
	    		response.addHeader("Content-Disposition","attachment; filename=\"" + TempltUtil.PREVIEW_DOC + "\""); 
	    		byte[] b = new byte[1024]; 
	    		int len; 
	    		while ((len=is.read(b)) >0) { 
	    		   response.getOutputStream().write(b,0,len); 
	    		} 
	    		is.close(); 
	    		response.getOutputStream().flush(); 
	    		response.getOutputStream().close(); 
    		} 
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    	} 
    }
    
    
    @RequestMapping("/test/word/export2")
    public void exportWord(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	Map<String, Object> beanParams = new HashMap<String, Object>();	
    	List<User> userList = new ArrayList<User>();
        for(int i=0;i<10;i++){
        	User u = new User();
        	u.setName("ZhangSan"+i);
        	u.setRealName("张三"+i);
        	u.setCompany("xx公司"+i);
        u.setPhone("130xxxxxxxx"+i); 
        userList.add(u);
        }
        beanParams.put("title", "用户信息");  
        beanParams.put("userList", userList);
        beanParams.put("attention", "请注意确保所有信息的正确性"); 
        
        WordExportUtil.writeResponse(request, response, WordExportUtil.WORD_2003, "用户信息列表导出", "templateFile.ftl", beanParams);
    }

}
