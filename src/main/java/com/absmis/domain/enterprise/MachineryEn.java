package com.absmis.domain.enterprise;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 *     机具设备企业
 *
 * @generated
 */

@javax.persistence.Entity
public class MachineryEn extends Organization {
    //建筑产业化信息
    @JsonIgnore
    @javax.persistence.OneToMany(mappedBy = "machineryEn",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MachineryEnIndustrialization> machineryEnIndustrializations;

    public MachineryEn() {
        super();
    }

    @Override
    public String getEnterpriseType() {
        return "设备生产企业";
    }

    public Set<MachineryEnIndustrialization> getMachineryEnIndustrializations() {
        return machineryEnIndustrializations;
    }

    public void setMachineryEnIndustrializations(Set<MachineryEnIndustrialization> machineryEnIndustrializations) {
        this.machineryEnIndustrializations = machineryEnIndustrializations;
    }
}

