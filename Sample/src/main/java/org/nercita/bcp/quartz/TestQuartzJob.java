package org.nercita.bcp.quartz;

public class TestQuartzJob {
	
    public void execute(){  
        try{  
             System.out.println("测试自动作业调度执行");
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 

}
