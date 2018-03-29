package com.absmis.domain.enterprise;


import com.absmis.jsonDeserialize.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated Industrialization
 */

@javax.persistence.Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"year", "quarter","machineryEn_id"})})
public class MachineryEnIndustrialization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar declareTime;

    private Integer year;
    private Integer quarter;
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar quarterEnd;

    public Calendar getQuarterEnd() {
        return quarterEnd;
    }

    public void setQuarterEnd(Calendar quarterEnd) {
        this.quarterEnd = quarterEnd;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    //预制混凝土生产设备
    private Double integralWall;
    //专用运输设备
    private Double specialTransportEquipment;
    // 专用施工设备
    private Double specialConstructionEquipment;

    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(name = "machineryEn_id")
    private MachineryEn machineryEn;

    private boolean submit;

    public boolean isSubmit() {
        return submit;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    @ManyToOne
    @JoinColumn(name = "checkedStatus_id")
    private CheckedStatus checkedStatus;

    public CheckedStatus getCheckedStatus() {
        return checkedStatus;
    }

    public Calendar getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(Calendar declareTime) {
        this.declareTime = declareTime;
    }

    public void setCheckedStatus(CheckedStatus checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public MachineryEnIndustrialization() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIntegralWall() {
        return integralWall;
    }

    public void setIntegralWall(Double integralWall) {
        this.integralWall = integralWall;
    }

    public Double getSpecialTransportEquipment() {
        return specialTransportEquipment;
    }

    public void setSpecialTransportEquipment(Double specialTransportEquipment) {
        this.specialTransportEquipment = specialTransportEquipment;
    }

    public Double getSpecialConstructionEquipment() {
        return specialConstructionEquipment;
    }

    public void setSpecialConstructionEquipment(double specialConstructionEquipment) {
        this.specialConstructionEquipment = specialConstructionEquipment;
    }

    public void setSpecialConstructionEquipment(Double specialConstructionEquipment) {
        this.specialConstructionEquipment = specialConstructionEquipment;
    }

    public MachineryEn getMachineryEn() {
        return machineryEn;
    }

    public void setMachineryEn(MachineryEn machineryEn) {
        this.machineryEn = machineryEn;
    }
}

