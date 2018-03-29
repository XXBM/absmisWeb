package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.Project;
import com.absmis.domain.enterprise.UnitEngineering;
import com.absmis.repository.enterprise.ProjectRepository;
import com.absmis.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service
public class ProjectService extends BasicService<Project, Long> {
    @Autowired
    ProjectRepository projectRepository;
    /*增加*/
    public void addProject(Project project){
        this.projectRepository.save(project);
    }

    /*修改*/
    public void updateProject(Project project){
        this.projectRepository.saveAndFlush(project);}

    /*findById*/
    public Project findById(Long id){
        return projectRepository.findOne(id);
    }

    /*删除*/
    public void deleteProject(Long id){
        this.projectRepository.delete(id);
    }

    //分页显示
    public Page<Project> findAll(Pageable pageable){
        return this.projectRepository.findAll(pageable);
    }


    /**
     * 多条件查询
     */
    public Page<Project> findBySepc(Specification<Project> specification, Pageable pageable) {
        return this.projectRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<Project> findBySepc(Specification<Project> specification) {
        return this.projectRepository.findAll(specification);
    }
    public List<Project> findBySepc(Specification<Project> specification, Sort sort) {
        return this.projectRepository.findAll(specification,sort);
    }


    public Specification<Project> queryProject(
            String startTime,
            String endTime){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if(!"Invalid date".equals(startTime) && !"".equals(startTime) && !new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(startTime)){
                    System.out.println("start"+startTime);
                    predicate.add(cb.greaterThanOrEqualTo(root.get("startingTime").as(String.class), startTime));
                }
                if(!"Invalid date".equals(endTime) && !"".equals(endTime) && !new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(endTime)){
                    System.out.println("end"+endTime);
                    predicate.add(cb.lessThanOrEqualTo(root.get("startingTime").as(String.class), endTime));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }



    public Specification<Project> queryQuarter(
            String endTime){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.lessThanOrEqualTo(root.get("startingTime").as(String.class), endTime));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Project> queryQuarter(
            Integer year,
            Integer quarter){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.lessThanOrEqualTo(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Project> queryQuarterByCategory(
            Long categoryId,
            Integer year,
            Integer quarter){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("projectCategory"), categoryId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.lessThanOrEqualTo(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Project> queryQuarterByCategory(
            Long categoryId,
            String endTime){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("projectCategory"), categoryId));
                predicate.add(cb.lessThanOrEqualTo(root.get("startingTime").as(String.class), endTime));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Project> queryQuarterByCategoryYear(
            Long categoryId,
            Integer year){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("projectCategory"), categoryId));
                predicate.add(cb.lessThan(root.get("year"), year));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Project> queryQuarterByForm(
            Long formId,
            Integer year,
            Integer quarter){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                Join<Project, UnitEngineering> projectAssUnitEngineeringJoins = root.join("unitEngineerings");;
                predicate.add(cb.equal(projectAssUnitEngineeringJoins.get("structureForm").get("id"),formId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.lessThanOrEqualTo(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Project> queryQuarterByFormYear(
            Long formId,
            Integer year){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                Join<Project, UnitEngineering> projectAssUnitEngineeringJoins = root.join("unitEngineerings");;
                predicate.add(cb.equal(projectAssUnitEngineeringJoins.get("structureForm").get("id"),formId));
                predicate.add(cb.lessThan(root.get("year"), year));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Project> queryQuarterByForm(
            Long formId,
            String endTime){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                Join<Project, UnitEngineering> projectAssUnitEngineeringJoins = root.join("unitEngineerings");;
                predicate.add(cb.equal(projectAssUnitEngineeringJoins.get("structureForm").get("id"),formId));
                predicate.add(cb.lessThanOrEqualTo(root.get("startingTime").as(String.class), endTime));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}