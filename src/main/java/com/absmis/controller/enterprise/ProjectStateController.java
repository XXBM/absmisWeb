package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.ProjectState;
import com.absmis.service.enterprise.ProjectStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectStateController {
    @Autowired
    ProjectStateService projectStateService;



    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllProjectStates", method = RequestMethod.GET)
    public List<ProjectState> findProjectState()throws Exception {
        List<ProjectState> projectStates = projectStateService.findAllT();
        return projectStates;
    }
}
