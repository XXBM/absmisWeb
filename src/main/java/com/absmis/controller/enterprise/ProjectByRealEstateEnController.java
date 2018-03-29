package com.absmis.controller.enterprise;

import com.absmis.domain.authority.User;
import com.absmis.domain.enterprise.ProjectByRealEstateEn;
import com.absmis.domain.enterprise.RealEstateEn;
import com.absmis.service.authority.UserService;
import com.absmis.service.enterprise.ProjectByRealEstateEnService;
import com.absmis.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ProjectByRealEstateEnController {
    @Autowired
    ProjectByRealEstateEnService projectByRealEstateEnService;

    @Autowired
    UserService userService;
    String username = null;
    User storedUser = null;
    //根据项目开工起止时间查询项目
    @RequestMapping(value = "/queryProjectByRealEstateEn", method = RequestMethod.GET)
    public Map<String, Object> queryProjectByRealEstateEn(
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        System.out.println(startTime);
        System.out.println(endTime);
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Pageable pageable = new PageRequest(page-1,size);
        Specification<ProjectByRealEstateEn> specification = this.projectByRealEstateEnService.queryProjectByRealEstateEn(startTime,endTime);
        Page<ProjectByRealEstateEn> list = this.projectByRealEstateEnService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.projectByRealEstateEnService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //添加
    @RequestMapping(value = "/addProjectByRealEstateEn", method = RequestMethod.POST)
    public Map<String, Object> addProjectByRealEstateEn(@RequestBody ProjectByRealEstateEn projectByRealEstateEn)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        System.out.println(projectByRealEstateEn.getStartingTime().getTime()+"时间问题");
        projectByRealEstateEn.setRealEstateEn((RealEstateEn)storedUser);
        projectByRealEstateEn.setYear(projectByRealEstateEn.getStartingTime().getWeekYear());
        projectByRealEstateEn.setQuarter(Utils.getSeason(projectByRealEstateEn.getStartingTime()));
        this.projectByRealEstateEnService.addProjectByRealEstateEn(projectByRealEstateEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectByRealEstateEn", projectByRealEstateEn);
        return map;
    }

//    /**
//     * 获取到所有
//     */
//    @RequestMapping(value = "/findProjectInfoById", method = RequestMethod.GET)
//    public ProjectByRealEstateEn findProjectById(@RequestParam(value = "id") Long id)throws Exception {
//        return projectByRealEstateEnService.findById(id);
//    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllProjectByRealEstateEns", method = RequestMethod.GET)
    public List<ProjectByRealEstateEn> findProjectByRealEstateEn()throws Exception {
        List<ProjectByRealEstateEn> projectByRealEstateEns = projectByRealEstateEnService.findAllT();
        return projectByRealEstateEns;
    }


    //实现分页
    @RequestMapping(value = "/displayAllProjectByRealEstateEns", method = RequestMethod.GET)
    public Map<String, Object> findAllProjectByRealEstateEn(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<ProjectByRealEstateEn> list = this.projectByRealEstateEnService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.projectByRealEstateEnService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateProjectByRealEstateEn", method = RequestMethod.PUT)
    public Map<String, Object> updateProjectByRealEstateEn(@RequestBody ProjectByRealEstateEn projectByRealEstateEn)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        projectByRealEstateEn.setRealEstateEn((RealEstateEn)storedUser);
        this.projectByRealEstateEnService.updateProjectByRealEstateEn(projectByRealEstateEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectByRealEstateEn", projectByRealEstateEn);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteProjectByRealEstateEn", method = RequestMethod.DELETE)
    public void deleteProjectByRealEstateEn(@RequestParam("id") Long id)throws Exception {
        this.projectByRealEstateEnService.deleteProjectByRealEstateEn(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteProjectByRealEstateEns",method = RequestMethod.DELETE)
    public void deleteProjectByRealEstateEns(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.projectByRealEstateEnService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
