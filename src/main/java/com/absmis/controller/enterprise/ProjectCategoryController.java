package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.ProjectCategory;
import com.absmis.service.enterprise.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectCategoryController {
    @Autowired
    ProjectCategoryService projectCategoryService;



    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllProjectCategorys", method = RequestMethod.GET)
    public List<ProjectCategory> findProjectCategory()throws Exception {
        List<ProjectCategory> projectCategories = projectCategoryService.findAllT();
        return projectCategories;
    }
}
