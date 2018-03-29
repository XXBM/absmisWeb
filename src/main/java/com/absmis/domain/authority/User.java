package com.absmis.domain.authority;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DynamicInsert(true)
@DynamicUpdate(true)
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    protected Long id;
    @Column(unique = true)
    protected String username;
    protected String password;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Calendar lastLogin;
    protected int loginCounter;
    @ManyToOne
    @JoinColumn(name = "role_id")
    protected Role role;
    public User() {
        super();
    }
    public User(Long id) {

       this.id=id;
    }
    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.role = user.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Calendar lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getLoginCounter() {
        return loginCounter;
    }

    public void setLoginCounter(int loginCounter) {
        this.loginCounter = loginCounter;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public abstract String getEnterpriseType();
}

