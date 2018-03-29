package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.MachineryEn;
import com.absmis.domain.enterprise.MachineryEnIndustrialization;
import com.absmis.domain.message.MachineryEnStatistics;
import com.absmis.service.authority.RoleService;
import com.absmis.service.enterprise.CheckedStatusService;
import com.absmis.service.enterprise.MachineryEnIndustrializationService;
import com.absmis.service.enterprise.MachineryEnService;
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
public class MachineryEnController {
    @Autowired
    MachineryEnService machineryEnService;
    @Autowired
    RoleService roleService;
    @Autowired
    CheckedStatusService checkedStatusService;
    @Autowired
    MachineryEnIndustrializationService machineryEnIndustrializationService;

    /**
     * 统计行业信息1-机具设备企业产业化信息
     */
    @RequestMapping(value = "/getAllMachineryEns", method = RequestMethod.GET)
    public Map<String, Object> getMachineryEns(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size
    )throws Exception {
        List<MachineryEn> machineryEns = machineryEnService.findAllT();
        List<MachineryEnStatistics> machineryEnStatisticses = new ArrayList<>();
        for(int i=0;i<machineryEns.size();i++){
            System.out.println(machineryEns.size());
            MachineryEnIndustrialization machineryEnIndustrialization = machineryEnIndustrializationService.getByMachineryEnIdAndYearAndQuarter(machineryEns.get(i).getId(),year,quarter);
            if(machineryEnIndustrialization!=null){
                MachineryEnStatistics integralWall = new MachineryEnStatistics(machineryEns.get(i).getName(),"预制混凝土生产设备",machineryEnIndustrialization.getIntegralWall());
                machineryEnStatisticses.add(integralWall);
                MachineryEnStatistics specialTransportEquipment = new MachineryEnStatistics(machineryEns.get(i).getName(),"专用运输设备",machineryEnIndustrialization.getSpecialTransportEquipment());
                machineryEnStatisticses.add(specialTransportEquipment);
                MachineryEnStatistics specialConstructionEquipment = new MachineryEnStatistics(machineryEns.get(i).getName(),"专用施工",machineryEnIndustrialization.getSpecialConstructionEquipment());
                machineryEnStatisticses.add(specialConstructionEquipment);
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        //查到的总用户数
        map.put("total", machineryEnStatisticses.size());

        //总页数
        int pageTimes;
        if (machineryEnStatisticses.size() % size == 0) {
            pageTimes = machineryEnStatisticses.size() / size;
        } else {
            pageTimes = machineryEnStatisticses.size() / size + 1;
        }
        map.put("pageTimes", pageTimes);

        List<MachineryEnStatistics> newMachineryEnStatistics = new ArrayList<MachineryEnStatistics>();
        //每页开始的第几条记录
        if(pageTimes==page) {
            newMachineryEnStatistics.addAll(machineryEnStatisticses.subList((page-1)*size,machineryEnStatisticses.size()));
        }else {
            newMachineryEnStatistics.addAll(machineryEnStatisticses.subList((page-1)*size,(page-1)*size+size));
        }
        map.put("rows", newMachineryEnStatistics);
        return map;
    }

    /**
     * 获取到所有
     */
//    @RequestMapping(value = "/getAllMachineryEnIndustrializations", method = RequestMethod.GET)
//    public List<MachineryEn> getMachineryEnIndustrialization()throws Exception {
//        Specification<MachineryEn> specification = this.machineryEnService.queryAnnual("integralWall",2017,3);
//        List<MachineryEn> machineryEnIndustrializations = machineryEnService.findBySepc(specification);
//        return machineryEnIndustrializations;
//    }
    //根据企业名称模糊查询
    @RequestMapping(value = "/queryMachineryEnByName", method = RequestMethod.GET)
    public Map<String, Object> queryMachineryEnByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<MachineryEn> specification = this.machineryEnService.queryName(query);
        Page<MachineryEn> list = this.machineryEnService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.machineryEnService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }
    //添加
    @RequestMapping(value = "/addMachineryEn", method = RequestMethod.POST)
    public Map<String, Object> addMachineryEn(@RequestBody MachineryEn machineryEn)throws Exception {
        machineryEn.setPassword(machineryEn.getUsername());
        machineryEn.setRole(roleService.findOne((long)6));
        this.machineryEnService.addMachineryEn(machineryEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("machineryEn", machineryEn);
        return map;
    }
    //非传统企业审核信息    完成 改
    @RequestMapping(value = "/checkMachineryEn", method = RequestMethod.POST)
    public Map<String, Object> checkMachineryEn(
            @RequestParam("id") Long id,
            @RequestParam("checkedStatusId") Long checkedId
    )throws Exception {
        MachineryEn machineryEn = machineryEnService.findOne(id);
        machineryEn.setCheckedStatus(checkedStatusService.findOne(checkedId));
        this.machineryEnService.update(machineryEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("machineryEn", machineryEn);
        return map;
    }
    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllMachineryEns", method = RequestMethod.GET)
    public List<MachineryEn> findMachineryEn()throws Exception {
        List<MachineryEn> machineryEns = machineryEnService.findAllT();
        return machineryEns;
    }


    //实现分页
    @RequestMapping(value = "/displayAllMachineryEns", method = RequestMethod.GET)
    public Map<String, Object> findAllMachineryEn(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<MachineryEn> list = this.machineryEnService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.machineryEnService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateMachineryEn", method = RequestMethod.PUT)
    public Map<String, Object> updateMachineryEn(@RequestBody MachineryEn machineryEn)throws Exception {
        this.machineryEnService.updateMachineryEn(machineryEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("machineryEn", machineryEn);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteMachineryEn", method = RequestMethod.DELETE)
    public void deleteMachineryEn(@RequestParam("id") Long id)throws Exception {
        this.machineryEnService.deleteMachineryEn(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteMachineryEns",method = RequestMethod.DELETE)
    public void deleteMachineryEns(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.machineryEnService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
