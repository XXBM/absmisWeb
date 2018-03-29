package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 9:48 2017/8/31.
 * @Modified by:
 */
public class ProjectInfo {
    private String description;
    private Double number;

    public ProjectInfo(String description, Double number) {
        this.description = description;
        this.number = number;
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
}
