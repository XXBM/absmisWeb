package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 15:11 2017/8/28.
 * @Modified by:
 */
public class ConstructionEnIndustrializationInfo {
    //新增装配式混凝土结构建筑的数量
    private Double addNewConcrete;
    //新增装配式钢结构建筑的数量
    private Double addNewSteel;
    // 新增装配式木建筑的数量
    private Double addNewTimber;
    //历年累计，总累计
    private Double cumulant;
    //年度装配式混凝土结构建筑的数量
    private Double annualConcrete;
    //年度装配式钢结构建筑的数量
    private Double annualSteel;
    //年度装配式木建筑的数量
    private Double annualTimber;

    public Double getAddNewConcrete() {
        return addNewConcrete;
    }

    public void setAddNewConcrete(Double addNewConcrete) {
        this.addNewConcrete = addNewConcrete;
    }

    public Double getAddNewSteel() {
        return addNewSteel;
    }

    public void setAddNewSteel(Double addNewSteel) {
        this.addNewSteel = addNewSteel;
    }

    public Double getAddNewTimber() {
        return addNewTimber;
    }

    public void setAddNewTimber(Double addNewTimber) {
        this.addNewTimber = addNewTimber;
    }

    public Double getCumulant() {
        return cumulant;
    }

    public void setCumulant(Double cumulant) {
        this.cumulant = cumulant;
    }

    public Double getAnnualConcrete() {
        return annualConcrete;
    }

    public void setAnnualConcrete(Double annualConcrete) {
        this.annualConcrete = annualConcrete;
    }

    public Double getAnnualSteel() {
        return annualSteel;
    }

    public void setAnnualSteel(Double annualSteel) {
        this.annualSteel = annualSteel;
    }

    public Double getAnnualTimber() {
        return annualTimber;
    }

    public void setAnnualTimber(Double annualTimber) {
        this.annualTimber = annualTimber;
    }
}
