package com.absmis.domain.enterprise;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 框架核心筒
 *
 * @generated
 */

@Embeddable
@DynamicInsert(true)
@DynamicUpdate(true)
public class FrameworkCore{
    //柱
    @javax.persistence.Column(nullable = true)
    private boolean columnFc;
    //梁
    @javax.persistence.Column(nullable = true)
    private boolean beamFc;
    //楼板
    @javax.persistence.Column(nullable = true)
    private boolean floorFc;
    //楼梯
    @javax.persistence.Column(nullable = true)
    private boolean stairsFc;
    //外墙
    @javax.persistence.Column(nullable = true)
    private boolean exteriorWallFc;
    //内墙
    @javax.persistence.Column(nullable = true)
    private boolean interiorWallFc;
    //整体厨房
    @javax.persistence.Column(nullable = true)
    private boolean integralKitchenFc;
    //整体卫生间
    @javax.persistence.Column(nullable = true)
    private boolean integralToiletFc;
    //太阳能
    @javax.persistence.Column(nullable = true)
    private boolean solarEnergyFc;
    public FrameworkCore() {
        super();
    }

    public boolean isColumnFc() {
        return columnFc;
    }

    public void setColumnFc(boolean columnFc) {
        this.columnFc = columnFc;
    }

    public boolean isBeamFc() {
        return beamFc;
    }

    public void setBeamFc(boolean beamFc) {
        this.beamFc = beamFc;
    }

    public boolean isFloorFc() {
        return floorFc;
    }

    public void setFloorFc(boolean floorFc) {
        this.floorFc = floorFc;
    }

    public boolean isStairsFc() {
        return stairsFc;
    }

    public void setStairsFc(boolean stairsFc) {
        this.stairsFc = stairsFc;
    }

    public boolean isExteriorWallFc() {
        return exteriorWallFc;
    }

    public void setExteriorWallFc(boolean exteriorWallFc) {
        this.exteriorWallFc = exteriorWallFc;
    }

    public boolean isInteriorWallFc() {
        return interiorWallFc;
    }

    public void setInteriorWallFc(boolean interiorWallFc) {
        this.interiorWallFc = interiorWallFc;
    }

    public boolean isIntegralKitchenFc() {
        return integralKitchenFc;
    }

    public void setIntegralKitchenFc(boolean integralKitchenFc) {
        this.integralKitchenFc = integralKitchenFc;
    }

    public boolean isIntegralToiletFc() {
        return integralToiletFc;
    }

    public void setIntegralToiletFc(boolean integralToiletFc) {
        this.integralToiletFc = integralToiletFc;
    }

    public boolean isSolarEnergyFc() {
        return solarEnergyFc;
    }

    public void setSolarEnergyFc(boolean solarEnergyFc) {
        this.solarEnergyFc = solarEnergyFc;
    }
}

