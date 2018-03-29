package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ApplicationStructureType;
import com.absmis.domain.message.DataGridResult;
import com.absmis.repository.enterprise.ApplicationStructureTypeRepository;
import com.absmis.service.BasicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ApplicationStructureTypeService extends BasicService<ApplicationStructureType, Long>{
    @Autowired
    ApplicationStructureTypeRepository applicationStructureTypeRepository;


    //分页显示
    public Page<ApplicationStructureType> findAll(Pageable pageable){
        return this.applicationStructureTypeRepository.findAll(pageable);
    }

    public DataGridResult getCountryList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);

        //查询结果
        Page<ApplicationStructureType> list = applicationStructureTypeRepository.findAll(new PageRequest(page - 1, rows));
        List<ApplicationStructureType> list2 = applicationStructureTypeRepository.findAll();
        //获取分页信息
        PageInfo<ApplicationStructureType> info = new PageInfo<>(list.getContent());
        DataGridResult result = new DataGridResult();
        long total = info.getTotal();

        //封装分页信息
        List<ApplicationStructureType> row = info.getList();
        result.setRows(row);
        result.setTotal((long)list2.size());
        return result;
        //分页处理
//        PageHelper.startPage(page, rows);
//
//        //查询结果
//        List<ApplicationStructureType> list = applicationStructureTypeRepository.findAll();
//
//        //获取分页信息
//        PageInfo<ApplicationStructureType> info = new PageInfo<ApplicationStructureType>(list);
//        DataGridResult result = new DataGridResult();
//        long total = info.getTotal();
//
//        //封装分页信息
//        List<ApplicationStructureType> row = info.getList();
//        result.setRows(row);
//        result.setTotal(total);
//        return result;
    }
}