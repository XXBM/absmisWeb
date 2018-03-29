package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ComponentEnIndustrialization;
import com.absmis.repository.enterprise.ComponentEnIndustrializationRepository;
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
public class ComponentEnIndustrializationService extends BasicService<ComponentEnIndustrialization, Long> {
    @Autowired
    ComponentEnIndustrializationRepository componentEnIndustrializationRepository;
    /*增加*/
    public void addComponentEnIndustrialization(ComponentEnIndustrialization componentEnIndustrialization){
        this.componentEnIndustrializationRepository.save(componentEnIndustrialization);
    }

    public ComponentEnIndustrialization getByComponentEnIdAndYearAndQuarter(Long id, Integer year, Integer quarter){
        return componentEnIndustrializationRepository.getByComponentEnIdAndYearAndQuarter(id,year,quarter);
    };

    public Page<ComponentEnIndustrialization> findBySubmit(Boolean submit, Pageable pageable) {
        return this.componentEnIndustrializationRepository.findBySubmit(submit, pageable);
    }

    public Page<ComponentEnIndustrialization> findByComponentEnId(Long componentEnId, Pageable pageable) {
        return this.componentEnIndustrializationRepository.findByComponentEnId(componentEnId, pageable);
    }
    public List<ComponentEnIndustrialization> findBySubmit(Boolean submit) {
        return this.componentEnIndustrializationRepository.findBySubmit(submit);
    }


    /*修改*/
    public void updateComponentEnIndustrialization(ComponentEnIndustrialization componentEnIndustrialization){
        this.componentEnIndustrializationRepository.saveAndFlush(componentEnIndustrialization);}

    /*findById*/
    public List<ComponentEnIndustrialization> findByComponentEnId(Long id){
        return componentEnIndustrializationRepository.findByComponentEnId(id);
    }

    public ComponentEnIndustrialization findById(Long id){
        return componentEnIndustrializationRepository.findOne(id);
    }

    /*删除*/
    public void deleteComponentEnIndustrialization(Long id){
        this.componentEnIndustrializationRepository.delete(id);
    }

    //分页显示
    public Page<ComponentEnIndustrialization> findAll(Pageable pageable){
        return this.componentEnIndustrializationRepository.findAll(pageable);
    }

    /**
     * 多条件查询
     */
    public Page<ComponentEnIndustrialization> findBySepc(Specification<ComponentEnIndustrialization> specification, Pageable pageable) {
        return this.componentEnIndustrializationRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<ComponentEnIndustrialization> findBySepc(Specification<ComponentEnIndustrialization> specification) {
        return this.componentEnIndustrializationRepository.findAll(specification);
    }
    public List<ComponentEnIndustrialization> findBySepc(Specification<ComponentEnIndustrialization> specification, Sort sort) {
        return this.componentEnIndustrializationRepository.findAll(specification,sort);
    }


    public Specification<ComponentEnIndustrialization> queryEnIndustrialization(
            Long id,
            String startTime,
            String endTime){
        return new Specification<ComponentEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<ComponentEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("componentEn"), id));
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

    public Specification<ComponentEnIndustrialization> queryIndustrialization(
            String name,
            String startTime,
            String endTime){
        return new Specification<ComponentEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<ComponentEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                if (name!=""){
                    predicate.add(cb.like(root.get("componentEn").get("name"),"%"+name+"%"));
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


    public Specification<ComponentEnIndustrialization> queryQuarter(
            Long enId,
            Integer year,
            Integer quarter){
        return new Specification<ComponentEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<ComponentEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("componentEn"), enId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.equal(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    public Specification<ComponentEnIndustrialization> queryAnnual(
            Long enId,
            Integer year,
            Integer quarter){
        return new Specification<ComponentEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<ComponentEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("componentEn"), enId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.lessThanOrEqualTo(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}