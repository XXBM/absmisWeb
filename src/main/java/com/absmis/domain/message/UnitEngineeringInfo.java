package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 10:21 2017/8/31.
 * @Modified by:
 */
public class UnitEngineeringInfo {
    private String description;
    private Double number;
    private Double area;

    public UnitEngineeringInfo(String description, Double number, Double area) {
        this.description = description;
        this.number = number;
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
