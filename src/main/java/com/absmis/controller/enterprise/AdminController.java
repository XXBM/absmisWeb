package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.Admin;
import com.absmis.service.authority.RoleService;
import com.absmis.service.enterprise.AdminService;
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
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;
    @Autowired
    CheckedStatusService checkedStatusService;

    //根据企业名称模糊查询
    @RequestMapping(value = "/queryAdminByName", method = RequestMethod.GET)
    public Map<String, Object> queryAdminByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<Admin> specification = this.adminService.queryName(query);
        Page<Admin> list = this.adminService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.adminService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //添加
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public Map<String, Object> addAdmin(@RequestBody Admin admin)throws Exception {
        admin.setPassword(admin.getUsername());
        admin.setRole(roleService.findOne((long)1));
        this.adminService.addAdmin(admin);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("admin", admin);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllAdmins", method = RequestMethod.GET)
    public List<Admin> findAdmin()throws Exception {
        List<Admin> admins = adminService.findAllT();
        return admins;
    }


    //实现分页
    @RequestMapping(value = "/displayAllAdmins", method = RequestMethod.GET)
    public Map<String, Object> findAllAdmin(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<Admin> list = this.adminService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.adminService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.PUT)
    public Map<String, Object> updateAdmin(@RequestBody Admin admin)throws Exception {
        this.adminService.updateAdmin(admin);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("admin", admin);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.DELETE)
    public void deleteAdmin(@RequestParam("id") Long id)throws Exception {
        this.adminService.deleteAdmin(id);
    }


    //批量删除   完成 删
    @RequestMapping(value = "/deleteAdmins",method = RequestMethod.DELETE)
    public void deleteAdmins(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.adminService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
