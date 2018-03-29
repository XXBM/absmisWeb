package com.absmis.domain.enterprise;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 应用建筑产业化技术内容
 *
 * @generated
 */


@Embeddable
@DynamicInsert(true)
@DynamicUpdate(true)
public class ApplicationTechnology {
    @Embedded
    private FrameworkShear frameworkShear;
    @Embedded
    private FrameworkCore frameworkCore;
    @Embedded
    private ShearWall shearWall;


    public ApplicationTechnology() {
        super();
    }

    public FrameworkShear getFrameworkShear() {
        return frameworkShear;
    }

    public void setFrameworkShear(FrameworkShear frameworkShear) {
        this.frameworkShear = frameworkShear;
    }

    public FrameworkCore getFrameworkCore() {
        return frameworkCore;
    }

    public void setFrameworkCore(FrameworkCore frameworkCore) {
        this.frameworkCore = frameworkCore;
    }

    public ShearWall getShearWall() {
        return shearWall;
    }

    public void setShearWall(ShearWall shearWall) {
        this.shearWall = shearWall;
    }
}

