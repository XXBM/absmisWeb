package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 16:00 2017/8/28.
 * @Modified by:no
 */
public class SubUnitAndComponentEnStatistics {
    private String name;//企业名称
    private String type;//企业类型
    private Double productionLine;//生产线
    private Double ability;//生产能力
    private Double appliance;//应用规模

    public SubUnitAndComponentEnStatistics(String name, String type, Double productionLine, Double ability, Double appliance) {
        this.name = name;
        this.type = type;
        this.productionLine = productionLine;
        this.ability = ability;
        this.appliance = appliance;
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

    public Double getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(Double productionLine) {
        this.productionLine = productionLine;
    }

    public Double getAbility() {
        return ability;
    }

    public void setAbility(Double ability) {
        this.ability = ability;
    }

    public Double getAppliance() {
        return appliance;
    }

    public void setAppliance(Double appliance) {
        this.appliance = appliance;
    }
}
