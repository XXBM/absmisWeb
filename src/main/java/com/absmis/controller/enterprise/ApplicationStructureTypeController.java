package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.ApplicationStructureType;
import com.absmis.domain.message.DataGridResult;
import com.absmis.service.enterprise.ApplicationStructureTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationStructureTypeController {
    @Autowired
    ApplicationStructureTypeService applicationStructureTypeService;
/**
 * @Author: LihuaHuang
 * @Description:
 *  1.每次翻页都修改SQL，向SQL传入相关参数去数据库实时查出该页的数据并显示。
 *  2.查出数据库某张表的全部数据，再通过在业务逻辑里面进行处理去取得某些数据并显示。
 *  其中第一种是最常用的，但有时候你必须在业务逻辑里面对数据进行处理，也就是第二种方法，
 *  在此，介绍第二种方法
 * @Date: Created in ${TIME} ${DATE}.
 * @Modified by:
 */
    //实现分页
    @RequestMapping(value = "/testPage", method = RequestMethod.GET)
    public Map<String, Object> testPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size
    ) throws Exception {
        List<ApplicationStructureType> applicationStructureTypes = this.applicationStructureTypeService.findAllT();
        Map<String, Object> map = new HashMap<String, Object>();
        //查到的总用户数
        map.put("total", applicationStructureTypes.size());

        //总页数
        int pageTimes;
        if (applicationStructureTypes.size() % size == 0) {
            pageTimes = applicationStructureTypes.size() / size;
        } else {
            pageTimes = applicationStructureTypes.size() / size + 1;
        }
        map.put("pageTimes", pageTimes);

        List<ApplicationStructureType> newApplicationStructureTypes = new ArrayList<ApplicationStructureType>();
        //每页开始的第几条记录
        if(pageTimes==page) {
            newApplicationStructureTypes.addAll(applicationStructureTypes.subList((page-1)*size,applicationStructureTypes.size()));
        }else {
            newApplicationStructureTypes.addAll(applicationStructureTypes.subList((page-1)*size,(page-1)*size+size));
        }
        map.put("applicationStructureType", newApplicationStructureTypes);
        return map;
    }

    //实现分页
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public DataGridResult findAll(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size
    ) throws Exception {
        DataGridResult countrylists = this.applicationStructureTypeService.getCountryList(page, size);
        return countrylists;
    }

    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllApplicationStructureTypes", method = RequestMethod.GET)
    public List<ApplicationStructureType> findApplicationStructureType() throws Exception {
        List<ApplicationStructureType> applicationStructureTypes = applicationStructureTypeService.findAllT();
        return applicationStructureTypes;
    }
}
