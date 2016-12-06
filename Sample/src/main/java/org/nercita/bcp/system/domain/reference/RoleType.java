package org.nercita.bcp.system.domain.reference;

/**
 * 角色类型：用于区分用户属于哪一种身份
 * @author zhangwenchao
 *
 */
public enum RoleType {

    /**
     * 区试处(0)
     */
	Regional{
        @Override
        public String getTitle() {
            return "Regional";
        }
    },
    
    /**
     * 主持人(1)
     */
    Master{
        @Override
        public String getTitle() {
            return "Master";
        }
    },
    
    /**
     * 试验点(2)
     */
    District{
        @Override
        public String getTitle() {
            return "District";
        }
    },
    
    
    /**
     * 种子站(3)
     */
    Station{
        @Override
        public String getTitle() {
            return "Station";
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
