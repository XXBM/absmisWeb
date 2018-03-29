package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.ComponentEn;
import com.absmis.domain.enterprise.ComponentEnIndustrialization;
import com.absmis.domain.enterprise.SubUnitEn;
import com.absmis.domain.enterprise.SubUnitEnIndustrialization;
import com.absmis.domain.message.SubUnitAndComponentEnStatistics;
import com.absmis.service.authority.RoleService;
import com.absmis.service.enterprise.*;
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
public class SubUnitEnController {
    @Autowired
    SubUnitEnService subUnitEnService;
    @Autowired
    RoleService roleService;
    @Autowired
    CheckedStatusService checkedStatusService;
    @Autowired
    ComponentEnService componentEnService;
    @Autowired
    SubUnitEnIndustrializationService subUnitEnIndustrializationService;
    @Autowired
    ComponentEnIndustrializationService componentEnIndustrializationService;
    /**
     * 统计行业信息1-构件部品企业产业化信息
     */
    @RequestMapping(value = "/getAllSubUnitAndComponentEns", method = RequestMethod.GET)
    public Map<String, Object> getMachineryEns(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size
    )throws Exception {
        List<SubUnitEn> subUnitEns = subUnitEnService.findAllT();
        List<SubUnitAndComponentEnStatistics> subUnitAndComponentEnStatisticses = new ArrayList<>();
        for(int i=0;i<subUnitEns.size();i++){
            SubUnitEnIndustrialization subUnitEnIndustrialization = subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(subUnitEns.get(i).getId(),year,quarter);
            if(subUnitEnIndustrialization!=null){
                Specification<SubUnitEnIndustrialization> spIntegralWall = this.subUnitEnIndustrializationService.queryAnnual(subUnitEns.get(i).getId(),year,quarter);
                List<SubUnitEnIndustrialization> listIntegralWall = subUnitEnIndustrializationService.findBySepc(spIntegralWall);
                double totalIntegralWallScale = 0;
                for(int x=0;x<listIntegralWall.size();x++){
                    totalIntegralWallScale += listIntegralWall.get(x).getIntegralWallScale();
                }
                SubUnitAndComponentEnStatistics integralWallNum = new SubUnitAndComponentEnStatistics(subUnitEns.get(i).getName(),"整体墙板",subUnitEnIndustrialization.getIntegralWallNum(),subUnitEnIndustrialization.getIntegralWallAbility(),totalIntegralWallScale);
                subUnitAndComponentEnStatisticses.add(integralWallNum);
                Specification<SubUnitEnIndustrialization> spIntegrativeExternalWall = this.subUnitEnIndustrializationService.queryAnnual(subUnitEns.get(i).getId(),year,quarter);
                List<SubUnitEnIndustrialization> listIntegrativeExternalWall = subUnitEnIndustrializationService.findBySepc(spIntegrativeExternalWall);
                double totalIntegrativeExternalWallScale = 0;
                for(int x=0;x<listIntegrativeExternalWall.size();x++){
                    totalIntegrativeExternalWallScale += listIntegrativeExternalWall.get(x).getIntegrativeExternalWallScale();
                }
                SubUnitAndComponentEnStatistics integrativeExternalWallNum = new SubUnitAndComponentEnStatistics(subUnitEns.get(i).getName(),"结构保温装饰一体化外墙",subUnitEnIndustrialization.getIntegrativeExternalWallNum(),subUnitEnIndustrialization.getIntegrativeExternalWallAbility(),totalIntegrativeExternalWallScale);
                subUnitAndComponentEnStatisticses.add(integrativeExternalWallNum);
                Specification<SubUnitEnIndustrialization> spPrebuiltStairs = this.subUnitEnIndustrializationService.queryAnnual(subUnitEns.get(i).getId(),year,quarter);
                List<SubUnitEnIndustrialization> listPrebuiltStairs = subUnitEnIndustrializationService.findBySepc(spPrebuiltStairs);
                double totalPrebuiltStairsScale = 0;
                for(int x=0;x<listPrebuiltStairs.size();x++){
                    totalPrebuiltStairsScale += listPrebuiltStairs.get(x).getPrebuiltStairsScale();
                }
                SubUnitAndComponentEnStatistics prebuiltStairsNum = new SubUnitAndComponentEnStatistics(subUnitEns.get(i).getName(),"预制楼梯",subUnitEnIndustrialization.getPrebuiltStairsNum(),subUnitEnIndustrialization.getPrebuiltStairsAbility(),totalPrebuiltStairsScale);
                subUnitAndComponentEnStatisticses.add(prebuiltStairsNum);
                Specification<SubUnitEnIndustrialization> spIntegralKitchen = this.subUnitEnIndustrializationService.queryAnnual(subUnitEns.get(i).getId(),year,quarter);
                List<SubUnitEnIndustrialization> listIntegralKitchen = subUnitEnIndustrializationService.findBySepc(spIntegralKitchen);
                double totalIntegralKitchenScale = 0;
                for(int x=0;x<listIntegralKitchen.size();x++){
                    totalIntegralKitchenScale += listIntegralKitchen.get(x).getIntegralKitchenScale();
                }
                SubUnitAndComponentEnStatistics integralKitchenNum = new SubUnitAndComponentEnStatistics(subUnitEns.get(i).getName(),"整体厨房",subUnitEnIndustrialization.getIntegralKitchenNum(),subUnitEnIndustrialization.getIntegralKitchenAbility(),totalIntegralKitchenScale);
                subUnitAndComponentEnStatisticses.add(integralKitchenNum);
                Specification<SubUnitEnIndustrialization> spIntegralToilet = this.subUnitEnIndustrializationService.queryAnnual(subUnitEns.get(i).getId(),year,quarter);
                List<SubUnitEnIndustrialization> listIntegralToilet = subUnitEnIndustrializationService.findBySepc(spIntegralToilet);
                double totalIntegralToiletScale = 0;
                for(int x=0;x<listIntegralToilet.size();x++){
                    totalIntegralToiletScale += listIntegralToilet.get(x).getIntegralToiletScale();
                }
                SubUnitAndComponentEnStatistics integralToiletNum = new SubUnitAndComponentEnStatistics(subUnitEns.get(i).getName(),"整体卫生间",subUnitEnIndustrialization.getIntegralToiletNum(),subUnitEnIndustrialization.getIntegralToiletAbility(),totalIntegralToiletScale);
                subUnitAndComponentEnStatisticses.add(integralToiletNum);
                Specification<SubUnitEnIndustrialization> spIntegralInteriorDecoration = this.subUnitEnIndustrializationService.queryAnnual(subUnitEns.get(i).getId(),year,quarter);
                List<SubUnitEnIndustrialization> listIntegralInteriorDecoration = subUnitEnIndustrializationService.findBySepc(spIntegralInteriorDecoration);
                double totalIntegralInteriorDecorationScale = 0;
                for(int x=0;x<listIntegralInteriorDecoration.size();x++){
                    totalIntegralInteriorDecorationScale += listIntegralInteriorDecoration.get(x).getIntegralInteriorDecorationScale();
                }
                SubUnitAndComponentEnStatistics integralInteriorDecorationNum = new SubUnitAndComponentEnStatistics(subUnitEns.get(i).getName(),"整体内装体系",subUnitEnIndustrialization.getIntegralInteriorDecorationNum(),subUnitEnIndustrialization.getIntegralInteriorDecorationAbility(),totalIntegralInteriorDecorationScale);
                subUnitAndComponentEnStatisticses.add(integralInteriorDecorationNum);
            }
        }
        List<ComponentEn> componentEns = componentEnService.findAllT();
        for(int i=0;i<componentEns.size();i++){
            ComponentEnIndustrialization componentEnIndustrialization = componentEnIndustrializationService.getByComponentEnIdAndYearAndQuarter(componentEns.get(i).getId(),year,quarter);
            if(componentEnIndustrialization!=null){
                Specification<ComponentEnIndustrialization> spPrebuiltConcrete = this.componentEnIndustrializationService.queryAnnual(componentEns.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> listPrebuiltConcrete = componentEnIndustrializationService.findBySepc(spPrebuiltConcrete);
                double totalPrebuiltConcreteScale = 0;
                for(int x=0;x<listPrebuiltConcrete.size();x++){
                    totalPrebuiltConcreteScale += listPrebuiltConcrete.get(x).getPrebuiltConcreteScale();
                }
                SubUnitAndComponentEnStatistics prebuiltConcrete = new SubUnitAndComponentEnStatistics(componentEns.get(i).getName(),"预制装配混凝土结构",componentEnIndustrialization.getPrebuiltConcreteNum(),componentEnIndustrialization.getPrebuiltConcreteAbility(),totalPrebuiltConcreteScale);
                subUnitAndComponentEnStatisticses.add(prebuiltConcrete);
                Specification<ComponentEnIndustrialization> spPrebuiltSteel = this.componentEnIndustrializationService.queryAnnual(componentEns.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> listPrebuiltSteel = componentEnIndustrializationService.findBySepc(spPrebuiltSteel);
                double totalPrebuiltSteelScale = 0;
                for(int x=0;x<listPrebuiltSteel.size();x++){
                    totalPrebuiltSteelScale += listPrebuiltSteel.get(x).getPrebuiltSteelScale();
                }
                SubUnitAndComponentEnStatistics prebuiltSteel = new SubUnitAndComponentEnStatistics(componentEns.get(i).getName(),"钢结构",componentEnIndustrialization.getPrebuiltSteelNum(),componentEnIndustrialization.getPrebuiltSteelAbility(),totalPrebuiltSteelScale);
                subUnitAndComponentEnStatisticses.add(prebuiltSteel);
                Specification<ComponentEnIndustrialization> spPrebuiltTimber = this.componentEnIndustrializationService.queryAnnual(componentEns.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> listPrebuiltTimber = componentEnIndustrializationService.findBySepc(spPrebuiltTimber);
                double totalPrebuiltTimberScale = 0;
                for(int x=0;x<listPrebuiltTimber.size();x++){
                    totalPrebuiltTimberScale += listPrebuiltTimber.get(x).getPrebuiltTimberScale();
                }
                SubUnitAndComponentEnStatistics prebuiltTimber = new SubUnitAndComponentEnStatistics(componentEns.get(i).getName(),"木结构",componentEnIndustrialization.getPrebuiltTimberNum(),componentEnIndustrialization.getPrebuiltTimberAbility(),totalPrebuiltTimberScale);
                subUnitAndComponentEnStatisticses.add(prebuiltTimber);
                Specification<ComponentEnIndustrialization> spPrebuiltOther = this.componentEnIndustrializationService.queryAnnual(componentEns.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> listPrebuiltOther = componentEnIndustrializationService.findBySepc(spPrebuiltOther);
                double totalPrebuiltOtherScale = 0;
                for(int x=0;x<listPrebuiltOther.size();x++){
                    totalPrebuiltOtherScale += listPrebuiltOther.get(x).getPrebuiltOtherScale();
                }
                SubUnitAndComponentEnStatistics prebuiltOther = new SubUnitAndComponentEnStatistics(componentEns.get(i).getName(),"其他结构的构件",componentEnIndustrialization.getPrebuiltOtherNum(),componentEnIndustrialization.getPrebuiltOtherAbility(),totalPrebuiltOtherScale);
                subUnitAndComponentEnStatisticses.add(prebuiltOther);
            }

        }

        Map<String, Object> map = new HashMap<String, Object>();
        //查到的总用户数
        map.put("total", subUnitAndComponentEnStatisticses.size());

        //总页数
        int pageTimes;
        if (subUnitAndComponentEnStatisticses.size() % size == 0) {
            pageTimes = subUnitAndComponentEnStatisticses.size() / size;
        } else {
            pageTimes = subUnitAndComponentEnStatisticses.size() / size + 1;
        }
        map.put("pageTimes", pageTimes);

        List<SubUnitAndComponentEnStatistics> newSubUnitAndComponentEnStatistics = new ArrayList<SubUnitAndComponentEnStatistics>();
        //每页开始的第几条记录
        if(pageTimes==page) {
            newSubUnitAndComponentEnStatistics.addAll(subUnitAndComponentEnStatisticses.subList((page-1)*size,subUnitAndComponentEnStatisticses.size()));
        }else {
            newSubUnitAndComponentEnStatistics.addAll(subUnitAndComponentEnStatisticses.subList((page-1)*size,(page-1)*size+size));
        }
        map.put("rows", newSubUnitAndComponentEnStatistics);
        return map;
    }

    //根据企业名称模糊查询
    @RequestMapping(value = "/querySubUnitEnByName", method = RequestMethod.GET)
    public Map<String, Object> querySubUnitEnByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<SubUnitEn> specification = this.subUnitEnService.queryName(query);
        Page<SubUnitEn> list = this.subUnitEnService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.subUnitEnService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }
    //添加
    @RequestMapping(value = "/addSubUnitEn", method = RequestMethod.POST)
    public Map<String, Object> addSubUnitEn(@RequestBody SubUnitEn subUnitEn)throws Exception {
        subUnitEn.setPassword(subUnitEn.getUsername());
        subUnitEn.setRole(roleService.findOne((long)7));
        this.subUnitEnService.addSubUnitEn(subUnitEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subUnitEn", subUnitEn);
        return map;
    }
    //非传统企业审核信息    完成 改
    @RequestMapping(value = "/checkSubUnitEn", method = RequestMethod.POST)
    public Map<String, Object> checkSubUnitEn(
            @RequestParam("id") Long id,
            @RequestParam("checkedStatusId") Long checkedId
    )throws Exception {
        SubUnitEn subUnitEn = subUnitEnService.findOne(id);
        subUnitEn.setCheckedStatus(checkedStatusService.findOne(checkedId));
        this.subUnitEnService.update(subUnitEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subUnitEn", subUnitEn);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllSubUnitEns", method = RequestMethod.GET)
    public List<SubUnitEn> findSubUnitEn()throws Exception {
        List<SubUnitEn> subUnitEns = subUnitEnService.findAllT();
        return subUnitEns;
    }


    //实现分页
    @RequestMapping(value = "/displayAllSubUnitEns", method = RequestMethod.GET)
    public Map<String, Object> findAllSubUnitEn(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<SubUnitEn> list = this.subUnitEnService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.subUnitEnService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateSubUnitEn", method = RequestMethod.PUT)
    public Map<String, Object> updateSubUnitEn(@RequestBody SubUnitEn subUnitEn)throws Exception {
        this.subUnitEnService.updateSubUnitEn(subUnitEn);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subUnitEn", subUnitEn);
        return map;
    }

    //删除一个学院   完成 删
    @RequestMapping(value = "/deleteSubUnitEn", method = RequestMethod.DELETE)
    public void deleteSubUnitEn(@RequestParam("id") Long id)throws Exception {
        this.subUnitEnService.deleteSubUnitEn(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteSubUnitEns",method = RequestMethod.DELETE)
    public void deleteSubUnitEns(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.subUnitEnService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
