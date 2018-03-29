package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.SubUnitEnIndustrialization;
import com.absmis.repository.enterprise.SubUnitEnIndustrializationRepository;
import com.absmis.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubUnitEnIndustrializationService extends BasicService<SubUnitEnIndustrialization, Long> {
    @Autowired
    SubUnitEnIndustrializationRepository subUnitEnIndustrializationRepository;
    /*增加*/
    public void addSubUnitEnIndustrialization(SubUnitEnIndustrialization subUnitEnIndustrialization){
        this.subUnitEnIndustrializationRepository.save(subUnitEnIndustrialization);
    }

    public SubUnitEnIndustrialization getBySubUnitEnIdAndYearAndQuarter(Long id, Integer year, Integer quarter){
        return subUnitEnIndustrializationRepository.getBySubUnitEnIdAndYearAndQuarter(id,year,quarter);
    };

    public Page<SubUnitEnIndustrialization> findBySubmit(Boolean submit, Pageable pageable) {
        return this.subUnitEnIndustrializationRepository.findBySubmit(submit, pageable);
    }
    public List<SubUnitEnIndustrialization> findBySubUnitEnId(Long id){
        return subUnitEnIndustrializationRepository.findBySubUnitEnId(id);
    }
    public Page<SubUnitEnIndustrialization> findBySubUnitEnId(Long subUnitEnId, Pageable pageable) {
        return this.subUnitEnIndustrializationRepository.findBySubUnitEnId(subUnitEnId, pageable);
    }
    public List<SubUnitEnIndustrialization> findBySubmit(Boolean submit) {
        return this.subUnitEnIndustrializationRepository.findBySubmit(submit);
    }

    /*修改*/
    public void updateSubUnitEnIndustrialization(SubUnitEnIndustrialization subUnitEnIndustrialization){
        this.subUnitEnIndustrializationRepository.saveAndFlush(subUnitEnIndustrialization);}

    /*findById*/
    public SubUnitEnIndustrialization findById(Long id){
        return subUnitEnIndustrializationRepository.findOne(id);
    }

    /*删除*/
    public void deleteSubUnitEnIndustrialization(Long id){
        this.subUnitEnIndustrializationRepository.delete(id);
    }

    //分页显示
    public Page<SubUnitEnIndustrialization> findAll(Pageable pageable){
        return this.subUnitEnIndustrializationRepository.findAll(pageable);
    }

    /**
     * 多条件查询
     */
    public Page<SubUnitEnIndustrialization> findBySepc(Specification<SubUnitEnIndustrialization> specification, Pageable pageable) {
        return this.subUnitEnIndustrializationRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<SubUnitEnIndustrialization> findBySepc(Specification<SubUnitEnIndustrialization> specification) {
        return this.subUnitEnIndustrializationRepository.findAll(specification);
    }
    public List<SubUnitEnIndustrialization> findBySepc(Specification<SubUnitEnIndustrialization> specification, Sort sort) {
        return this.subUnitEnIndustrializationRepository.findAll(specification,sort);
    }


    public Specification<SubUnitEnIndustrialization> queryEnIndustrialization(
            Long id,
            String startTime,
            String endTime){
        return new Specification<SubUnitEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<SubUnitEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("subUnitEn"), id));
                if (startTime!=""){
                    predicate.add(cb.greaterThanOrEqualTo(root.get("declareTime").as(String.class), startTime));
                }
                if(endTime!=""){
                    predicate.add(cb.lessThanOrEqualTo(root.get("declareTime").as(String.class), endTime));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<SubUnitEnIndustrialization> queryIndustrialization(
            String name,
            String startTime,
            String endTime){
        return new Specification<SubUnitEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<SubUnitEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                if (name!=""){
                    predicate.add(cb.like(root.get("subUnitEn").get("name"),"%"+name+"%"));
                }
                if (startTime!=""){
                    predicate.add(cb.greaterThanOrEqualTo(root.get("declareTime").as(String.class), startTime));
                }
                if(endTime!=""){
                    predicate.add(cb.lessThanOrEqualTo(root.get("declareTime").as(String.class), endTime));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    public Specification<SubUnitEnIndustrialization> queryAnnual(
            Long enId,
            Integer year,
            Integer quarter){
        return new Specification<SubUnitEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<SubUnitEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("subUnitEn"), enId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.lessThanOrEqualTo(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }



}