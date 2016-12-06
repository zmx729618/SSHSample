package org.nercita.bcp.ztree.web;

import java.util.List;

import javax.annotation.Resource;

import org.nercita.bcp.ztree.service.InputsClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 投入品领用记录顶层逻辑
 *
 */
@Controller
public class InputsDrawController {
	@Resource(name="inputsClassService")
	private InputsClassService inputsClassService;
	
	@RequestMapping("/ztree")
	public String query(ModelMap modelMap) {
		List<Object[]> inputsClassList = inputsClassService.findAllLevelInputsClasses();
		StringBuffer strBuffer = new StringBuffer("[");
		strBuffer.append("{\"id\":\"1\",\"name\":\"根目录\",\"pId\":\"0\",\"open\":\"true\",\"nocheck\":\"true\"}");
		
		for (Object[] objs : inputsClassList) {
			if (objs[2].equals(1)) {
				if(objs[0] != null && objs[1] != null){
					strBuffer.append(",{\"id\":\"").append(objs[0].toString())
					.append("\",\"name\":\"").append(objs[1].toString())
					.append("\",\"pId\":\"1\",")
					.append("\"level\":\"1\",");
					strBuffer.append("\"nocheck\":\"true\"}");
				}	
			} else if (objs[2].equals(2)) {
				if(objs[0] != null && objs[1] != null && objs[3] != null){
					strBuffer.append(",{\"id\":\"").append(objs[0].toString())
					.append("\",\"name\":\"").append(objs[1].toString())
					.append("\",\"pId\":\"").append(objs[3].toString())
					.append("\",\"level\":\"2\",")
					.append("\"nocheck\":\"true\"");
					strBuffer.append("}");
				}
			} else if (objs[2].equals(3)) {
				if(objs[0] != null && objs[1] != null && objs[3] != null){
					strBuffer.append(",{\"id\":\"").append(objs[0].toString())
					.append("\",\"name\":\"").append(objs[1].toString())
					.append("\",\"pId\":\"").append(objs[3].toString())
					.append("\",\"level\":\"3");
					strBuffer.append("\"}");
				}
			}
		}
		strBuffer.append("]");
		modelMap.addAttribute("tree", strBuffer.toString());
		return "/ztree";
	}
}
