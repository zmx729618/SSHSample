package org.nercita.bcp.excel.web;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.nercita.bcp.excel.service.TestService;
import org.nercita.bcp.excel.util.Constant;
import org.nercita.bcp.excel.util.ExportUtil;
import org.nercita.bcp.excel.util.ExportUtils;
import org.nercita.bcp.excel.util.ImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class TestController {
	@Autowired 
	private TestService testService;

	@RequestMapping(value = "/test/index")
	public String indexPage(ModelMap modelMap){
		 

		return "/test/index";
	}	
	
	@RequestMapping(value = "/test/export")
	public String configExport(ModelMap modelMap){
		 
		return "/test/export";
	}
	
	/**
	 * 数据导出
	 */
	@RequestMapping(value="/test/exportXLS")
	public void exportXLS(HttpServletRequest request, HttpServletResponse response, String table, String header) {
		String[] field = header.split(",");
		List<String> listHeadText=new ArrayList<String>(); 
		List<String> listHeadName=new ArrayList<String>(); 
		for(int i=0;i<field.length;i++){
			String[] s = field[i].split("@");
			listHeadName.add(s[0]);
			listHeadText.add(s[1]);
		}
		table="TestExImport";
		String[][] layouts=testService.getTableData(table, listHeadName, null);
        Map<String, Object> beanParams = new HashMap<String, Object>();
        beanParams.put("header", listHeadText);
        beanParams.put("arr", layouts);
        beanParams.put("title", "导出数据");
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        beanParams.put("exportdate", df.format(new Date()));
        String path = request.getSession().getServletContext().getRealPath("/template");
        path = path + "/analysis/test.xls";
        
        path = "D:\\apache-tomcat-7.0.62\\webapps\\Sample-pbc\\template\\analysis\\test.xls";
        System.out.println(path);
        ExportUtil.writeResponse("导出数据", request, response, ExportUtils.EXCEL_2003, path, beanParams);        
	}

	
	/**
	 * 导入
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/test/import")
	public String import1(ModelMap modelMap){
		 
		return "/test/import";
	}	
	
	@RequestMapping(value = "/test/importXLS")
	@ResponseBody
	public String importXLS(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, String header){
		String message = "ok";
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
			if ("application/octet-stream".equals(file.getContentType())){
				file.getInputStream().close();
				message="fileIsOpenning";
			}
			else if (ArrayUtils.contains(Constant.allowedContentTypes, file.getContentType())) {
				int col=header.split(",").length;	//列数
				String[][] importdata = ImportUtil.importFile(file,0,1,col);				
				//相关操作
				request.getSession().setAttribute("importdata", importdata); 
				request.getSession().setAttribute("header", header); 
				
				file.getInputStream().close();
			}
		}
		catch (Exception e){
			message="error";
			e.printStackTrace();
		}
		return message;
	}
	
	@RequestMapping(value = "/test/importView")
	public String importView(ModelMap modelMap,HttpServletRequest request){
		String[][] importdata = (String[][]) request.getSession().getAttribute("importdata");
		String header= (String) request.getSession().getAttribute("header");
		List<String> listHeadText=new ArrayList<String>(); 
		if(header!=null&&!"".equals(header)){
			String[] field = header.split(",");			 
			for(int i=0;i<field.length;i++){
				String[] s = field[i].split("@");				
				listHeadText.add(s[1]);
			}
		}
		
		modelMap.addAttribute("importdata", importdata);
		modelMap.addAttribute("listHeaderText", listHeadText);		
		return "/test/importView";
	}
}
