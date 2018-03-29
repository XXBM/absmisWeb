package com.absmis.domain.enterprise;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * 传统企业抽象
 *
 * @generated
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ConstructionEn")
public abstract class ConstructionEn extends Organization {
    //本单位从事装配式建筑初始累计
    protected Double cumulant;
    //建筑产业化信息
    @JsonIgnore
    @javax.persistence.OneToMany(mappedBy = "constructionEn",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Set<ConstructionEnIndustrialization> constructionEnIndustrializations;


    public ConstructionEn() {
        super();
    }

    public Double getCumulant() {
        return cumulant;
    }

    public void setCumulant(Double cumulant) {
        this.cumulant = cumulant;
    }

    public Set<ConstructionEnIndustrialization> getConstructionEnIndustrializations() {
        return constructionEnIndustrializations;
    }

    public void setConstructionEnIndustrializations(Set<ConstructionEnIndustrialization> constructionEnIndustrializations) {
        this.constructionEnIndustrializations = constructionEnIndustrializations;
    }

    abstract String getQualification();

}

