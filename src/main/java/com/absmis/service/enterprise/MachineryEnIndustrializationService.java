package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.MachineryEnIndustrialization;
import com.absmis.repository.enterprise.MachineryEnIndustrializationRepository;
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
public class MachineryEnIndustrializationService extends BasicService<MachineryEnIndustrialization, Long> {
    @Autowired
    MachineryEnIndustrializationRepository machineryEnIndustrializationRepository;
    /*增加*/
    public void addMachineryEnIndustrialization(MachineryEnIndustrialization machineryEnIndustrialization){
        this.machineryEnIndustrializationRepository.save(machineryEnIndustrialization);
    }
    public MachineryEnIndustrialization getByYearAndQuarter(Integer year,Integer quarter){
        return machineryEnIndustrializationRepository.getByYearAndQuarter(year,quarter);
    };
    public MachineryEnIndustrialization getByMachineryEnIdAndYearAndQuarter(Long id,Integer year,Integer quarter){
        return machineryEnIndustrializationRepository.getByMachineryEnIdAndYearAndQuarter(id,year,quarter);
    };
    /*修改*/
    public void updateMachineryEnIndustrialization(MachineryEnIndustrialization machineryEnIndustrialization){
        this.machineryEnIndustrializationRepository.saveAndFlush(machineryEnIndustrialization);}


    public Page<MachineryEnIndustrialization> findBySubmit(Boolean submit, Pageable pageable) {
        return this.machineryEnIndustrializationRepository.findBySubmit(submit, pageable);
    }
    public List<MachineryEnIndustrialization> findByMachineryEnId(Long id){
        return machineryEnIndustrializationRepository.findByMachineryEnId(id);
    }
    public Page<MachineryEnIndustrialization> findByMachineryEnId(Long machineryEnId, Pageable pageable) {
        return this.machineryEnIndustrializationRepository.findByMachineryEnId(machineryEnId, pageable);
    }
    public List<MachineryEnIndustrialization> findBySubmit(Boolean submit) {
        return this.machineryEnIndustrializationRepository.findBySubmit(submit);
    }

    /*findById*/
    public MachineryEnIndustrialization findById(Long id){
        return machineryEnIndustrializationRepository.findOne(id);
    }

    /*删除*/
    public void deleteMachineryEnIndustrialization(Long id){
        this.machineryEnIndustrializationRepository.delete(id);
    }

    //分页显示
    public Page<MachineryEnIndustrialization> findAll(Pageable pageable){
        return this.machineryEnIndustrializationRepository.findAll(pageable);
    }

    /**
     * 多条件查询
     */
    public Page<MachineryEnIndustrialization> findBySepc(Specification<MachineryEnIndustrialization> specification, Pageable pageable) {
        return this.machineryEnIndustrializationRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<MachineryEnIndustrialization> findBySepc(Specification<MachineryEnIndustrialization> specification) {
        return this.machineryEnIndustrializationRepository.findAll(specification);
    }
    public List<MachineryEnIndustrialization> findBySepc(Specification<MachineryEnIndustrialization> specification, Sort sort) {
        return this.machineryEnIndustrializationRepository.findAll(specification,sort);
    }


    public Specification<MachineryEnIndustrialization> queryEnIndustrialization(
            Long id,
            String startTime,
            String endTime){
        return new Specification<MachineryEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<MachineryEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("machineryEn"), id));
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


    public Specification<MachineryEnIndustrialization> queryIndustrialization(
            String name,
            String startTime,
            String endTime){
        return new Specification<MachineryEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<MachineryEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                if (name!=""){
                    predicate.add(cb.like(root.get("machineryEn").get("name"),"%"+name+"%"));
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

    public Specification<MachineryEnIndustrialization> queryQuarter(
            Long enId,
            Integer year,
            Integer quarter){
        return new Specification<MachineryEnIndustrialization>() {
            @Override
            public Predicate toPredicate(Root<MachineryEnIndustrialization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("machineryEn"), enId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.equal(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


}