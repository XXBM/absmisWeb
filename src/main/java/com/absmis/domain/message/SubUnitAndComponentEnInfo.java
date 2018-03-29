package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 16:00 2017/8/28.
 * @Modified by:no
 */
public class SubUnitAndComponentEnInfo {
    private String description;
    private Double enNum;
    private Double productionLine;
    private Double ability;
    private Double appliance;

    public SubUnitAndComponentEnInfo(String description, Double enNum, Double productionLine, Double ability, Double appliance) {
        this.description = description;
        this.enNum = enNum;
        this.productionLine = productionLine;
        this.ability = ability;
        this.appliance = appliance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getEnNum() {
        return enNum;
    }

    public void setEnNum(Double enNum) {
        this.enNum = enNum;
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
