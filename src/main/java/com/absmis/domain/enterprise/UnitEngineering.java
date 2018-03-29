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
 * 单位工程
 *
 * @generated
 */

@javax.persistence.Entity
public class UnitEngineering implements Serializable {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @javax.persistence.Id
    private Long id;
    //工程名称
    private String name;
    //工程开工时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar startingTime;
    private Integer year;
    private Integer quarter;
    //建筑面积
    private Double constructionArea;
    //地上层数
    private Double undergroundNum;
    //地下层数
    private Double overgroundNum;
    //工程类别
    private String engineeringCategory;
    //单位工程结构形式
    @javax.persistence.ManyToOne
    @JoinColumn(name = "structureForm_id")
    private StructureForm structureForm;
    //项目
    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "checkedStatus_id")
    private CheckedStatus checkedStatus;
    //单位工程产业化信息
    @Embedded
    private EngineeringIndustrialization engineeringIndustrialization;



    public UnitEngineering() {
        super();
    }

    public Calendar getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Calendar startingTime) {
        this.startingTime = startingTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Double constructionArea) {
        this.constructionArea = constructionArea;
    }

    public Double getUndergroundNum() {
        return undergroundNum;
    }

    public void setUndergroundNum(Double undergroundNum) {
        this.undergroundNum = undergroundNum;
    }

    public Double getOvergroundNum() {
        return overgroundNum;
    }

    public void setOvergroundNum(Double overgroundNum) {
        this.overgroundNum = overgroundNum;
    }

    public String getEngineeringCategory() {
        return engineeringCategory;
    }

    public void setEngineeringCategory(String engineeringCategory) {
        this.engineeringCategory = engineeringCategory;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public EngineeringIndustrialization getEngineeringIndustrialization() {
        return engineeringIndustrialization;
    }

    public void setEngineeringIndustrialization(EngineeringIndustrialization engineeringIndustrialization) {
        this.engineeringIndustrialization = engineeringIndustrialization;
    }

    public StructureForm getStructureForm() {
        return structureForm;
    }

    public void setStructureForm(StructureForm structureForm) {
        this.structureForm = structureForm;
    }
}

