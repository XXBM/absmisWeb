package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.Project;
import com.absmis.domain.message.ProjectInfo;
import com.absmis.domain.message.ProjectInfoByForm;
import com.absmis.service.enterprise.CheckedStatusService;
import com.absmis.service.enterprise.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    CheckedStatusService checkedStatusService;

    //统计项目信息3中
    @RequestMapping(value = "/queryQuarterFormProject", method = RequestMethod.GET)
    public List<ProjectInfoByForm> queryQuarterByForm(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception {
        Specification<Project> concreteSp = this.projectService.queryQuarterByForm((long)1,year,quarter);
        List<Project> concreteList = this.projectService.findBySepc(concreteSp);
        double concreteArea = 0;
        for(int i=0;i<concreteList.size();i++){
            concreteArea += concreteList.get(i).getTotalConstructionArea();
        }
        ProjectInfoByForm concreteInfo = new ProjectInfoByForm("混凝土结构",(double)concreteList.size(),concreteArea);
        Specification<Project> steelSp = this.projectService.queryQuarterByForm((long)2,year,quarter);
        List<Project> steelList = this.projectService.findBySepc(steelSp);
        double stellArea = 0;
        for(int i=0;i<steelList.size();i++){
            stellArea += steelList.get(i).getTotalConstructionArea();
        }
        ProjectInfoByForm steelInfo = new ProjectInfoByForm("钢结构",(double)steelList.size(),stellArea);
        Specification<Project> timberSp = this.projectService.queryQuarterByForm((long)3,year,quarter);
        List<Project> timberList = this.projectService.findBySepc(timberSp);
        double timberArea = 0;
        for(int i=0;i<timberList.size();i++){
            timberArea += timberList.get(i).getTotalConstructionArea();
        }
        ProjectInfoByForm timberInfo = new ProjectInfoByForm("木结构",(double)timberList.size(),timberArea);
        Specification<Project> otherSp = this.projectService.queryQuarterByForm((long)4,year,quarter);
        List<Project> otherList = this.projectService.findBySepc(otherSp);
        double otherArea = 0;
        for(int i=0;i<otherList.size();i++){
            otherArea += otherList.get(i).getTotalConstructionArea();
        }
        ProjectInfoByForm otherInfo = new ProjectInfoByForm("其他结构",(double)otherList.size(),otherArea);
        List<ProjectInfoByForm> projectInfoByForms = new ArrayList<>();
        projectInfoByForms.add(concreteInfo);
        projectInfoByForms.add(steelInfo);
        projectInfoByForms.add(timberInfo);
        projectInfoByForms.add(otherInfo);
        return projectInfoByForms;
    }
    //统计项目信息3上
    @RequestMapping(value = "/queryQuarterProject", method = RequestMethod.GET)
    public List<ProjectInfo> queryQuarter(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception {
        Specification<Project> projectSp = this.projectService.queryQuarter(year,quarter);
        List<Project> projectList = this.projectService.findBySepc(projectSp);
        ProjectInfo projectInfo = new ProjectInfo("项目总数",(double)projectList.size());
        Specification<Project> pPublicSp = this.projectService.queryQuarterByCategory((long)1,year,quarter);
        List<Project> pPublicList = this.projectService.findBySepc(pPublicSp);
        ProjectInfo pPublicInfo = new ProjectInfo("公建项目数量",(double)pPublicList.size());
        Specification<Project> pHouseSp = this.projectService.queryQuarterByCategory((long)2,year,quarter);
        List<Project> pHouseList = this.projectService.findBySepc(pHouseSp);
        ProjectInfo pHouseInfo = new ProjectInfo("住宅项目数量",(double)pHouseList.size());
        Specification<Project> pWorkSp = this.projectService.queryQuarterByCategory((long)3,year,quarter);
        List<Project> pWorkList = this.projectService.findBySepc(pWorkSp);
        ProjectInfo pWorkInfo = new ProjectInfo("厂房项目数量",(double)pWorkList.size());
        Specification<Project> pOtherSp = this.projectService.queryQuarterByCategory((long)4,year,quarter);
        List<Project> pOtherList = this.projectService.findBySepc(pOtherSp);
        ProjectInfo pOtherInfo = new ProjectInfo("其他项目数量",(double)pOtherList.size());
        double totalConstructionArea = 0;
        double overgroundConstructionArea = 0;
        double industrializedTechnologyArea = 0;
        for(int i=0;i<projectList.size();i++){
            totalConstructionArea += projectList.get(i).getTotalConstructionArea();
            overgroundConstructionArea += projectList.get(i).getOvergroundConstructionArea();
            industrializedTechnologyArea += projectList.get(i).getProjectIndustrialization().getIndustrializedTechnologyArea();
        }
        ProjectInfo projectByT = new ProjectInfo("项目总建筑面积",totalConstructionArea);
        ProjectInfo projectByO = new ProjectInfo("地上建筑总面积",overgroundConstructionArea);
        ProjectInfo projectByI = new ProjectInfo("应用产业化技术的项目总建筑面积",industrializedTechnologyArea);
        List<ProjectInfo> projectInfos = new ArrayList<>();
        projectInfos.add(projectInfo);
        projectInfos.add(pPublicInfo);
        projectInfos.add(pHouseInfo);
        projectInfos.add(pWorkInfo);
        projectInfos.add(pOtherInfo);
        projectInfos.add(projectByT);
        projectInfos.add(projectByO);
        projectInfos.add(projectByI);
        return projectInfos;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findProjectInfoById", method = RequestMethod.GET)
    public Project findProjectById(@RequestParam(value = "id") Long id)throws Exception {
        System.out.println(projectService.findById(id).getFillTime().getTime()+"时间问题");
        return projectService.findById(id);
    }

    //根据项目开工起止时间查询项目
    @RequestMapping(value = "/queryProject", method = RequestMethod.GET)
    public Map<String, Object> queryProject(
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        System.out.println("***"+startTime);
        System.out.println("---"+endTime);
        Pageable pageable = new PageRequest(page-1,size);
        Specification<Project> specification = this.projectService.queryProject(startTime,endTime);
        Page<Project> list = this.projectService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.projectService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //添加
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public Map<String, Object> addProject(@RequestBody Project project)throws Exception {
        this.projectService.addProject(project);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project", project);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllProjects", method = RequestMethod.GET)
    public List<Project> findProject()throws Exception {
        List<Project> projects = projectService.findAllT();
        return projects;
    }


    //实现分页
    @RequestMapping(value = "/displayAllProjects", method = RequestMethod.GET)
    public Map<String, Object> findAllProject(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<Project> list = this.projectService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.projectService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateProject", method = RequestMethod.PUT)
    public Map<String, Object> updateProject(@RequestBody Project project)throws Exception {
        this.projectService.updateProject(project);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project", project);
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateProjectById", method = RequestMethod.POST)
    public Map<String, Object> updateProjectById(
            @RequestParam("id") Long id,
            @RequestParam("checkedStatus") Long checkedId
    )throws Exception {
        Project project = projectService.findById(id);
        project.setCheckedStatus(checkedStatusService.findOne(checkedId));
        this.projectService.updateProject(project);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project", project);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteProject", method = RequestMethod.DELETE)
    public void deleteProject(@RequestParam("id") Long id)throws Exception {
        this.projectService.deleteProject(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteProjects",method = RequestMethod.DELETE)
    public void deleteProjects(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.projectService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
