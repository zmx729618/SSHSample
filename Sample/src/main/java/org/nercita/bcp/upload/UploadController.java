package org.nercita.bcp.upload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadController {
	
	@RequestMapping("/upload/upload")
	public String uploadPage(){
		return "/upload/upload";
	}
	
	@RequestMapping("/upload/progress")
	public void uploadFile(HttpServletRequest request,HttpServletResponse response,
	                       @RequestParam("file") CommonsMultipartFile file) throws IOException {
		request.getRequestedSessionId();
	     response.setContentType("text/html");
	     response.setCharacterEncoding("GBK");
	     PrintWriter out;
	     boolean flag = false;
	     if (file.getSize() > 0) {
	          //文件上传的位置可以自定义
	         flag = FileUploadUtil.uploadFile(request,file);
	     }
	     out = response.getWriter();
	     if (flag == true) {
	        out.print("1");
	     } else {
	        out.print("2");
	     }
	}

}
