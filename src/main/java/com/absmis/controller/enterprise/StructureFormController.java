package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.Project;
import com.absmis.domain.enterprise.StructureForm;
import com.absmis.domain.enterprise.UnitEngineering;
import com.absmis.service.enterprise.ProjectService;
import com.absmis.service.enterprise.StructureFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class StructureFormController {
    @Autowired
    StructureFormService structureFormService;
    @Autowired
    ProjectService projectService;



    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllStructureForms", method = RequestMethod.GET)
    public List<StructureForm> findStructureForm()throws Exception {
        List<StructureForm> structureForms = structureFormService.findAllT();
        return structureForms;
    }

    @RequestMapping(value = "/findStructureForm", method = RequestMethod.GET)
    public List<StructureForm> getStructureForm(
            @RequestParam(value = "id") Long id
    )throws Exception {
        Project project = projectService.findById(id);
        List<StructureForm> structureForms = new ArrayList<>();
        List<UnitEngineering> unitEngineerings = project.getUnitEngineerings();
        for(int i=0;i<unitEngineerings.size();i++){
            structureForms.add(unitEngineerings.get(i).getStructureForm());
        }
        List newList = new ArrayList(new HashSet(structureForms));
        return newList;
    }
}
