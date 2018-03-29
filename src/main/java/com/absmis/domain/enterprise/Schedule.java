package com.absmis.domain.enterprise;

import com.absmis.jsonDeserialize.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;
import java.util.Calendar;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * 项目计划信息
 *
 * @generated
 */


@Embeddable
@DynamicInsert(true)
@DynamicUpdate(true)
public class Schedule {
    //项目起止时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar projectStartTime;
    //项目结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar projectEndTime;
    //取得土地使用权开始时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar landUseRightStart;
    //取得土地使用权结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar landUseRightEnd;
    //建设用地规划许可证开始时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar constructionLicenseStart;
    //建设用地规划许可证结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar constructionLicenseEnd;
    //建设工程规划许可证开始时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar engineeringLicenseStart;
    //建设工程规划许可证结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar engineeringLicenseEnd;
    //组织工程招标及工程施工开始时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar tenderStart;
    //组织工程招标及工程施工结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar tenderEnd;
    //项目综合验收开始时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar comprehensiveInspectionAndAcceptanceStart;
    //项目综合验收结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar comprehensiveInspectionAndAcceptanceEnd;
    //项目交付使用开始时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar deliveryStart;
    //项目交付使用结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar deliveryEnd;
    //施工图设计及审查开始时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar constructionDrawingStart;
    //施工图设计及审查结束时间
    @JsonSerialize(using = CustomDateSerializer.class)
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar constructionDrawingEnd;

    public Schedule() {
        super();
    }

    public Calendar getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(Calendar projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public Calendar getProjectEndTime() {
        return projectEndTime;
    }

    public void setProjectEndTime(Calendar projectEndTime) {
        this.projectEndTime = projectEndTime;
    }

    public Calendar getLandUseRightStart() {
        return landUseRightStart;
    }

    public void setLandUseRightStart(Calendar landUseRightStart) {
        this.landUseRightStart = landUseRightStart;
    }

    public Calendar getLandUseRightEnd() {
        return landUseRightEnd;
    }

    public void setLandUseRightEnd(Calendar landUseRightEnd) {
        this.landUseRightEnd = landUseRightEnd;
    }

    public Calendar getConstructionLicenseStart() {
        return constructionLicenseStart;
    }

    public void setConstructionLicenseStart(Calendar constructionLicenseStart) {
        this.constructionLicenseStart = constructionLicenseStart;
    }

    public Calendar getConstructionLicenseEnd() {
        return constructionLicenseEnd;
    }

    public void setConstructionLicenseEnd(Calendar constructionLicenseEnd) {
        this.constructionLicenseEnd = constructionLicenseEnd;
    }

    public Calendar getEngineeringLicenseStart() {
        return engineeringLicenseStart;
    }

    public void setEngineeringLicenseStart(Calendar engineeringLicenseStart) {
        this.engineeringLicenseStart = engineeringLicenseStart;
    }

    public Calendar getEngineeringLicenseEnd() {
        return engineeringLicenseEnd;
    }

    public void setEngineeringLicenseEnd(Calendar engineeringLicenseEnd) {
        this.engineeringLicenseEnd = engineeringLicenseEnd;
    }

    public Calendar getTenderStart() {
        return tenderStart;
    }

    public void setTenderStart(Calendar tenderStart) {
        this.tenderStart = tenderStart;
    }

    public Calendar getTenderEnd() {
        return tenderEnd;
    }

    public void setTenderEnd(Calendar tenderEnd) {
        this.tenderEnd = tenderEnd;
    }

    public Calendar getComprehensiveInspectionAndAcceptanceStart() {
        return comprehensiveInspectionAndAcceptanceStart;
    }

    public void setComprehensiveInspectionAndAcceptanceStart(Calendar comprehensiveInspectionAndAcceptanceStart) {
        this.comprehensiveInspectionAndAcceptanceStart = comprehensiveInspectionAndAcceptanceStart;
    }

    public Calendar getComprehensiveInspectionAndAcceptanceEnd() {
        return comprehensiveInspectionAndAcceptanceEnd;
    }

    public void setComprehensiveInspectionAndAcceptanceEnd(Calendar comprehensiveInspectionAndAcceptanceEnd) {
        this.comprehensiveInspectionAndAcceptanceEnd = comprehensiveInspectionAndAcceptanceEnd;
    }

    public Calendar getDeliveryStart() {
        return deliveryStart;
    }

    public void setDeliveryStart(Calendar deliveryStart) {
        this.deliveryStart = deliveryStart;
    }

    public Calendar getDeliveryEnd() {
        return deliveryEnd;
    }

    public void setDeliveryEnd(Calendar deliveryEnd) {
        this.deliveryEnd = deliveryEnd;
    }

    public Calendar getConstructionDrawingStart() {
        return constructionDrawingStart;
    }

    public void setConstructionDrawingStart(Calendar constructionDrawingStart) {
        this.constructionDrawingStart = constructionDrawingStart;
    }

    public Calendar getConstructionDrawingEnd() {
        return constructionDrawingEnd;
    }

    public void setConstructionDrawingEnd(Calendar constructionDrawingEnd) {
        this.constructionDrawingEnd = constructionDrawingEnd;
    }
}

