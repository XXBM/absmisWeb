package com.absmis.service;

import com.absmis.JpaRepository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;


/**
 * Created by dell on 2016/11/12.
 */
public abstract class BasicService<T extends Serializable,ID extends Serializable>{
    @Autowired
    MyRepository<T,ID> basicDao;

    /**按照id获取实体*/
    public T findOne(ID id){
        return basicDao.findOne(id);
    }
    /**获取所有的该实体类所有的属性*/
    public List<T> findAllT(){
        return basicDao.findAll();
    }
    /**获取所有的该实体类所有的属性  +  分页显示*/
    public Page<T> findAllT(Pageable pageable){
        return this.basicDao.findAll(pageable);
    }
    /**添加*/
    public void add(T t){
        this.basicDao.save(t);
    }
    /**更新*/
    public void update(T t){
        this.basicDao.saveAndFlush(t);
    }
    /**删除*/
    public void deleteById(ID id){
        this.basicDao.delete(id);
    }
}
