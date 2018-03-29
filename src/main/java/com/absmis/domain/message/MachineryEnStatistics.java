package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 14:37 2017/8/28.
 * @Modified by:no
 */
public class MachineryEnStatistics {
    private String name;//企业名称
    private String type;//企业类型
    private Double ability;//生产能力

    public MachineryEnStatistics(String name, String type, Double ability) {
        this.name = name;
        this.type = type;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAbility() {
        return ability;
    }

    public void setAbility(Double ability) {
        this.ability = ability;
    }
}
