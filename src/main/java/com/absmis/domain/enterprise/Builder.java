package com.absmis.domain.enterprise;


import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 施工单位
 *
 * @generated
 */

@javax.persistence.Entity
@DiscriminatorValue("Builder")
public class Builder extends ConstructionEn {
    //资质证书编号
    private String qualificationNo;

    //资质
    //private String qualificationDes;

    @JoinColumn(name = "builderQualification_id")
    @ManyToOne
    private BuilderQualification builderQualification;


    public Builder() {
        super();
    }

    @Override
    public String getEnterpriseType() {
        return "施工单位";
    }

    @Override
    String getQualification() {
        return null;
    }

    public String getQualificationNo() {
        return qualificationNo;
    }

    public void setQualificationNo(String qualificationNo) {
        this.qualificationNo = qualificationNo;
    }

    public BuilderQualification getBuilderQualification() {
        return builderQualification;
    }

    public void setBuilderQualification(BuilderQualification builderQualification) {
        this.builderQualification = builderQualification;
    }
}

