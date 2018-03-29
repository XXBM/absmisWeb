package com.absmis.domain.message;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 11:03 2017/8/31.
 * @Modified by:
 */
public class ProjectInfoByForm {
    private String formDes;
    private Double projectNum;
    private Double area;

    public ProjectInfoByForm(String formDes, Double projectNum, Double area) {
        this.formDes = formDes;
        this.projectNum = projectNum;
        this.area = area;
    }

    public String getFormDes() {
        return formDes;
    }

    public void setFormDes(String formDes) {
        this.formDes = formDes;
    }

    public Double getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Double projectNum) {
        this.projectNum = projectNum;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
