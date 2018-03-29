package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ProjectByEstateOwner;
import com.absmis.repository.enterprise.ProjectByEstateOwnerRepository;
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
public class ProjectByEstateOwnerService extends BasicService<ProjectByEstateOwner, Long> {
    @Autowired
    ProjectByEstateOwnerRepository projectByEstateOwnerRepository;
    /*增加*/
    public void addProjectByEstateOwner(ProjectByEstateOwner projectByEstateOwner){
        this.projectByEstateOwnerRepository.save(projectByEstateOwner);
    }

    /*修改*/
    public void updateProjectByEstateOwner(ProjectByEstateOwner projectByEstateOwner){
        this.projectByEstateOwnerRepository.saveAndFlush(projectByEstateOwner);}

    /*findById*/
    public ProjectByEstateOwner findById(Long id){
        return projectByEstateOwnerRepository.findOne(id);
    }

    /*删除*/
    public void deleteProjectByEstateOwner(Long id){
        this.projectByEstateOwnerRepository.delete(id);
    }

    //分页显示
    public Page<ProjectByEstateOwner> findAll(Pageable pageable){
        return this.projectByEstateOwnerRepository.findAll(pageable);
    }

    /**
     * 多条件查询
     */
    public Page<ProjectByEstateOwner> findBySepc(Specification<ProjectByEstateOwner> specification, Pageable pageable) {
        return this.projectByEstateOwnerRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<ProjectByEstateOwner> findBySepc(Specification<ProjectByEstateOwner> specification) {
        return this.projectByEstateOwnerRepository.findAll(specification);
    }
    public List<ProjectByEstateOwner> findBySepc(Specification<ProjectByEstateOwner> specification, Sort sort) {
        return this.projectByEstateOwnerRepository.findAll(specification,sort);
    }


    public Specification<ProjectByEstateOwner> queryProjectByEstateOwner(
            String startTime,
            String endTime){
        return new Specification<ProjectByEstateOwner>() {
            @Override
            public Predicate toPredicate(Root<ProjectByEstateOwner> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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