package com.absmis.domain.enterprise;


import com.absmis.domain.authority.User;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *     业务管理员
 *
 * @generated
 */

@javax.persistence.Entity
public class Supervisor extends User {
    public Supervisor() {
        super();
    }

    @Override
    public String getEnterpriseType() {
        return "管理部门";
    }

}

