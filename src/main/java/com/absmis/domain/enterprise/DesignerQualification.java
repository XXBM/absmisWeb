package com.absmis.domain.enterprise;

import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 设计单位资质等级
 *
 * @generated
 */

@javax.persistence.Entity
public class DesignerQualification implements Serializable {
    private static final long serialVersionUID = 2618319012162901983L;
    @javax.persistence.Id
    private Long id;
    private String no;
    private String description;
    public DesignerQualification() {
        super();
    }

    public DesignerQualification(Long id) {
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

