package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.SubUnitEn;
import com.absmis.domain.enterprise.SubUnitEnIndustrialization;
import com.absmis.repository.enterprise.SubUnitEnRepository;
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
public class SubUnitEnService extends BasicService<SubUnitEn, Long> {
    @Autowired
    SubUnitEnRepository subUnitEnRepository;
    /*增加*/
    public void addSubUnitEn(SubUnitEn subUnitEn){
        this.subUnitEnRepository.save(subUnitEn);
    }

    /*修改*/
    public void updateSubUnitEn(SubUnitEn subUnitEn){
        this.subUnitEnRepository.saveAndFlush(subUnitEn);}

    /*findById*/
    public SubUnitEn findById(Long id){
        return subUnitEnRepository.findOne(id);
    }

    /*删除*/
    public void deleteSubUnitEn(Long id){
        this.subUnitEnRepository.delete(id);
    }

    //分页显示
    public Page<SubUnitEn> findAll(Pageable pageable){
        return this.subUnitEnRepository.findAll(pageable);
    }

    /**
     * 多条件查询
     */
    public Page<SubUnitEn> findBySepc(Specification<SubUnitEn> specification, Pageable pageable) {
        return this.subUnitEnRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<SubUnitEn> findBySepc(Specification<SubUnitEn> specification) {
        return this.subUnitEnRepository.findAll(specification);
    }
    public List<SubUnitEn> findBySepc(Specification<SubUnitEn> specification, Sort sort) {
        return this.subUnitEnRepository.findAll(specification,sort);
    }

    public Specification<SubUnitEn> queryName(String property){
        return new Specification<SubUnitEn>() {
            @Override
            public Predicate toPredicate(Root<SubUnitEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.like(root.get("name"),"%"+property+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<SubUnitEn> queryAnnual(
            String property,
            Integer year,
            Integer quarter
    ){
        return new Specification<SubUnitEn>() {
            @Override
            public Predicate toPredicate(Root<SubUnitEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                Join<SubUnitEn, SubUnitEnIndustrialization> subUnitEnAssIndustrializationJoins = root.join("subUnitEnIndustrializations");
                predicate.add(cb.greaterThan(subUnitEnAssIndustrializationJoins.get(property),0));
                predicate.add(cb.lessThanOrEqualTo(subUnitEnAssIndustrializationJoins.get("quarterEnd").as(String.class), Utils.getQuarterEndTime(year,quarter)));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}