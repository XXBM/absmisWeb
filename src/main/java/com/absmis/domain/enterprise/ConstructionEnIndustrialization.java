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
 * ConstructionEnIndustrialization
 *
 * @generated
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"year", "quarter","constructionEn_id"})})
public class ConstructionEnIndustrialization implements Serializable {
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

    //新增装配式混凝土结构建筑的数量
    private Double addNewConcrete;
    //新增装配式钢结构建筑的数量
    private Double addNewSteel;
    // 新增装配式木建筑的数量
    private Double addNewTimber;

    private Double totalScale;

    public Double getTotalScale() {
        return totalScale;
    }

    public void setTotalScale(Double totalScale) {
        this.totalScale = totalScale;
    }

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


    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(name = "constructionEn_id")
    private ConstructionEn constructionEn;
    public ConstructionEnIndustrialization() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAddNewConcrete() {
        return addNewConcrete;
    }

    public Calendar getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(Calendar declareTime) {
        this.declareTime = declareTime;
    }

    public CheckedStatus getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(CheckedStatus checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public void setAddNewConcrete(Double addNewConcrete) {
        this.addNewConcrete = addNewConcrete;
    }

    public Double getAddNewSteel() {
        return addNewSteel;
    }

    public void setAddNewSteel(Double addNewSteel) {
        this.addNewSteel = addNewSteel;
    }

    public Double getAddNewTimber() {
        return addNewTimber;
    }

    public void setAddNewTimber(Double addNewTimber) {
        this.addNewTimber = addNewTimber;
    }

    public ConstructionEn getConstructionEn() {
        return constructionEn;
    }

    public void setConstructionEn(ConstructionEn constructionEn) {
        this.constructionEn = constructionEn;
    }
}


