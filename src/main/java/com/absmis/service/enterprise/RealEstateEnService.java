package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ConstructionEnIndustrialization;
import com.absmis.domain.enterprise.RealEstateEn;
import com.absmis.repository.enterprise.RealEstateEnRepository;
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
public class RealEstateEnService extends BasicService<RealEstateEn, Long> {
    @Autowired
    RealEstateEnRepository realEstateEnRepository;
    /*增加*/
    public void addRealEstateEn(RealEstateEn realEstateEn){
        this.realEstateEnRepository.save(realEstateEn);
    }

    /*修改*/
    public void updateRealEstateEn(RealEstateEn realEstateEn){
        this.realEstateEnRepository.saveAndFlush(realEstateEn);}

    /*findById*/
    public RealEstateEn findById(Long id){
        return realEstateEnRepository.findOne(id);
    }

    /*删除*/
    public void deleteRealEstateEn(Long id){
        this.realEstateEnRepository.delete(id);
    }

    //分页显示
    public Page<RealEstateEn> findAll(Pageable pageable){
        return this.realEstateEnRepository.findAll(pageable);
    }

    /**
     * 多条件查询
     */
    public Page<RealEstateEn> findBySepc(Specification<RealEstateEn> specification, Pageable pageable) {
        return this.realEstateEnRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<RealEstateEn> findBySepc(Specification<RealEstateEn> specification) {
        return this.realEstateEnRepository.findAll(specification);
    }
    public List<RealEstateEn> findBySepc(Specification<RealEstateEn> specification, Sort sort) {
        return this.realEstateEnRepository.findAll(specification,sort);
    }

    public Specification<RealEstateEn> queryName(String property){
        return new Specification<RealEstateEn>() {
            @Override
            public Predicate toPredicate(Root<RealEstateEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.like(root.get("name"),"%"+property+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<RealEstateEn> queryByCumulant(){
        return new Specification<RealEstateEn>() {
            @Override
            public Predicate toPredicate(Root<RealEstateEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.greaterThan(root.get("cumulant"),0));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<RealEstateEn> queryByAnnualCumulant(
            Integer year,
            Integer quarter
    ){
        return new Specification<RealEstateEn>(){
            @Override
            public Predicate toPredicate(Root<RealEstateEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("cumulant"),0));
                Join<RealEstateEn, ConstructionEnIndustrialization> realEstateEnAssIndustrializationJoins = root.join("constructionEnIndustrializations");
                predicate.add(cb.greaterThan(realEstateEnAssIndustrializationJoins.get("totalScale"),0));
                predicate.add(cb.lessThanOrEqualTo(realEstateEnAssIndustrializationJoins.get("quarterEnd").as(String.class), Utils.getQuarterEndTime(year,quarter)));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}