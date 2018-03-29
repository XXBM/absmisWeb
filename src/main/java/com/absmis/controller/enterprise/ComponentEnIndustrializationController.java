package com.absmis.controller.enterprise;

import com.absmis.domain.authority.User;
import com.absmis.domain.enterprise.ComponentEn;
import com.absmis.domain.enterprise.ComponentEnIndustrialization;
import com.absmis.domain.message.SubUnitAndComponentEnInfo;
import com.absmis.service.authority.UserService;
import com.absmis.service.enterprise.CheckedStatusService;
import com.absmis.service.enterprise.ComponentEnIndustrializationService;
import com.absmis.service.enterprise.ComponentEnService;
import com.absmis.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ComponentEnIndustrializationController {
    @Autowired
    ComponentEnIndustrializationService componentEnIndustrializationService;
    @Autowired
    ComponentEnService componentEnService;
    @Autowired
    CheckedStatusService checkedStatusService;
    @Autowired
    UserService userService;
    String username = null;
    User storedUser = null;
    public static Calendar startingTime;


    //根据企业和申报起止时间查询
    @RequestMapping(value = "/querytjComponentEn", method = RequestMethod.GET)
    public List<SubUnitAndComponentEnInfo> queryComponentEn(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception{
        Specification<ComponentEn> prebuiltConcreteSp = this.componentEnService.queryAnnual("prebuiltConcreteNum",year,quarter);
        List<ComponentEn> prebuiltConcretes = componentEnService.findBySepc(prebuiltConcreteSp);
        double totalPrebuiltConcretesNum = 0;
        double totalPrebuiltConcretesAbility = 0;
        double totalPrebuiltConcretesScale = 0;
        for(int i=0;i<prebuiltConcretes.size();i++){
            ComponentEnIndustrialization componentEnIndustrialization = componentEnIndustrializationService.getByComponentEnIdAndYearAndQuarter(prebuiltConcretes.get(i).getId(),year,quarter);
            System.out.println(componentEnIndustrialization==null);
            if(componentEnIndustrialization!=null){
                totalPrebuiltConcretesNum += componentEnIndustrialization.getPrebuiltConcreteNum();
                totalPrebuiltConcretesAbility += componentEnIndustrialization.getPrebuiltConcreteAbility();
                Specification<ComponentEnIndustrialization> sp = this.componentEnIndustrializationService.queryAnnual(prebuiltConcretes.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> list = componentEnIndustrializationService.findBySepc(sp);
                for(int x=0;x<list.size();x++){
                    totalPrebuiltConcretesScale += list.get(x).getPrebuiltConcreteScale();
                }
            }
        }
        Specification<ComponentEn> prebuiltSteelSp = this.componentEnService.queryAnnual("prebuiltSteelNum",year,quarter);
        List<ComponentEn> prebuiltSteels = componentEnService.findBySepc(prebuiltSteelSp);
        double totalPrebuiltSteelNum = 0;
        double totalPrebuiltSteelAbility = 0;
        double totalPrebuiltSteelScale = 0;
        for(int i=0;i<prebuiltSteels.size();i++){
            ComponentEnIndustrialization componentEnIndustrialization = componentEnIndustrializationService.getByComponentEnIdAndYearAndQuarter(prebuiltSteels.get(i).getId(),year,quarter);
            if(componentEnIndustrialization!=null){
                totalPrebuiltSteelNum += componentEnIndustrialization.getPrebuiltSteelNum();
                totalPrebuiltSteelAbility += componentEnIndustrialization.getPrebuiltSteelAbility();
                Specification<ComponentEnIndustrialization> sp = this.componentEnIndustrializationService.queryAnnual(prebuiltSteels.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> list = componentEnIndustrializationService.findBySepc(sp);
                for(int x=0;x<list.size();x++){
                    totalPrebuiltSteelScale += list.get(x).getPrebuiltSteelScale();
                }
            }
        }
        Specification<ComponentEn> prebuiltTimberSp = this.componentEnService.queryAnnual("prebuiltTimberNum",year,quarter);
        List<ComponentEn> prebuiltTimbers = componentEnService.findBySepc(prebuiltTimberSp);
        double totalPrebuiltTimberNum = 0;
        double totalPrebuiltTimberAbility = 0;
        double totalPrebuiltTimberScale = 0;
        for(int i=0;i<prebuiltTimbers.size();i++){
            ComponentEnIndustrialization componentEnIndustrialization = componentEnIndustrializationService.getByComponentEnIdAndYearAndQuarter(prebuiltTimbers.get(i).getId(),year,quarter);
            if(componentEnIndustrialization!=null){
                totalPrebuiltTimberNum += componentEnIndustrialization.getPrebuiltTimberNum();
                totalPrebuiltTimberAbility += componentEnIndustrialization.getPrebuiltTimberAbility();
                Specification<ComponentEnIndustrialization> sp = this.componentEnIndustrializationService.queryAnnual(prebuiltTimbers.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> list = componentEnIndustrializationService.findBySepc(sp);
                for(int x=0;x<list.size();x++){
                    totalPrebuiltTimberScale += list.get(x).getPrebuiltTimberScale();
                }
            }
        }
        Specification<ComponentEn> prebuiltOtherSp = this.componentEnService.queryAnnual("prebuiltOtherNum",year,quarter);
        List<ComponentEn> prebuiltOthers = componentEnService.findBySepc(prebuiltOtherSp);
        double totalPrebuiltOtherNum = 0;
        double totalPrebuiltOtherAbility = 0;
        double totalPrebuiltOtherScale = 0;
        for(int i=0;i<prebuiltOthers.size();i++){
            ComponentEnIndustrialization componentEnIndustrialization = componentEnIndustrializationService.getByComponentEnIdAndYearAndQuarter(prebuiltOthers.get(i).getId(),year,quarter);
            if(componentEnIndustrialization!=null){
                totalPrebuiltOtherNum += componentEnIndustrialization.getPrebuiltOtherNum();
                totalPrebuiltOtherAbility += componentEnIndustrialization.getPrebuiltOtherAbility();
                Specification<ComponentEnIndustrialization> sp = this.componentEnIndustrializationService.queryAnnual(prebuiltOthers.get(i).getId(),year,quarter);
                List<ComponentEnIndustrialization> list = componentEnIndustrializationService.findBySepc(sp);
                for(int x=0;x<list.size();x++){
                    totalPrebuiltOtherScale += list.get(x).getPrebuiltOtherScale();
                }
            }
        }
        List<SubUnitAndComponentEnInfo> subUnitAndComponentEnInfos = new ArrayList<>();
        SubUnitAndComponentEnInfo integralWallEnInfo = new SubUnitAndComponentEnInfo("预制装配混凝土结构生产情况",(double)prebuiltConcretes.size(),totalPrebuiltConcretesNum,totalPrebuiltConcretesAbility,totalPrebuiltConcretesScale);
        subUnitAndComponentEnInfos.add(integralWallEnInfo);
        SubUnitAndComponentEnInfo integrativeExternalWallEnInfo = new SubUnitAndComponentEnInfo("钢结构生产情况",(double)prebuiltSteels.size(),totalPrebuiltSteelNum,totalPrebuiltSteelAbility,totalPrebuiltSteelScale);
        subUnitAndComponentEnInfos.add(integrativeExternalWallEnInfo);
        SubUnitAndComponentEnInfo prebuiltStairsEnInfo = new SubUnitAndComponentEnInfo("木结构生产情况",(double)prebuiltTimbers.size(),totalPrebuiltTimberNum,totalPrebuiltTimberAbility,totalPrebuiltTimberScale);
        subUnitAndComponentEnInfos.add(prebuiltStairsEnInfo);
        SubUnitAndComponentEnInfo integralKitchenEnInfo = new SubUnitAndComponentEnInfo("其他结构的构件生产情况",(double)prebuiltOthers.size(),totalPrebuiltOtherNum,totalPrebuiltOtherAbility,totalPrebuiltOtherScale);
        subUnitAndComponentEnInfos.add(integralKitchenEnInfo);
        return subUnitAndComponentEnInfos;
    }



    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryQuarterComponentEnIn", method = RequestMethod.GET)
    public ComponentEnIndustrialization queryQuarter(
            @RequestParam(value = "enId") Long enId,
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception {
        return componentEnIndustrializationService.getByComponentEnIdAndYearAndQuarter(enId,year,quarter);
    }

    //check根据企业和申报起止时间查询
    @RequestMapping(value = "/queryComponentEnInCheck", method = RequestMethod.GET)
    public Map<String, Object> queryComponentEnInCheck(
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Pageable pageable = new PageRequest(page-1,size);
        Specification<ComponentEnIndustrialization> specification = this.componentEnIndustrializationService.queryEnIndustrialization(storedUser.getId(),startTime,endTime);
        Page<ComponentEnIndustrialization> list = this.componentEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.componentEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryComponentEnIn", method = RequestMethod.GET)
    public Map<String, Object> queryComponentEnIn(
            @RequestParam(value = "componentEnName") String name,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<ComponentEnIndustrialization> specification = this.componentEnIndustrializationService.queryIndustrialization(name,startTime,endTime);
        Page<ComponentEnIndustrialization> list = this.componentEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.componentEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //添加
    @RequestMapping(value = "/addComponentEnIndustrialization", method = RequestMethod.POST)
    public Map<String, Object> addComponentEnIndustrialization(@RequestBody ComponentEnIndustrialization componentEnIndustrialization)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        componentEnIndustrialization.setComponentEn((ComponentEn)storedUser);
        componentEnIndustrialization.setQuarterEnd(Utils.getQuarterEnd(componentEnIndustrialization.getYear(),componentEnIndustrialization.getQuarter()));
        this.componentEnIndustrializationService.addComponentEnIndustrialization(componentEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("componentEnIndustrialization", componentEnIndustrialization);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllComponentEnIndustrializations", method = RequestMethod.GET)
    public List<ComponentEnIndustrialization> findComponentEnIndustrialization()throws Exception {
        List<ComponentEnIndustrialization> componentEnIndustrializations = componentEnIndustrializationService.findAllT();
        return componentEnIndustrializations;
    }


    //实现分页
    @RequestMapping(value = "/displayAllComponentEnIndustrializations", method = RequestMethod.GET)
    public Map<String, Object> findAllComponentEnIndustrialization(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<ComponentEnIndustrialization> list = this.componentEnIndustrializationService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.componentEnIndustrializationService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    @RequestMapping(value = "/getAllComponentEnIndustrializations", method = RequestMethod.GET)
    public Map<String, Object> getAllComponentEnIndustrialization(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Page<ComponentEnIndustrialization> list = this.componentEnIndustrializationService.findByComponentEnId(storedUser.getId(),new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.componentEnIndustrializationService.findByComponentEnId(storedUser.getId()).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //实现分页 审核查询
    @RequestMapping(value = "/displayAllComponentEnIndustrializationsBySubmit", method = RequestMethod.GET)
    public Map<String, Object> findAllComponentEnIndustrializationBySubmit(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<ComponentEnIndustrialization> list = this.componentEnIndustrializationService.findBySubmit(true,new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.componentEnIndustrializationService.findBySubmit(true).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateComponentEnIndustrialization", method = RequestMethod.PUT)
    public Map<String, Object> updateComponentEnIndustrialization(@RequestBody ComponentEnIndustrialization componentEnIndustrialization)throws Exception {
        this.componentEnIndustrializationService.updateComponentEnIndustrialization(componentEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("componentEnIndustrialization", componentEnIndustrialization);
        return map;
    }

    //check
    @RequestMapping(value = "/checkComponentEnIndustrialization", method = RequestMethod.POST)
    public Map<String, Object> checkComponentEnIndustrialization(
            @RequestParam("id") Long id,
            @RequestParam("constructionEnId") Long constructionEnId,
            @RequestParam("checkedStatusId") Long checkedStatusId
    )throws Exception {
        ComponentEnIndustrialization componentEnIndustrialization = componentEnIndustrializationService.findById(id);
        componentEnIndustrialization.setComponentEn(componentEnService.findOne(constructionEnId));
        componentEnIndustrialization.setCheckedStatus(checkedStatusService.findOne(checkedStatusId));
        this.componentEnIndustrializationService.updateComponentEnIndustrialization(componentEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("componentEnIndustrialization", componentEnIndustrialization);
        return map;
    }

    //单个删除   完成 删
    @RequestMapping(value = "/deleteComponentEnIndustrialization", method = RequestMethod.DELETE)
    public void deleteComponentEnIndustrialization(@RequestParam("id") Long id)throws Exception {
        this.componentEnIndustrializationService.deleteComponentEnIndustrialization(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteComponentEnIndustrializations",method = RequestMethod.DELETE)
    public void deleteComponentEnIndustrializations(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.componentEnIndustrializationService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
