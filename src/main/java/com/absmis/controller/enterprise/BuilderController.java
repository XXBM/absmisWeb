package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.Builder;
import com.absmis.service.authority.RoleService;
import com.absmis.service.enterprise.BuilderService;
import com.absmis.service.enterprise.CheckedStatusService;
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
public class BuilderController {
    @Autowired
    BuilderService builderService;
    @Autowired
    RoleService roleService;
    @Autowired
    CheckedStatusService checkedStatusService;

    //根据企业名称模糊查询
    @RequestMapping(value = "/queryBuilderByName", method = RequestMethod.GET)
    public Map<String, Object> queryBuilderByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<Builder> specification = this.builderService.queryName(query);
        Page<Builder> list = this.builderService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.builderService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //添加
    @RequestMapping(value = "/addBuilder", method = RequestMethod.POST)
    public Map<String, Object> addBuilder(@RequestBody Builder builder)throws Exception {
        builder.setPassword(builder.getUsername());
        builder.setRole(roleService.findOne((long)5));
        this.builderService.addBuilder(builder);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("builder", builder);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllBuilders", method = RequestMethod.GET)
    public List<Builder> findBuilder()throws Exception {
        List<Builder> builders = builderService.findAllT();
        return builders;
    }

    //非传统企业审核信息    完成 改
    @RequestMapping(value = "/checkBuilder", method = RequestMethod.POST)
    public Map<String, Object> checkBuilder(
            @RequestParam("id") Long id,
            @RequestParam("checkedStatusId") Long checkedId
    )throws Exception {
        Builder builder = builderService.findOne(id);
        builder.setCheckedStatus(checkedStatusService.findOne(checkedId));
        this.builderService.update(builder);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("builder", builder);
        return map;
    }

    //实现分页
    @RequestMapping(value = "/displayAllBuilders", method = RequestMethod.GET)
    public Map<String, Object> findAllBuilder(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<Builder> list = this.builderService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.builderService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateBuilder", method = RequestMethod.PUT)
    public Map<String, Object> updateBuilder(@RequestBody Builder builder)throws Exception {
        this.builderService.updateBuilder(builder);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("builder", builder);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteBuilder", method = RequestMethod.DELETE)
    public void deleteBuilder(@RequestParam("id") Long id)throws Exception {
        this.builderService.deleteBuilder(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteBuilders",method = RequestMethod.DELETE)
    public void deleteBuilders(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.builderService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
