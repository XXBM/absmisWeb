package com.absmis.domain.enterprise;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 建设单位
 *
 * @generated
 */

@javax.persistence.Entity
public class EstateOwner extends Organization {
    //项目
    @JsonIgnore
    @javax.persistence.OneToMany(mappedBy = "estateOwner",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProjectByEstateOwner> projectByEstateOwners;

    public EstateOwner() {
        super();
    }

    @Override
    public String getEnterpriseType() {
        return "建设单位";
    }

    public Set<ProjectByEstateOwner> getProjectByEstateOwners() {
        return projectByEstateOwners;
    }

    public void setProjectByEstateOwners(Set<ProjectByEstateOwner> projectByEstateOwners) {
        this.projectByEstateOwners = projectByEstateOwners;
    }
}

