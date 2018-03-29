package com.absmis.domain.enterprise;

import javax.persistence.DiscriminatorValue;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 项目
 *
 * @generated
 */

@javax.persistence.Entity
@DiscriminatorValue("ProjectByRealEstateEn")
public class ProjectByRealEstateEn extends Project {
    //房地产企业
    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(name = "realEstateEn_id")
    private RealEstateEn realEstateEn;

    public ProjectByRealEstateEn(Long id) {
        super(id);
    }

    public ProjectByRealEstateEn() {
        super();
    }

    @Override
    public String getOwner() {
        return "房地产企业";
    }

    @Override
    public String testProject() {
        return "test房地产企业";
    }

    public RealEstateEn getRealEstateEn() {
        return realEstateEn;
    }

    public void setRealEstateEn(RealEstateEn realEstateEn) {
        this.realEstateEn = realEstateEn;
    }
}

