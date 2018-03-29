package com.absmis.domain.enterprise;

import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 房地产资质等级
 *
 * @generated
 */

@javax.persistence.Entity
public class RealEstateEnQualification implements Serializable {
    @javax.persistence.Id
    private Long id;
    private String no;
    private String description;
    public RealEstateEnQualification() {
        super();
    }

    public RealEstateEnQualification(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

