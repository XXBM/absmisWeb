package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ComponentEn;
import com.absmis.domain.enterprise.ComponentEnIndustrialization;
import com.absmis.repository.enterprise.ComponentEnRepository;
import com.absmis.service.BasicService;
import com.absmis.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;



@Service
public class ComponentEnService extends BasicService<ComponentEn, Long> {
    @Autowired
    ComponentEnRepository componentEnRepository;
    /*增加*/
    public void addComponentEn(ComponentEn componentEn){
        this.componentEnRepository.save(componentEn);
    }

    /*修改*/
    public void updateComponentEn(ComponentEn componentEn){
        this.componentEnRepository.saveAndFlush(componentEn);}

    /*findById*/
    public ComponentEn findById(Long id){
        return componentEnRepository.findOne(id);
    }

    /*删除*/
    public void deleteComponentEn(Long id){
        this.componentEnRepository.delete(id);
    }

    //分页显示
    public Page<ComponentEn> findAll(Pageable pageable){
        return this.componentEnRepository.findAll(pageable);
    }
    /**
     * 多条件查询
     */
    public Page<ComponentEn> findBySepc(Specification<ComponentEn> specification, Pageable pageable) {
        return this.componentEnRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<ComponentEn> findBySepc(Specification<ComponentEn> specification) {
        return this.componentEnRepository.findAll(specification);
    }
    public List<ComponentEn> findBySepc(Specification<ComponentEn> specification, Sort sort) {
        return this.componentEnRepository.findAll(specification,sort);
    }

    public Specification<ComponentEn> queryName(String property){
        return new Specification<ComponentEn>() {
            @Override
            public Predicate toPredicate(Root<ComponentEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.like(root.get("name"),"%"+property+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    public Specification<ComponentEn> queryAnnual(
            String property,
            Integer year,
            Integer quarter
    ){
        return new Specification<ComponentEn>() {
            @Override
            public Predicate toPredicate(Root<ComponentEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                Join<ComponentEn, ComponentEnIndustrialization> componentEnAssIndustrializationJoins = root.join("componentEnIndustrializations");
                predicate.add(cb.greaterThan(componentEnAssIndustrializationJoins.get(property),0));
                predicate.add(cb.lessThanOrEqualTo(componentEnAssIndustrializationJoins.get("quarterEnd").as(String.class), Utils.getQuarterEndTime(year,quarter)));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}