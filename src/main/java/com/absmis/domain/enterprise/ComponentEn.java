package com.absmis.domain.enterprise;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated 构件企业
 */

@javax.persistence.Entity
public class ComponentEn extends Organization {
    //建筑产业化信息
    @JsonIgnore
    @javax.persistence.OneToMany(mappedBy = "componentEn",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ComponentEnIndustrialization> componentEnIndustrializations;

    public ComponentEn() {
        super();
    }
    public ComponentEn(Long id) {
       this.id = id;
    }

    @Override
    public String getEnterpriseType() {
        return "构件生产企业";
    }

    public Set<ComponentEnIndustrialization> getComponentEnIndustrializations() {
        return componentEnIndustrializations;
    }


    public void setComponentEnIndustrializations(Set<ComponentEnIndustrialization> componentEnIndustrializations) {
        this.componentEnIndustrializations = componentEnIndustrializations;
    }
}

