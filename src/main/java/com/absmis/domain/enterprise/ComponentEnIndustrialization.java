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
 * 构件企业
 *
 * @generated
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"year", "quarter","componentEn_id"})})
public class ComponentEnIndustrialization implements Serializable {
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

    /*生产条数*/
    //预制装配混凝土结构
    private Double prebuiltConcreteNum;
    //钢结构
    private Double prebuiltSteelNum;
    //木结构
    private Double prebuiltTimberNum;
    //其他结构的构件
    private Double prebuiltOtherNum;

    /*生产能力*/
    //预制装配混凝土结构
    private Double prebuiltConcreteAbility;
    //钢结构
    private Double prebuiltSteelAbility;
    //木结构
    private Double prebuiltTimberAbility;
    //其他结构的构件
    private Double prebuiltOtherAbility;

    /*应用规模*/
    //预制装配混凝土结构
    private Double prebuiltConcreteScale;
    //钢结构
    private Double prebuiltSteelScale;
    //木结构
    private Double prebuiltTimberScale;
    //其他结构的构件
    private Double prebuiltOtherScale;

    @javax.persistence.ManyToOne
    @JoinColumn(name = "componentEn_id")
    private ComponentEn componentEn;

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

    public ComponentEnIndustrialization() {
        super();
    }

    public CheckedStatus getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(CheckedStatus checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrebuiltConcreteNum() {
        return prebuiltConcreteNum;
    }

    public Calendar getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(Calendar declareTime) {
        this.declareTime = declareTime;
    }

    public void setPrebuiltConcreteNum(Double prebuiltConcreteNum) {
        this.prebuiltConcreteNum = prebuiltConcreteNum;
    }

    public Double getPrebuiltSteelNum() {
        return prebuiltSteelNum;
    }

    public void setPrebuiltSteelNum(Double prebuiltSteelNum) {
        this.prebuiltSteelNum = prebuiltSteelNum;
    }

    public Double getPrebuiltTimberNum() {
        return prebuiltTimberNum;
    }

    public void setPrebuiltTimberNum(Double prebuiltTimberNum) {
        this.prebuiltTimberNum = prebuiltTimberNum;
    }

    public Double getPrebuiltOtherNum() {
        return prebuiltOtherNum;
    }

    public void setPrebuiltOtherNum(Double prebuiltOtherNum) {
        this.prebuiltOtherNum = prebuiltOtherNum;
    }

    public Double getPrebuiltConcreteAbility() {
        return prebuiltConcreteAbility;
    }

    public void setPrebuiltConcreteAbility(Double prebuiltConcreteAbility) {
        this.prebuiltConcreteAbility = prebuiltConcreteAbility;
    }

    public Double getPrebuiltSteelAbility() {
        return prebuiltSteelAbility;
    }

    public void setPrebuiltSteelAbility(Double prebuiltSteelAbility) {
        this.prebuiltSteelAbility = prebuiltSteelAbility;
    }

    public Double getPrebuiltTimberAbility() {
        return prebuiltTimberAbility;
    }

    public void setPrebuiltTimberAbility(Double prebuiltTimberAbility) {
        this.prebuiltTimberAbility = prebuiltTimberAbility;
    }

    public Double getPrebuiltOtherAbility() {
        return prebuiltOtherAbility;
    }

    public void setPrebuiltOtherAbility(Double prebuiltOtherAbility) {
        this.prebuiltOtherAbility = prebuiltOtherAbility;
    }

    public Double getPrebuiltConcreteScale() {
        return prebuiltConcreteScale;
    }

    public void setPrebuiltConcreteScale(Double prebuiltConcreteScale) {
        this.prebuiltConcreteScale = prebuiltConcreteScale;
    }

    public Double getPrebuiltSteelScale() {
        return prebuiltSteelScale;
    }

    public void setPrebuiltSteelScale(Double prebuiltSteelScale) {
        this.prebuiltSteelScale = prebuiltSteelScale;
    }

    public Double getPrebuiltTimberScale() {
        return prebuiltTimberScale;
    }

    public void setPrebuiltTimberScale(Double prebuiltTimberScale) {
        this.prebuiltTimberScale = prebuiltTimberScale;
    }

    public Double getPrebuiltOtherScale() {
        return prebuiltOtherScale;
    }

    public void setPrebuiltOtherScale(Double prebuiltOtherScale) {
        this.prebuiltOtherScale = prebuiltOtherScale;
    }

    public ComponentEn getComponentEn() {
        return componentEn;
    }

    public void setComponentEn(ComponentEn componentEn) {
        this.componentEn = componentEn;
    }
}

