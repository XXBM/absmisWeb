package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ProjectByRealEstateEn;
import com.absmis.repository.enterprise.ProjectByRealEstateEnRepository;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service
public class ProjectByRealEstateEnService extends BasicService<ProjectByRealEstateEn, Long> {
    @Autowired
    ProjectByRealEstateEnRepository projectByRealEstateEnRepository;
    /*增加*/
    public void addProjectByRealEstateEn(ProjectByRealEstateEn projectByRealEstateEn){
        this.projectByRealEstateEnRepository.save(projectByRealEstateEn);
    }

    /*修改*/
    public void updateProjectByRealEstateEn(ProjectByRealEstateEn projectByRealEstateEn){
        this.projectByRealEstateEnRepository.saveAndFlush(projectByRealEstateEn);}

    /*findById*/
    public ProjectByRealEstateEn findById(Long id){
        return projectByRealEstateEnRepository.findOne(id);
    }

    /*删除*/
    public void deleteProjectByRealEstateEn(Long id){
        this.projectByRealEstateEnRepository.delete(id);
    }

    //分页显示
    public Page<ProjectByRealEstateEn> findAll(Pageable pageable){
        return this.projectByRealEstateEnRepository.findAll(pageable);
    }


    /**
     * 多条件查询
     */
    public Page<ProjectByRealEstateEn> findBySepc(Specification<ProjectByRealEstateEn> specification, Pageable pageable) {
        return this.projectByRealEstateEnRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<ProjectByRealEstateEn> findBySepc(Specification<ProjectByRealEstateEn> specification) {
        return this.projectByRealEstateEnRepository.findAll(specification);
    }
    public List<ProjectByRealEstateEn> findBySepc(Specification<ProjectByRealEstateEn> specification, Sort sort) {
        return this.projectByRealEstateEnRepository.findAll(specification,sort);
    }


    public Specification<ProjectByRealEstateEn> queryProjectByRealEstateEn(
            String startTime,
            String endTime){
        return new Specification<ProjectByRealEstateEn>() {
            @Override
            public Predicate toPredicate(Root<ProjectByRealEstateEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if(!"Invalid date".equals(startTime) && !"".equals(startTime) && !new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(startTime)){
                    predicate.add(cb.greaterThanOrEqualTo(root.get("startingTime").as(String.class), startTime));
                }
                if(!"Invalid date".equals(endTime) && !"".equals(endTime) && !new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(endTime)){
                    predicate.add(cb.lessThanOrEqualTo(root.get("startingTime").as(String.class), endTime));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


}