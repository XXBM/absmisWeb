package com.absmis.domain.enterprise;


import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *     设计单位
 *
 * @generated
 */

@javax.persistence.Entity
@DiscriminatorValue("Designer")
public class Designer extends ConstructionEn {
    //资质证书编号
    private String qualificationNo;
    //资质
//    @javax.persistence.Column(nullable = false)
//    privae String qualificationDes;
    @JoinColumn(name = "designerQualification_id")
    @ManyToOne
    private DesignerQualification designerQualification;

    public Designer() {
        super();
    }

    @Override
    public String getEnterpriseType() {
        return "设计单位";
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

    public DesignerQualification getDesignerQualification() {
        return designerQualification;
    }

    public void setDesignerQualification(DesignerQualification designerQualification) {
        this.designerQualification = designerQualification;
    }
}

