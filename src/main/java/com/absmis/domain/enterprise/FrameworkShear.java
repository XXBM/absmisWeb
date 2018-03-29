package com.absmis.domain.enterprise;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 框架及剪框
 *
 * @generated
 */

@Embeddable
@DynamicInsert(true)
@DynamicUpdate(true)
public class FrameworkShear {
    //柱
    @javax.persistence.Column(nullable = true)
    private Boolean columnFs;
    //梁
    @javax.persistence.Column(nullable = true)
    private Boolean beamFs;
    //楼板
    @javax.persistence.Column(nullable = true)
    private Boolean floorFs;
    //楼梯
    @javax.persistence.Column(nullable = true)
    private Boolean stairsFs;
    //外墙
    @javax.persistence.Column(nullable = true)
    private Boolean exteriorWallFs;
    //内墙
    @javax.persistence.Column(nullable = true)
    private Boolean interiorWallFs;
    //整体厨房
    @javax.persistence.Column(nullable = true)
    private Boolean integralKitchenFs;
    //整体卫生间
    @javax.persistence.Column(nullable = true)
    private Boolean integralToiletFs;
    //太阳能
    @javax.persistence.Column(nullable = true)
    private Boolean solarEnergyFs;

    public FrameworkShear() {
        super();
    }

    public Boolean getColumnFs() {
        return columnFs;
    }

    public void setColumnFs(Boolean columnFs) {
        this.columnFs = columnFs;
    }

    public Boolean getBeamFs() {
        return beamFs;
    }

    public void setBeamFs(Boolean beamFs) {
        this.beamFs = beamFs;
    }

    public Boolean getFloorFs() {
        return floorFs;
    }

    public void setFloorFs(Boolean floorFs) {
        this.floorFs = floorFs;
    }

    public Boolean getStairsFs() {
        return stairsFs;
    }

    public void setStairsFs(Boolean stairsFs) {
        this.stairsFs = stairsFs;
    }

    public Boolean getExteriorWallFs() {
        return exteriorWallFs;
    }

    public void setExteriorWallFs(Boolean exteriorWallFs) {
        this.exteriorWallFs = exteriorWallFs;
    }

    public Boolean getInteriorWallFs() {
        return interiorWallFs;
    }

    public void setInteriorWallFs(Boolean interiorWallFs) {
        this.interiorWallFs = interiorWallFs;
    }

    public Boolean getIntegralKitchenFs() {
        return integralKitchenFs;
    }

    public void setIntegralKitchenFs(Boolean integralKitchenFs) {
        this.integralKitchenFs = integralKitchenFs;
    }

    public Boolean getIntegralToiletFs() {
        return integralToiletFs;
    }

    public void setIntegralToiletFs(Boolean integralToiletFs) {
        this.integralToiletFs = integralToiletFs;
    }

    public Boolean getSolarEnergyFs() {
        return solarEnergyFs;
    }

    public void setSolarEnergyFs(Boolean solarEnergyFs) {
        this.solarEnergyFs = solarEnergyFs;
    }
}

