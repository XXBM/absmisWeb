package com.absmis.domain.enterprise;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *     单位工程产业化信息
 *
 * @generated
 *
 */
@Embeddable
@DynamicInsert(true)
@DynamicUpdate(true)
public class EngineeringIndustrialization implements Serializable {
    //单体装配率
    private Double unitAssemblyRate;
    //外墙预制比例应用产业化技术的建筑面积
    private Double exteriorWallArea;
    //预制外墙水平投影面积
    private Double wallShadowArea;
    //不纳入地上容积率的建筑面积
    private Double conArea;
    //应用结构类型
    @javax.persistence.ManyToOne
    @JoinColumn(name = "applicationStructureType_id")
    private ApplicationStructureType applicationStructureType;
    // 应用构件部品
    @Embedded
    private FrameworkShear frameworkShear;
    //应用楼层范围
    private String floorScope;
    public EngineeringIndustrialization() {
        super();
    }

    public EngineeringIndustrialization(Double unitAssemblyRate, Double exteriorWallArea, Double wallShadowArea, Double conArea, ApplicationStructureType applicationStructureType, FrameworkShear frameworkShear, String floorScope) {
        this.unitAssemblyRate = unitAssemblyRate;
        this.exteriorWallArea = exteriorWallArea;
        this.wallShadowArea = wallShadowArea;
        this.conArea = conArea;
        this.applicationStructureType = applicationStructureType;
        this.frameworkShear = frameworkShear;
        this.floorScope = floorScope;
    }

    public Double getUnitAssemblyRate() {
        return unitAssemblyRate;
    }

    public void setUnitAssemblyRate(Double unitAssemblyRate) {
        this.unitAssemblyRate = unitAssemblyRate;
    }

    public Double getExteriorWallArea() {
        return exteriorWallArea;
    }

    public void setExteriorWallArea(Double exteriorWallArea) {
        this.exteriorWallArea = exteriorWallArea;
    }

    public Double getWallShadowArea() {
        return wallShadowArea;
    }

    public void setWallShadowArea(Double wallShadowArea) {
        this.wallShadowArea = wallShadowArea;
    }

    public Double getConArea() {
        return conArea;
    }

    public void setConArea(Double conArea) {
        this.conArea = conArea;
    }

    public ApplicationStructureType getApplicationStructureType() {
        return applicationStructureType;
    }

    public void setApplicationStructureType(ApplicationStructureType applicationStructureType) {
        this.applicationStructureType = applicationStructureType;
    }

    public FrameworkShear getFrameworkShear() {
        return frameworkShear;
    }

    public void setFrameworkShear(FrameworkShear frameworkShear) {
        this.frameworkShear = frameworkShear;
    }

    public String getFloorScope() {
        return floorScope;
    }

    public void setFloorScope(String floorScope) {
        this.floorScope = floorScope;
    }

}

