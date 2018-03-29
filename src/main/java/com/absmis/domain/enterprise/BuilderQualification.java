package com.absmis.domain.enterprise;

import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 施工单位资质等级
 * @generated
 */

@javax.persistence.Entity
public class BuilderQualification implements Serializable {
    @javax.persistence.Id
    private Long id;
    private String no;
    private String description;
    public BuilderQualification() {
        super();
    }

    public BuilderQualification(Long id) {
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

