package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.Supervisor;
import com.absmis.service.authority.RoleService;
import com.absmis.service.enterprise.CheckedStatusService;
import com.absmis.service.enterprise.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class SupervisorController {
    @Autowired
    SupervisorService supervisorService;
    @Autowired
    RoleService roleService;
    @Autowired
    CheckedStatusService checkedStatusService;


    //根据企业名称模糊查询
    @RequestMapping(value = "/querySupervisorByName", method = RequestMethod.GET)
    public Map<String, Object> querySupervisorByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<Supervisor> specification = this.supervisorService.queryName(query);
        Page<Supervisor> list = this.supervisorService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.supervisorService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //添加
    @RequestMapping(value = "/addSupervisor", method = RequestMethod.POST)
    public Map<String, Object> addSupervisor(@RequestBody Supervisor supervisor)throws Exception {
        supervisor.setPassword(supervisor.getUsername());
        supervisor.setRole(roleService.findOne((long)1));
        this.supervisorService.addSupervisor(supervisor);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supervisor", supervisor);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllSupervisors", method = RequestMethod.GET)
    public List<Supervisor> findSupervisor()throws Exception {
        List<Supervisor> supervisors = supervisorService.findAllT();
        return supervisors;
    }


    //实现分页
    @RequestMapping(value = "/displayAllSupervisors", method = RequestMethod.GET)
    public Map<String, Object> findAllSupervisor(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<Supervisor> list = this.supervisorService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.supervisorService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateSupervisor", method = RequestMethod.PUT)
    public Map<String, Object> updateSupervisor(@RequestBody Supervisor supervisor)throws Exception {
        this.supervisorService.updateSupervisor(supervisor);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supervisor", supervisor);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteSupervisor", method = RequestMethod.DELETE)
    public void deleteSupervisor(@RequestParam("id") Long id)throws Exception {
        this.supervisorService.deleteSupervisor(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteSupervisors",method = RequestMethod.DELETE)
    public void deleteSupervisors(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.supervisorService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
