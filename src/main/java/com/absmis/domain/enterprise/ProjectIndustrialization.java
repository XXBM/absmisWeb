package com.absmis.domain.enterprise;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */


@Embeddable
@DynamicInsert(true)
@DynamicUpdate(true)
public class ProjectIndustrialization {
    //应用产业化技术的建筑面积
    private Double industrializedTechnologyArea;
    //落实产业化技术的面积比例
    private Double industrializedTechnologyAreaRatio;
    //建筑单体装配率（≥45%）
    private Double unitAssemblyRate;
    //建筑外墙采用预制墙体的比例（≥60%）
    private Double wall;
    //各单体预制外墙水平投影总面积
    private Double wallShadowArea;
    //不纳入地上容积率的建筑面积
    private Double constructionArea;
    //应用建筑产业化技术内容
    @Embedded
    private ApplicationTechnology applicationTechnology;

    public ApplicationTechnology getApplicationTechnology() {
        return applicationTechnology;
    }

    public void setApplicationTechnology(ApplicationTechnology applicationTechnology) {
        this.applicationTechnology = applicationTechnology;
    }

    public ProjectIndustrialization() {
        super();
    }

    public Double getIndustrializedTechnologyArea() {
        return industrializedTechnologyArea;
    }

    public void setIndustrializedTechnologyArea(Double industrializedTechnologyArea) {
        this.industrializedTechnologyArea = industrializedTechnologyArea;
    }

    public Double getIndustrializedTechnologyAreaRatio() {
        return industrializedTechnologyAreaRatio;
    }

    public void setIndustrializedTechnologyAreaRatio(Double industrializedTechnologyAreaRatio) {
        this.industrializedTechnologyAreaRatio = industrializedTechnologyAreaRatio;
    }

    public Double getUnitAssemblyRate() {
        return unitAssemblyRate;
    }

    public void setUnitAssemblyRate(Double unitAssemblyRate) {
        this.unitAssemblyRate = unitAssemblyRate;
    }

    public Double getWall() {
        return wall;
    }

    public void setWall(Double wall) {
        this.wall = wall;
    }

    public Double getWallShadowArea() {
        return wallShadowArea;
    }

    public void setWallShadowArea(Double wallShadowArea) {
        this.wallShadowArea = wallShadowArea;
    }

    public Double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Double constructionArea) {
        this.constructionArea = constructionArea;
    }

}

