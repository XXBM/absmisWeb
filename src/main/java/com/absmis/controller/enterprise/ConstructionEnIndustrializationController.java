package com.absmis.controller.enterprise;

import com.absmis.domain.authority.User;
import com.absmis.domain.enterprise.ConstructionEn;
import com.absmis.domain.enterprise.ConstructionEnIndustrialization;
import com.absmis.domain.message.ConstructionEnIndustrializationInfo;
import com.absmis.service.authority.UserService;
import com.absmis.service.enterprise.CheckedStatusService;
import com.absmis.service.enterprise.ConstructionEnIndustrializationService;
import com.absmis.service.enterprise.ConstructionEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConstructionEnIndustrializationController {
    @Autowired
    ConstructionEnIndustrializationService constructionEnIndustrializationService;
    @Autowired
    ConstructionEnService constructionEnService;
    @Autowired
    CheckedStatusService checkedStatusService;
    @Autowired
    UserService userService;
    String username = null;
    User storedUser = null;

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryAnnual", method = RequestMethod.GET)
    public ConstructionEnIndustrializationInfo queryQuarterConstructionEnIndustrialization(
            @RequestParam(value = "enId") Long enId,
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    )throws Exception {
        ConstructionEnIndustrialization constructionEnIndustrialization =  constructionEnIndustrializationService.getByConstructionEnIdAndYearAndQuarter(enId,year,quarter);
        ConstructionEn constructionEn = constructionEnService.findOne(enId);
        Specification<ConstructionEnIndustrialization> sp = this.constructionEnIndustrializationService.queryAnnual(enId,year,quarter);
        List<ConstructionEnIndustrialization> annualList = this.constructionEnIndustrializationService.findBySepc(sp);
        double annualConcrete = 0;
        //年度装配式钢结构建筑的数量
        double annualSteel = 0;
        //年度装配式木建筑的数量
        double annualTimber = 0;
        for(int i=0;i<annualList.size();i++){
            annualConcrete += annualList.get(i).getAddNewConcrete();
            annualSteel += annualList.get(i).getAddNewSteel();
            annualTimber += annualList.get(i).getAddNewTimber();
        }
        ConstructionEnIndustrializationInfo constructionEnIndustrializationInfo = new ConstructionEnIndustrializationInfo();
        constructionEnIndustrializationInfo.setAddNewConcrete(constructionEnIndustrialization.getAddNewConcrete());
        constructionEnIndustrializationInfo.setAddNewSteel(constructionEnIndustrialization.getAddNewSteel());
        constructionEnIndustrializationInfo.setAddNewTimber(constructionEnIndustrialization.getAddNewTimber());
        double totalScale = 0;
        Specification<ConstructionEnIndustrialization> constructionEnIndustrializationSpecification = this.constructionEnIndustrializationService.queryTotalScale(enId,year,quarter);
        List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(constructionEnIndustrializationSpecification);
        for(int x=0;x<constructionEnIndustrializations.size();x++){
            totalScale += constructionEnIndustrializations.get(x).getTotalScale();
        }
        constructionEnIndustrializationInfo.setCumulant(totalScale);
        constructionEnIndustrializationInfo.setAnnualConcrete(annualConcrete);
        constructionEnIndustrializationInfo.setAnnualSteel(annualSteel);
        constructionEnIndustrializationInfo.setAnnualTimber(annualTimber);
        return constructionEnIndustrializationInfo;
    }


    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryConstructionEnInCheck", method = RequestMethod.GET)
    public Map<String, Object> queryConstructionEnInCheck(
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Pageable pageable = new PageRequest(page-1,size);
        Specification<ConstructionEnIndustrialization> specification = this.constructionEnIndustrializationService.queryEnIndustrialization(storedUser.getId(),startTime,endTime);
        Page<ConstructionEnIndustrialization> list = this.constructionEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.constructionEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/queryConstructionEnIn", method = RequestMethod.GET)
    public Map<String, Object> queryConstructionEnIn(
            @RequestParam(value = "constructionEnName") String name,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size)throws Exception {
        Pageable pageable = new PageRequest(page-1,size);
        Specification<ConstructionEnIndustrialization> specification = this.constructionEnIndustrializationService.queryIndustrialization(name,startTime,endTime);
        Page<ConstructionEnIndustrialization> list = this.constructionEnIndustrializationService.findBySepc(specification,pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.constructionEnIndustrializationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }


    //添加
    @RequestMapping(value = "/addConstructionEnIndustrialization", method = RequestMethod.POST)
    public Map<String, Object> addConstructionEnIndustrialization(@RequestBody ConstructionEnIndustrialization constructionEnIndustrialization)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        ConstructionEn constructionEn = (ConstructionEn)storedUser;
        //设置当期总量
        constructionEnIndustrialization.setTotalScale(constructionEnIndustrialization.getAddNewConcrete()+constructionEnIndustrialization.getAddNewTimber()+constructionEnIndustrialization.getAddNewSteel());
        constructionEnIndustrialization.setConstructionEn(constructionEn);
        this.constructionEnIndustrializationService.addConstructionEnIndustrialization(constructionEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("constructionEnIndustrialization", constructionEnIndustrialization);
        return map;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllConstructionEnIndustrializations", method = RequestMethod.GET)
    public List<ConstructionEnIndustrialization> findConstructionEnIndustrialization()throws Exception {
        List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findAllT();
        return constructionEnIndustrializations;
    }


    //实现分页
    @RequestMapping(value = "/displayAllConstructionEnIndustrializations", method = RequestMethod.GET)
    public Map<String, Object> findAllConstructionEnIndustrialization(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<ConstructionEnIndustrialization> list = this.constructionEnIndustrializationService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.constructionEnIndustrializationService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    @RequestMapping(value = "/getAllConstructionEnIndustrialization", method = RequestMethod.GET)
    public Map<String, Object> getAllConstructionEnIndustrialization(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        Page<ConstructionEnIndustrialization> list = this.constructionEnIndustrializationService.findByConstructionEnId(storedUser.getId(),new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.constructionEnIndustrializationService.findByConstructionEnId(storedUser.getId()).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }


    //实现分页 审核查询
    @RequestMapping(value = "/displayAllConstructionEnIndustrializationsBySubmit", method = RequestMethod.GET)
    public Map<String, Object> findAllConstructionEnIndustrializationBySubmit(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size)throws Exception {
        Page<ConstructionEnIndustrialization> list = this.constructionEnIndustrializationService.findBySubmit(true,new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.constructionEnIndustrializationService.findBySubmit(true).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //修改学院信息    完成 改
    @RequestMapping(value = "/updateConstructionEnIndustrialization", method = RequestMethod.PUT)
    public Map<String, Object> updateConstructionEnIndustrialization(@RequestBody ConstructionEnIndustrialization constructionEnIndustrialization)throws Exception {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        storedUser = userService.findByUsername(username);
        constructionEnIndustrialization.setConstructionEn((ConstructionEn)storedUser);
        this.constructionEnIndustrializationService.updateConstructionEnIndustrialization(constructionEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("constructionEnIndustrialization", constructionEnIndustrialization);
        return map;
    }

    //check
    @RequestMapping(value = "/checkConstructionEnIndustrialization", method = RequestMethod.POST)
    public Map<String, Object> checkConstructionEnIndustrialization(
            @RequestParam("id") Long id,
            @RequestParam("constructionEnId") Long constructionEnId,
            @RequestParam("checkedStatusId") Long checkedStatusId
    )throws Exception {
        ConstructionEnIndustrialization constructionEnIndustrialization = constructionEnIndustrializationService.findById(id);
        constructionEnIndustrialization.setConstructionEn(constructionEnService.findOne(constructionEnId));
        constructionEnIndustrialization.setCheckedStatus(checkedStatusService.findOne(checkedStatusId));
        this.constructionEnIndustrializationService.updateConstructionEnIndustrialization(constructionEnIndustrialization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("constructionEnIndustrialization", constructionEnIndustrialization);
        return map;
    }


    //单个删除   完成 删
    @RequestMapping(value = "/deleteConstructionEnIndustrialization", method = RequestMethod.DELETE)
    public void deleteConstructionEnIndustrialization(@RequestParam("id") Long id)throws Exception {
        this.constructionEnIndustrializationService.deleteConstructionEnIndustrialization(id);
    }

    //批量删除   完成 删
    @RequestMapping(value = "/deleteConstructionEnIndustrializations",method = RequestMethod.DELETE)
    public void deleteConstructionEnIndustrializations(@RequestParam("ids") String ids){
        String deleteIds[] = ids.split(",");
        for(int i = 0; i<deleteIds.length; i++){
            this.constructionEnIndustrializationService.deleteById(Long.parseLong(deleteIds[i]));
        }
    }
}
