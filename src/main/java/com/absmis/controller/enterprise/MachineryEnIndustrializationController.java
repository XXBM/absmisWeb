package com.absmis.controller.enterprise;

import com.absmis.domain.authority.User;
import com.absmis.domain.enterprise.MachineryEn;
import com.absmis.domain.enterprise.MachineryEnIndustrialization;
import com.absmis.domain.message.MachineryEnInfo;
import com.absmis.service.authority.UserService;
import com.absmis.service.enterprise.MachineryEnIndustrializationService;
import com.absmis.service.enterprise.MachineryEnService;
import com.absmis.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MachineryEnIndustrializationController {
    @Autowired
    MachineryEnIndustrializationService machineryEnIndustrializationService;
    @Autowired
    MachineryEnService machineryEnService;
    @Autowired
    UserService userService;
    String username = null;
    User storedUser = null;

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/querytjMachineryEn", method = RequestMethod.GET)
    public List<MachineryEnInfo> queryMachineryEn(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception{
        Specification<MachineryEn> integralWallSp = this.machineryEnService.queryAnnual("integralWall",year,quarter);
        List<MachineryEn> integralWalls = machineryEnService.findBySepc(integralWallSp);
        double totalIntegralWall = 0;
        for(int i=0;i<integralWalls.size();i++){
            MachineryEnIndustrialization machineryEnIndustrialization = machineryEnIndustrializationService.getByMachineryEnIdAndYearAndQuarter(integralWalls.get(i).getId(),year,quarter);
            if(machineryEnIndustrialization!=null){
                totalIntegralWall += machineryEnIndustrialization.getIntegralWall();
            }
        }
        Specification<MachineryEn> specialTransportEquipmentSp = this.machineryEnService.queryAnnual("specialTransportEquipment",year,quarter);
        List<MachineryEn> specialTransportEquipments = machineryEnService.findBySepc(specialTransportEquipmentSp);
        double totalSpecialTransportEquipment = 0;
        for(int i=0;i<specialTransportEquipments.size();i++){
            MachineryEnIndustrialization machineryEnIndustrialization = machineryEnIndustrializationService.getByMachineryEnIdAndYearAndQuarter(specialTransportEquipments.get(i).getId(),year,quarter);
            if(machineryEnIndustrialization!=null){
                totalSpecialTransportEquipment += machineryEnIndustrialization.getSpecialTransportEquipment();
            }
        }
        Specification<MachineryEn> specialConstructionEquipmentSp = this.machineryEnService.queryAnnual("specialConstructionEquipment",year,quarter);
        List<MachineryEn> specialConstructionEquipments = machineryEnService.findBySepc(specialConstructionEquipmentSp);
        double totalSpecialConstructionEquipment = 0;
        for(int i=0;i<specialConstructionEquipments.size();i++){
            MachineryEnIndustrialization machineryEnIndustrialization = machineryEnIndustrializationService.getByMachineryEnIdAndYearAndQuarter(specialConstructionEquipments.get(i).getId(),year,quarter);
            if(machineryEnIndustrialization!=null){
                totalSpecialConstructionEquipment += machineryEnIndustrialization.getSpecialConstructionEquipment();
            }
        }
        List<MachineryEnInfo> machineryEnInfos = new ArrayList<>();
        MachineryEnInfo integralWallMachineryEnInfo = new MachineryEnInfo("预制混凝土生产设备情况",(double)integralWalls.size(),totalIntegralWall);
        machineryEnInfos.add(integralWallMachineryEnInfo);
        MachineryEnInfo steMachineryEnInfo = new MachineryEnInfo("专用运输设备生产情况",(double)specialConstructionEquipments.size(),totalSpecialTransportEquipment);
        machineryEnInfos.add(steMachineryEnInfo);
        MachineryEnInfo sceachineryEnInfo = new MachineryEnInfo("专用施工设备生产情况",(double)specialTransportEquipments.size(),totalSpecialConstructionEquipment);
        machineryEnInfos.add(sceachineryEnInfo);
        return machineryEnInfos;
    }



    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findA", method = RequestMethod.GET)
    public MachineryEnIndustrialization test()throws Exception {
        return machineryEnIndustrializationService.getByMachineryEnIdAndYearAndQuarter((long)131072,2017,3);
    }



    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryQuarterMachineryEnIn", method = RequestMethod.GET)
    public MachineryEnIndustrialization queryQuarter(
            @RequestParam(value = "enId") Long enId,
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception {
        return machineryEnIndustrializationService.getByMachineryEnIdAndYearAndQuarter(enId,year,quarter);
    }


    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryMachineryEnInCheck", method = RequestMethod.GET)
    public Map<String, Object> queryMachineryEnInCheck(
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Pageable pageable = new PageRequest(page-1,size);
        Specification<MachineryEnIndustrialization> specification = this.machineryEnIndustrializationService.queryEnIndustrialization(storedUser.getId(),startTime,endTime);
        Page<MachineryEnIndustrialization> list = this.machineryEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.machineryEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryMachineryEnIn", method = RequestMethod.GET)
    public Map<String, Object> queryMachineryEnIn(
            @RequestParam(value = "machineryEnName") String name,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<MachineryEnIndustrialization> specification = this.machineryEnIndustrializationService.queryIndustrialization(name,startTime,endTime);
        Page<MachineryEnIndustrialization> list = this.machineryEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.machineryEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //添加
    @RequestMapping(value = "/addMachineryEnIndustrialization", method = RequestMethod.POST)
    public Map<String, Object> addMachineryEnIndustrialization(@RequestBody MachineryEnIndustrialization machineryEnIndustrialization)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        machineryEnIndustrialization.setMachineryEn((MachineryEn) storedUser);
        machineryEnIndustrialization.setQuarterEnd(Utils.getQuarterEnd(machineryEnIndustrialization.getYear(),machineryEnIndustrialization.getQuarter()));
        this.machineryEnIndustrializationService.addMachineryEnIndustrialization(machineryEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("machineryEnIndustrialization", machineryEnIndustrialization);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllMachineryEnIndustrializations", method = RequestMethod.GET)
    public List<MachineryEnIndustrialization> findMachineryEnIndustrialization()throws Exception {
        List<MachineryEnIndustrialization> machineryEnIndustrializations = machineryEnIndustrializationService.findAllT();
        return machineryEnIndustrializations;
    }


    //实现分页
    @RequestMapping(value = "/displayAllMachineryEnIndustrializations", method = RequestMethod.GET)
    public Map<String, Object> findAllMachineryEnIndustrialization(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<MachineryEnIndustrialization> list = this.machineryEnIndustrializationService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.machineryEnIndustrializationService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    @RequestMapping(value = "/getAllMachineryEnIndustrializations", method = RequestMethod.GET)
    public Map<String, Object> getAllMachineryEnIndustrializations(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Page<MachineryEnIndustrialization> list = this.machineryEnIndustrializationService.findByMachineryEnId(storedUser.getId(),new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.machineryEnIndustrializationService.findByMachineryEnId(storedUser.getId()).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }


    //实现分页 审核查询
    @RequestMapping(value = "/displayAllMachineryEnIndustrializationsBySubmit", method = RequestMethod.GET)
    public Map<String, Object> findAllMachineryEnIndustrializationBySubmit(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<MachineryEnIndustrialization> list = this.machineryEnIndustrializationService.findBySubmit(true,new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.machineryEnIndustrializationService.findBySubmit(true).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateMachineryEnIndustrialization", method = RequestMethod.PUT)
    public Map<String, Object> updateMachineryEnIndustrialization(@RequestBody MachineryEnIndustrialization machineryEnIndustrialization)throws Exception {
        this.machineryEnIndustrializationService.updateMachineryEnIndustrialization(machineryEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("machineryEnIndustrialization", machineryEnIndustrialization);
        return map;
    }

    //单个删除   完成 删
    @RequestMapping(value = "/deleteMachineryEnIndustrialization", method = RequestMethod.DELETE)
    public void deleteMachineryEnIndustrialization(@RequestParam("id") Long id)throws Exception {
        this.machineryEnIndustrializationService.deleteMachineryEnIndustrialization(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteMachineryEnIndustrializations",method = RequestMethod.DELETE)
    public void deleteMachineryEnIndustrializations(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.machineryEnIndustrializationService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
