package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 15:11 2017/8/28.
 * @Modified by:
 */
public class ConstructionEnStatistics {
    private String name;//企业名称
    private String type;//企业类型
    private Double totalScale;//总的规模累计
    private Double annualScale;//年度规模累计

    public ConstructionEnStatistics(String name, String type, Double totalScale, Double annualScale) {
        this.name = name;
        this.type = type;
        this.totalScale = totalScale;
        this.annualScale = annualScale;
    }

    public Double getTotalScale() {
        return totalScale;
    }

    public void setTotalScale(Double totalScale) {
        this.totalScale = totalScale;
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

    public Double getAnnualScale() {
        return annualScale;
    }

    public void setAnnualScale(Double annualScale) {
        this.annualScale = annualScale;
    }
}
