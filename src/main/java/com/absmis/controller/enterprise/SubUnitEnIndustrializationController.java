package com.absmis.controller.enterprise;

import com.absmis.domain.authority.User;
import com.absmis.domain.enterprise.SubUnitEn;
import com.absmis.domain.enterprise.SubUnitEnIndustrialization;
import com.absmis.domain.message.SubUnitAndComponentEnInfo;
import com.absmis.service.authority.UserService;
import com.absmis.service.enterprise.SubUnitEnIndustrializationService;
import com.absmis.service.enterprise.SubUnitEnService;
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
public class SubUnitEnIndustrializationController {
    @Autowired
    SubUnitEnIndustrializationService subUnitEnIndustrializationService;
    @Autowired
    SubUnitEnService subUnitEnService;
    @Autowired
    UserService userService;
    String username = null;
    User storedUser = null;

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/querytjSubUnitEn", method = RequestMethod.GET)
    public List<SubUnitAndComponentEnInfo> querySubUnitEn(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception{
        Specification<SubUnitEn> integralWallSp = this.subUnitEnService.queryAnnual("integralWallNum",year,quarter);
        List<SubUnitEn> integralWalls = subUnitEnService.findBySepc(integralWallSp);
        double totalIntegralWallNum = 0;
        double totalIntegralWallAbility = 0;
        double totalIntegralWallScale = 0;
        for(int i=0;i<integralWalls.size();i++){
            SubUnitEnIndustrialization subUnitEnIndustrialization = subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(integralWalls.get(i).getId(),year,quarter);
            totalIntegralWallNum += subUnitEnIndustrialization.getIntegralWallNum();
            totalIntegralWallAbility += subUnitEnIndustrialization.getIntegralWallAbility();
            Specification<SubUnitEnIndustrialization> sp = this.subUnitEnIndustrializationService.queryAnnual(integralWalls.get(i).getId(),year,quarter);
            List<SubUnitEnIndustrialization> list = subUnitEnIndustrializationService.findBySepc(sp);
            for(int x=0;x<list.size();x++){
                totalIntegralWallScale += list.get(x).getIntegralWallScale();
            }
        }
        Specification<SubUnitEn> integrativeExternalWallSp = this.subUnitEnService.queryAnnual("integrativeExternalWallNum",year,quarter);
        List<SubUnitEn> integrativeExternalWalls = subUnitEnService.findBySepc(integrativeExternalWallSp);
        double totalIntegrativeExternalWallNum = 0;
        double totalIntegrativeExternalWallAbility = 0;
        double totalIntegrativeExternalWallScale = 0;
        for(int i=0;i<integrativeExternalWalls.size();i++){
            SubUnitEnIndustrialization subUnitEnIndustrialization = subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(integrativeExternalWalls.get(i).getId(),year,quarter);
            totalIntegrativeExternalWallNum += subUnitEnIndustrialization.getIntegrativeExternalWallNum();
            totalIntegrativeExternalWallAbility += subUnitEnIndustrialization.getIntegrativeExternalWallAbility();
            Specification<SubUnitEnIndustrialization> sp = this.subUnitEnIndustrializationService.queryAnnual(integrativeExternalWalls.get(i).getId(),year,quarter);
            List<SubUnitEnIndustrialization> list = subUnitEnIndustrializationService.findBySepc(sp);
            for(int x=0;x<list.size();x++){
                totalIntegrativeExternalWallScale += list.get(x).getIntegrativeExternalWallScale();
            }
        }
        Specification<SubUnitEn> prebuiltStairsSp = this.subUnitEnService.queryAnnual("prebuiltStairsNum",year,quarter);
        List<SubUnitEn> prebuiltStairses = subUnitEnService.findBySepc(prebuiltStairsSp);
        double totalPrebuiltStairsSum = 0;
        double totalprebuiltStairsAbility = 0;
        double totalPrebuiltStairsScale = 0;
        for(int i=0;i<prebuiltStairses.size();i++){
            SubUnitEnIndustrialization subUnitEnIndustrialization = subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(prebuiltStairses.get(i).getId(),year,quarter);
            totalPrebuiltStairsSum += subUnitEnIndustrialization.getPrebuiltStairsNum();
            totalprebuiltStairsAbility += subUnitEnIndustrialization.getPrebuiltStairsAbility();
            Specification<SubUnitEnIndustrialization> sp = this.subUnitEnIndustrializationService.queryAnnual(prebuiltStairses.get(i).getId(),year,quarter);
            List<SubUnitEnIndustrialization> list = subUnitEnIndustrializationService.findBySepc(sp);
            for(int x=0;x<list.size();x++){
                totalPrebuiltStairsScale += list.get(x).getPrebuiltStairsScale();
            }
        }
        Specification<SubUnitEn> integralKitchenSp = this.subUnitEnService.queryAnnual("integralKitchenNum",year,quarter);
        List<SubUnitEn> integralKitchens = subUnitEnService.findBySepc(integralKitchenSp);
        double totalIntegralKitchenNum = 0;
        double totalIntegralKitchenAbility = 0;
        double totalIntegralKitchenScale = 0;
        for(int i=0;i<integralKitchens.size();i++){
            SubUnitEnIndustrialization subUnitEnIndustrialization = subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(integralKitchens.get(i).getId(),year,quarter);
            totalIntegralKitchenNum += subUnitEnIndustrialization.getIntegralKitchenNum();
            totalIntegralKitchenAbility += subUnitEnIndustrialization.getIntegralKitchenAbility();
            Specification<SubUnitEnIndustrialization> sp = this.subUnitEnIndustrializationService.queryAnnual(integralKitchens.get(i).getId(),year,quarter);
            List<SubUnitEnIndustrialization> list = subUnitEnIndustrializationService.findBySepc(sp);
            for(int x=0;x<list.size();x++){
                totalIntegralKitchenScale += list.get(x).getIntegralKitchenScale();
            }
        }
        Specification<SubUnitEn> integralToiletSp = this.subUnitEnService.queryAnnual("integralToiletNum",year,quarter);
        List<SubUnitEn> integralToilets = subUnitEnService.findBySepc(integralToiletSp);
        double totalIntegralToiletNum = 0;
        double totalIntegralToiletAbility = 0;
        double totalIntegralToiletScale = 0;
        for(int i=0;i<integralToilets.size();i++){
            SubUnitEnIndustrialization subUnitEnIndustrialization = subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(integralToilets.get(i).getId(),year,quarter);
            totalIntegralToiletNum += subUnitEnIndustrialization.getIntegralToiletNum();
            totalIntegralToiletAbility += subUnitEnIndustrialization.getIntegralToiletAbility();
            Specification<SubUnitEnIndustrialization> sp = this.subUnitEnIndustrializationService.queryAnnual(integralToilets.get(i).getId(),year,quarter);
            List<SubUnitEnIndustrialization> list = subUnitEnIndustrializationService.findBySepc(sp);
            for(int x=0;x<list.size();x++){
                totalIntegralToiletScale += list.get(x).getIntegralToiletScale();
            }
        }
        Specification<SubUnitEn> integralInteriorDecorationSp = this.subUnitEnService.queryAnnual("integralInteriorDecorationNum",year,quarter);
        List<SubUnitEn> integralInteriorDecorations = subUnitEnService.findBySepc(integralInteriorDecorationSp);
        double totalIntegralInteriorDecorationNum = 0;
        double totalIntegralInteriorDecorationAbility = 0;
        double totalIntegralInteriorDecorationScale = 0;
        for(int i=0;i<integralInteriorDecorations.size();i++){
            SubUnitEnIndustrialization subUnitEnIndustrialization = subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(integralInteriorDecorations.get(i).getId(),year,quarter);
            totalIntegralInteriorDecorationNum += subUnitEnIndustrialization.getIntegralInteriorDecorationNum();
            totalIntegralInteriorDecorationAbility += subUnitEnIndustrialization.getIntegralInteriorDecorationAbility();
            Specification<SubUnitEnIndustrialization> sp = this.subUnitEnIndustrializationService.queryAnnual(integralInteriorDecorations.get(i).getId(),year,quarter);
            List<SubUnitEnIndustrialization> list = subUnitEnIndustrializationService.findBySepc(sp);
            for(int x=0;x<list.size();x++){
                totalIntegralInteriorDecorationScale += list.get(x).getIntegralInteriorDecorationScale();
            }
        }
        List<SubUnitAndComponentEnInfo> subUnitAndComponentEnInfos = new ArrayList<>();
        SubUnitAndComponentEnInfo integralWallEnInfo = new SubUnitAndComponentEnInfo("整体墙板生产情况",(double)integralWalls.size(),totalIntegralWallNum,totalIntegralWallAbility,totalIntegralWallScale);
        subUnitAndComponentEnInfos.add(integralWallEnInfo);
        SubUnitAndComponentEnInfo integrativeExternalWallEnInfo = new SubUnitAndComponentEnInfo("结构保温装饰一体化外墙生产情况",(double)integrativeExternalWalls.size(),totalIntegrativeExternalWallNum,totalIntegrativeExternalWallAbility,totalIntegrativeExternalWallScale);
        subUnitAndComponentEnInfos.add(integrativeExternalWallEnInfo);
        SubUnitAndComponentEnInfo prebuiltStairsEnInfo = new SubUnitAndComponentEnInfo("预制楼梯生产情况",(double)prebuiltStairses.size(),totalPrebuiltStairsSum,totalprebuiltStairsAbility,totalPrebuiltStairsScale);
        subUnitAndComponentEnInfos.add(prebuiltStairsEnInfo);
        SubUnitAndComponentEnInfo integralKitchenEnInfo = new SubUnitAndComponentEnInfo("整体厨房生产情况",(double)integralKitchens.size(),totalIntegralKitchenNum,totalIntegralKitchenAbility,totalIntegralKitchenScale);
        subUnitAndComponentEnInfos.add(integralKitchenEnInfo);
        SubUnitAndComponentEnInfo integralToiletEnInfo = new SubUnitAndComponentEnInfo("整体卫生间生产情况",(double)integralToilets.size(),totalIntegralToiletNum,totalIntegralToiletAbility,totalIntegralToiletScale);
        subUnitAndComponentEnInfos.add(integralToiletEnInfo);
        SubUnitAndComponentEnInfo integralInteriorDecorationEnInfo = new SubUnitAndComponentEnInfo("整体内装体系生产情况",(double)integralInteriorDecorations.size(),totalIntegralInteriorDecorationNum,totalIntegralInteriorDecorationAbility,totalIntegralInteriorDecorationScale);
        subUnitAndComponentEnInfos.add(integralInteriorDecorationEnInfo);
        return subUnitAndComponentEnInfos;
    }



    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryQuarterSubUnitEnIn", method = RequestMethod.GET)
    public SubUnitEnIndustrialization queryQuarter(
            @RequestParam(value = "enId") Long enId,
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception {
        return subUnitEnIndustrializationService.getBySubUnitEnIdAndYearAndQuarter(enId,year,quarter);
    }

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/querySubUnitEnInCheck", method = RequestMethod.GET)
    public Map<String, Object> querySubUnitEnIn(
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Pageable pageable = new PageRequest(page-1,size);
        Specification<SubUnitEnIndustrialization> specification = this.subUnitEnIndustrializationService.queryEnIndustrialization(storedUser.getId(),startTime,endTime);
        Page<SubUnitEnIndustrialization> list = this.subUnitEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.subUnitEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/querySubUnitEnIn", method = RequestMethod.GET)
    public Map<String, Object> querySubUnitEnIn(
            @RequestParam(value = "subUnitEnName") String name,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<SubUnitEnIndustrialization> specification = this.subUnitEnIndustrializationService.queryIndustrialization(name,startTime,endTime);
        Page<SubUnitEnIndustrialization> list = this.subUnitEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.subUnitEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }
    //添加
    @RequestMapping(value = "/addSubUnitEnIndustrialization", method = RequestMethod.POST)
    public Map<String, Object> addSubUnitEnIndustrialization(@RequestBody SubUnitEnIndustrialization subUnitEnIndustrialization)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        subUnitEnIndustrialization.setSubUnitEn((SubUnitEn)storedUser);
        subUnitEnIndustrialization.setQuarterEnd(Utils.getQuarterEnd(subUnitEnIndustrialization.getYear(),subUnitEnIndustrialization.getQuarter()));
        this.subUnitEnIndustrializationService.addSubUnitEnIndustrialization(subUnitEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subUnitEnIndustrialization", subUnitEnIndustrialization);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllSubUnitEnIndustrializations", method = RequestMethod.GET)
    public List<SubUnitEnIndustrialization> findSubUnitEnIndustrialization()throws Exception {
        List<SubUnitEnIndustrialization> subUnitEnIndustrializations = subUnitEnIndustrializationService.findAllT();
        return subUnitEnIndustrializations;
    }


    //实现分页
    @RequestMapping(value = "/displayAllSubUnitEnIndustrializations", method = RequestMethod.GET)
    public Map<String, Object> findAllSubUnitEnIndustrialization(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<SubUnitEnIndustrialization> list = this.subUnitEnIndustrializationService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.subUnitEnIndustrializationService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    @RequestMapping(value = "/getAllSubUnitEnIndustrializations", method = RequestMethod.GET)
    public Map<String, Object> getAllSubUnitEnIndustrializations(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Page<SubUnitEnIndustrialization> list = this.subUnitEnIndustrializationService.findBySubUnitEnId(storedUser.getId(),new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.subUnitEnIndustrializationService.findBySubUnitEnId(storedUser.getId()).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }


    //实现分页 审核查询
    @RequestMapping(value = "/displayAllSubUnitEnIndustrializationsBySubmit", method = RequestMethod.GET)
    public Map<String, Object> findAllSubUnitEnIndustrializationBySubmit(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<SubUnitEnIndustrialization> list = this.subUnitEnIndustrializationService.findBySubmit(true,new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.subUnitEnIndustrializationService.findBySubmit(true).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateSubUnitEnIndustrialization", method = RequestMethod.PUT)
    public Map<String, Object> updateSubUnitEnIndustrialization(@RequestBody SubUnitEnIndustrialization subUnitEnIndustrialization)throws Exception {
        this.subUnitEnIndustrializationService.updateSubUnitEnIndustrialization(subUnitEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subUnitEnIndustrialization", subUnitEnIndustrialization);
        return map;
    }

    //单个删除   完成 删
    @RequestMapping(value = "/deleteSubUnitEnIndustrialization", method = RequestMethod.DELETE)
    public void deleteSubUnitEnIndustrialization(@RequestParam("id") Long id)throws Exception {
        this.subUnitEnIndustrializationService.deleteSubUnitEnIndustrialization(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteSubUnitEnIndustrializations",method = RequestMethod.DELETE)
    public void deleteSubUnitEnIndustrializations(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.subUnitEnIndustrializationService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
