package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 14:37 2017/8/28.
 * @Modified by:no
 */
public class MachineryEnInfo {
    private String description;
    private Double enNum;
    private Double ability;

    public MachineryEnInfo(String description, Double enNum, Double ability) {
        this.description = description;
        this.enNum = enNum;
        this.ability = ability;
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

    public Double getAbility() {
        return ability;
    }

    public void setAbility(Double ability) {
        this.ability = ability;
    }
}
