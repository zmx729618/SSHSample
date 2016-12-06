package org.nercita.bcp.system.domain.reference;

/**
 * 用户状态：用户的状态有启用和禁用两种
 * @author zhangwenchao
 *
 */
public enum UserState {

    /**
     * 禁用(0)
     */
    Disable {
        @Override
        public String getTitle() {
            return "Disable";
        }
    },
    
    /**
     * 启用(1)
     */
    Enable {
        @Override
        public String getTitle() {
            return "Enable";
        }
    };


    @Override
    public String toString() {
        return this.getTitle();
    }

    public abstract String getTitle();

    public int getValue() {
        return this.ordinal();
    }

}
