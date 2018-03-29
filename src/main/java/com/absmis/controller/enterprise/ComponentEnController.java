package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.ComponentEn;
import com.absmis.service.authority.RoleService;
import com.absmis.service.enterprise.CheckedStatusService;
import com.absmis.service.enterprise.ComponentEnService;
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
public class ComponentEnController {
    @Autowired
    ComponentEnService componentEnService;
    @Autowired
    RoleService roleService;
    @Autowired
    CheckedStatusService checkedStatusService;
    //根据企业名称模糊查询
    @RequestMapping(value = "/queryComponentEnByName", method = RequestMethod.GET)
    public Map<String, Object> queryComponentEnByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<ComponentEn> specification = this.componentEnService.queryName(query);
        Page<ComponentEn> list = this.componentEnService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.componentEnService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }
    //添加
    @RequestMapping(value = "/addComponentEn", method = RequestMethod.POST)
    public Map<String, Object> addComponentEn(@RequestBody ComponentEn componentEn)throws Exception {
        componentEn.setPassword(componentEn.getUsername());
        componentEn.setRole(roleService.findOne((long)8));
        this.componentEnService.addComponentEn(componentEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("componentEn", componentEn);
        return map;
    }

    //非传统企业审核信息    完成 改
    @RequestMapping(value = "/checkComponentEn", method = RequestMethod.POST)
    public Map<String, Object> checkComponentEn(
            @RequestParam("id") Long id,
            @RequestParam("checkedStatusId") Long checkedId
    )throws Exception {
        ComponentEn componentEn = componentEnService.findOne(id);
        componentEn.setCheckedStatus(checkedStatusService.findOne(checkedId));
        this.componentEnService.update(componentEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("componentEn", componentEn);
        return map;
    }
    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllComponentEns", method = RequestMethod.GET)
    public List<ComponentEn> findComponentEn()throws Exception {
        List<ComponentEn> componentEns = componentEnService.findAllT();
        return componentEns;
    }


    //实现分页
    @RequestMapping(value = "/displayAllComponentEns", method = RequestMethod.GET)
    public Map<String, java.lang.Object> findAllComponentEn(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<ComponentEn> list = this.componentEnService.findAllT(new PageRequest(page - 1, size));
        Map<String, java.lang.Object> map = new HashMap<String, java.lang.Object>();
        int total = this.componentEnService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateComponentEn", method = RequestMethod.PUT)
    public Map<String, java.lang.Object> updateComponentEn(@RequestBody ComponentEn componentEn)throws Exception {
        this.componentEnService.updateComponentEn(componentEn);
        Map<String, java.lang.Object> map = new HashMap<String, java.lang.Object>();
        map.put("componentEn", componentEn);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteComponentEn", method = RequestMethod.DELETE)
    public void deleteComponentEn(@RequestParam("id") Long id)throws Exception {
        this.componentEnService.deleteComponentEn(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteComponentEns",method = RequestMethod.DELETE)
    public void deleteComponentEns(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.componentEnService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
