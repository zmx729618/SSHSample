package org.nercita.bcp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;


@Controller
public class UITestController {
	
	private static Logger logger = LogManager.getLogger(UITestController.class);
	
    @RequestMapping("/echarts")
    public String echarts(ModelMap map){
    	
    	    logger.info("测试Echarts");
    	    JSONArray jsonArray = new JSONArray();
    	    jsonArray.add("衬衫");
    	    jsonArray.add("羊毛衫");
    	    jsonArray.add("雪纺衫");
    	    jsonArray.add("裤子");
    	    jsonArray.add("高跟鞋");
    	    jsonArray.add("袜子");  	   
            map.addAttribute("data", jsonArray);
            return "/echarts";

    }
    
    
    
    @RequestMapping("/cropper")
    public String cropper(ModelMap map){
    	
    	    logger.info("测试Cropper");
            map.addAttribute("data", "cropper");
            return "/cropper";

    }
    
    
    @RequestMapping("/validationEngine")
    public String validationEngine(ModelMap map){
    	
    	    logger.info("测试-validationEngine");
            map.addAttribute("data", "validationEngine");
            return "/validationEngine";

    }
    
    
    @RequestMapping("/kendo/grid")
    public String kendoGrid(ModelMap map){
    	
    	    logger.info("测试-kendo-grid");
            map.addAttribute("data", "validationEngine");
            return "/kendo/grid";

    }
    
    
    @RequestMapping("/jqgrid")
    public String jqgrid(ModelMap map){
    	
    	    logger.info("测试-Jqgrid");
            map.addAttribute("data", "jqgrid");
            return "/jqgrid";

    }
    
    
    @RequestMapping("/jqbuilder")
    public String jqbuilder(ModelMap map){
    	
    	    logger.info("测试-Jqbuilder");
            map.addAttribute("data", "jqbuilder");
            return "/jqbuilder";

    }
    
    
    
	

}
