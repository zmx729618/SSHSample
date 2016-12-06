/**
 * 
 */
package org.nercita.bcp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.nercita.core.orm.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * kendo grid封装page类
 * @author zhaoxy
 *
 */

public class  KendoPage<T> extends Page<T>{
	
	private int skip;//起始值
	private int take;//每页数据量？
	private List<FilterDescriptor> filter;
	private List<SortDescriptor> sort;
	private String logic;//最外层逻辑
	
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public int getTake() {
		return take;
	}
	public void setTake(int take) {
		this.take = take;
	}
	public List<FilterDescriptor> getFilter() {
		return filter;
	}
	public void setFilter(List<FilterDescriptor> filter) {
		this.filter = filter;
	}
	public List<SortDescriptor> getSort() {
		return sort;
	}
	public void setSort(List<SortDescriptor> sort) {
		this.sort = sort;
	}
	public KendoPage(){
		
	}
	public KendoPage(String models){
		if(models==null||models.equals("")){
			//models为空时赋第一页，共1000页的初始值
			models=getVirtualModel();
		}
		JSONObject obj=JSON.parseObject(models);
		if(obj.containsKey("page")){
			this.pageNo=obj.getIntValue("page");
		}
		if(obj.containsKey("pageSize")){
			this.pageSize=obj.getIntValue("pageSize");
		}
		if(obj.containsKey("skip")){
			this.skip=obj.getIntValue("skip");
		}
		if(obj.containsKey("take")){
			this.take=obj.getIntValue("take");
		}
		if(obj.containsKey("sort")){
			sort=new ArrayList<SortDescriptor>();
			JSONArray ja=obj.getJSONArray("sort");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo = (JSONObject) ja.get(i);
				SortDescriptor so=new SortDescriptor();
				so.setDir(jo.getString("dir"));
				so.setField(jo.getString("field"));
				sort.add(so);
			}
		}
		if(obj.containsKey("filter")){
			filter=new ArrayList<FilterDescriptor>();
			JSONArray ja=obj.getJSONObject("filter").getJSONArray("filters");
			logic=obj.getJSONObject("filter").getString("logic");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo = (JSONObject) ja.get(i);
				FilterDescriptor fo=new FilterDescriptor();
				fo.setField(jo.getString("field"));
				fo.setValue(jo.getString("value"));
				if(fo.getValue()!=null&&!fo.getValue().equals(""))
					filter.add(fo);
			}
		}
	}
	
	
	/**
	 * 将KendoPage转换为JSON字符串
	 * 转到前台的JSON数据
	 * 默认转换方法，包括date格式化、空值控制、禁止引用
	 * 如result中存在循环引用则会内存溢出，建议使用含属性过滤器的方法
	 * @return  
	 */
	public String  getDefaultJsonResult(){	
		
		HashMap<String, Object> result=new HashMap<String, Object>();
		result.put("data",  this.getResult()!=null? this.getResult() : "");
		result.put("count", this.getTotalCount());
		return JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue);		
	}
	
	
	
	/**
	 * 将KendoPage转换为JSON字符串
	 * 转到前台的JSON数据
	 * SerializerFeature.DisableCircularReferenceDetect  循环引用
	 * SerializerFeature.WriteDateUseDateFormat 日期格式化
	 * SerializerFeature.WriteMapNullValue   空值映射为null
	 * @return  
	 */
	public String  getJsonResult(SerializerFeature... features){	
		
		HashMap<String, Object> result=new HashMap<String, Object>();
		result.put("data",  this.getResult()!=null? this.getResult() : "");
		result.put("count", this.getTotalCount());
		return JSON.toJSONString(result,features);
		
		
	}
	
	/**
	 * 增加属性过滤器
	 * @param filter
	 * @param features
	 * @return
	 */
	public String  getJsonResult(SerializeFilter filter, SerializerFeature... features){	
		
		HashMap<String, Object> result=new HashMap<String, Object>();
		result.put("data",  this.getResult()!=null? this.getResult() : "");
		result.put("count", this.getTotalCount());		
		return JSON.toJSONString(result, filter, features);
		
		
	}

	/**
	 * 后台添加过滤条件使用
	 * @param field
	 * @param value
	 */
	public void addFilter(String field,String value){
		if(filter==null)filter=new ArrayList<FilterDescriptor>();
		FilterDescriptor fd=new FilterDescriptor();
		fd.setField(field);
		fd.setValue(value);
		filter.add(fd);
	}
	
	public String getVirtualModel(){
		JSONObject jo=new JSONObject();
		jo.put("page", 1);
		jo.put("pageSize", 1000);
		return jo.toJSONString();
	}
	/**
     * ，分隔的字符串加单引号
     * @param original
     * @return
     */
    public String addQuote(String original){
		return "'"+original.replaceAll(",", "','")+"'";
    }
}
