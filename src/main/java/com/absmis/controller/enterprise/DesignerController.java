package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.Designer;
import com.absmis.service.authority.RoleService;
import com.absmis.service.enterprise.CheckedStatusService;
import com.absmis.service.enterprise.DesignerService;
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
public class DesignerController {
    @Autowired
    DesignerService designerService;
    @Autowired
    RoleService roleService;
    @Autowired
    CheckedStatusService checkedStatusService;

    //根据企业名称模糊查询
    @RequestMapping(value = "/queryDesignerByName", method = RequestMethod.GET)
    public Map<String, Object> queryDesignerByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<Designer> specification = this.designerService.queryName(query);
        Page<Designer> list = this.designerService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.designerService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }
    //添加
    @RequestMapping(value = "/addDesigner", method = RequestMethod.POST)
    public Map<String, Object> addDesigner(@RequestBody Designer designer)throws Exception {
        designer.setPassword(designer.getUsername());
        designer.setRole(roleService.findOne((long)4));
        this.designerService.addDesigner(designer);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("designer", designer);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllDesigners", method = RequestMethod.GET)
    public List<Designer> findDesigner()throws Exception {
        List<Designer> designers = designerService.findAllT();
        return designers;
    }

    //非传统企业审核信息    完成 改
    @RequestMapping(value = "/checkDesigner", method = RequestMethod.POST)
    public Map<String, Object> checkDesigner(
            @RequestParam("id") Long id,
            @RequestParam("checkedStatusId") Long checkedId
    )throws Exception {
        Designer designer = designerService.findOne(id);
        designer.setCheckedStatus(checkedStatusService.findOne(checkedId));
        this.designerService.update(designer);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("designer", designer);
        return map;
    }

    //实现分页
    @RequestMapping(value = "/displayAllDesigners", method = RequestMethod.GET)
    public Map<String, Object> findAllDesigner(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<Designer> list = this.designerService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.designerService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateDesigner", method = RequestMethod.PUT)
    public Map<String, Object> updateDesigner(@RequestBody Designer designer)throws Exception {
        this.designerService.updateDesigner(designer);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("designer", designer);
        return map;
    }

    //单个删除   完成 删
    @RequestMapping(value = "/deleteDesigner", method = RequestMethod.DELETE)
    public void deleteDesigner(@RequestParam("id") Long id)throws Exception {
        this.designerService.deleteDesigner(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteDesigners",method = RequestMethod.DELETE)
    public void deleteDesigners(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.designerService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
