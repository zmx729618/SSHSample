package org.nercita.bcp.ztree.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.nercita.bcp.ztree.dao.InputsClassDao;
import org.nercita.bcp.ztree.domain.InputsClass;
import org.nercita.core.orm.IBaseDao;
import org.nercita.core.service.BaseService;

/**
 * 投入品分类 业务类
 *
 */
@Service("inputsClassService")
public class InputsClassService extends BaseService<InputsClass, String>{
	
	@Resource(name="inputsClassDao")
	InputsClassDao inputsClassDao;
	
    @Override
    protected IBaseDao<InputsClass, String> getEntityDao() {
        return inputsClassDao;
    }
    
    /**
     * 获得投入品分类 树结构
     * @return
     */
    public List<Object[]> findAllLevelInputsClasses(){
    	return inputsClassDao.findAllLevelInputsClasses();
    }
    
    /**
     * 根据投入品分类等级 获取投入品分类集合
     * @return
     */
    public List<InputsClass> findInputsClassListByLevel(int level){
    	return inputsClassDao.findInputsClassListByLevel(level);
    }
    
    
    /**
     * 根据名称 编码 级别获取投入品
     * @return List<Object>
     */
    public List<InputsClass> findInputsClassList(String name,String code,String level,Integer status){
    	return inputsClassDao.findInputsClassList(name,code,level,status);
    }
    

   /**
	 * 根据输入得到获得品类下拉框列表
	 * @author zhangyf
	 */
	public String selectThreeInputsClass(String name){
		if(name == null || "".equals(name)){
			return "";
		}
		List<Object[]> objList =inputsClassDao.selectThreeInputsClass(name);
		StringBuffer json = new StringBuffer("");
		for(Object[] objs : objList){
	       json.append("{text:'" + (objs[1] != null? objs[1].toString():"") + "'," +
	                     "id:'"+(objs[0] != null? objs[0].toString():"")+"'," +
	                     "name:'"+ (objs[1] != null? objs[1].toString():"") +"'}\n");
		}
		return json.toString();	
	}
	
	/**
	 * 判断该投入品分类下是否具有投入品
	 * @param inputsClassId
	 * @return
	 */
	public boolean haveInputs(String inputsClassId){
		return inputsClassDao.haveInputs(inputsClassId);
	}
	
	/**
	 * 判断该投入品分类下是否具有子类
	 * @param inputsClassId
	 * @return
	 */
	public boolean haveChildren(String inputsClassId){
		return inputsClassDao.haveChildren(inputsClassId);
	}
	
	/**
	 * 依名称、级别检查名称是否重复
	 * 同级别不允许重复
	 * @param name
	 * @param level
	 * @return true：重名
	 */
	public boolean haveName(String name,int level){
		List<InputsClass> list=inputsClassDao.findInputsClassList(name, ""+level);
		if(list.size()>0)return true;
		return false;
	}
	/**
	 * 获得当前投入品的父类编码
	 * @param inputsClassId : 当前三级投入品分类
	 * @return
	 */
	public int findCodeByParentInputsClass(String inputsClassId){
		return inputsClassDao.findCodeByParentInputsClass(inputsClassId);
	}
	
}
