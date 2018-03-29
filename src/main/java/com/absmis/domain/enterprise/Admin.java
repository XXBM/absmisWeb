package com.absmis.domain.enterprise;


import com.absmis.domain.authority.User;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *     管理部门
 *
 * @generated
 */

@javax.persistence.Entity
public class Admin extends User {
    public Admin() {
        super();
    }

    @Override
    public String getEnterpriseType() {
        return "业务管理员";
    }

}

