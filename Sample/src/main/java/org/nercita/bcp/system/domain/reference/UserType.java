package org.nercita.bcp.system.domain.reference;

/**
 * 用户类型用于区分登陆用户的类型（系统管理员和普通用户）
 * @author zhangwenchao
 *
 */
public enum UserType {
	
    /**
     * 系统管理员(0)
     */
    System {
        @Override
        public String getTitle() {
            return "System";
        }
    },

    
    /**
     * 普通用户(1)
     */
    General {
        @Override
        public String getTitle() {
            return "General";
        }
    };
    
    
    @Override
    public String toString() {
        return this.getTitle();
    }   

    public int getValue() {
        return this.ordinal();
    }
    
    public abstract String getTitle();

}
