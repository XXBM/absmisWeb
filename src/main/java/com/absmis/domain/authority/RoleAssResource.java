package com.absmis.domain.authority;


import javax.persistence.JoinColumn;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */

@javax.persistence.Entity
public class RoleAssResource {
    @javax.persistence.Id
    private Long id;
    @JoinColumn(name = "role_id")
    @javax.persistence.ManyToOne
    private Role role;
    @JoinColumn(name = "resource_id")
    @javax.persistence.ManyToOne
    private Resource resource;

    public RoleAssResource() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}

