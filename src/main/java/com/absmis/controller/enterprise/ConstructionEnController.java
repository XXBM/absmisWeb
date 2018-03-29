package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.*;
import com.absmis.domain.message.ConstructionEnInfo;
import com.absmis.domain.message.ConstructionEnStatistics;
import com.absmis.service.enterprise.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConstructionEnController {
    @Autowired
    ConstructionEnService constructionEnService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    CheckedStatusService checkedStatusService;
    @Autowired
    RealEstateEnService realEstateEnService;
    @Autowired
    DesignerService designerService;
    @Autowired
    BuilderService builderService;
    @Autowired
    ConstructionEnIndustrializationService constructionEnIndustrializationService;

    /**
     * 统计行业信息1-机具设备企业产业化信息
     */
    @RequestMapping(value = "/getAllConstructionEns", method = RequestMethod.GET)
    public Map<String, Object> getConstructionEns(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size
    ) throws Exception {
        List<ConstructionEn> constructionEns = constructionEnService.findAllT();
        List<ConstructionEnStatistics> constructionEnStatisticses = new ArrayList<>();
        for (int i = 0; i < constructionEns.size(); i++) {
            Specification<ConstructionEnIndustrialization> constructionEnIndustrializationSpecificationA = this.constructionEnIndustrializationService.queryAnnual(constructionEns.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializationsA = constructionEnIndustrializationService.findBySepc(constructionEnIndustrializationSpecificationA);
            double annualScale = 0;
            for (int x = 0; x < constructionEnIndustrializationsA.size(); x++) {
                annualScale += constructionEnIndustrializationsA.get(x).getTotalScale();
            }

            Specification<ConstructionEnIndustrialization> constructionEnIndustrializationSpecification = this.constructionEnIndustrializationService.queryTotalScale(constructionEns.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(constructionEnIndustrializationSpecification);
            double totalScale = 0;
            if (constructionEns.get(i).getCumulant() != null) {
                totalScale += constructionEns.get(i).getCumulant();
            }
            for (int x = 0; x < constructionEnIndustrializations.size(); x++) {
                totalScale += constructionEnIndustrializations.get(x).getTotalScale();
            }

            ConstructionEnStatistics ces = new ConstructionEnStatistics(constructionEns.get(i).getName(), constructionEns.get(i).getEnterpriseType(), totalScale, annualScale);
            constructionEnStatisticses.add(ces);

        }
        Map<String, Object> map = new HashMap<String, Object>();
        //查到的总用户数
        map.put("total", constructionEnStatisticses.size());

        //总页数
        int pageTimes;
        if (constructionEnStatisticses.size() % size == 0) {
            pageTimes = constructionEnStatisticses.size() / size;
        } else {
            pageTimes = constructionEnStatisticses.size() / size + 1;
        }
        map.put("pageTimes", pageTimes);

        List<ConstructionEnStatistics> newConstructionEnStatistics = new ArrayList<ConstructionEnStatistics>();
        //每页开始的第几条记录
        if (pageTimes == page) {
            newConstructionEnStatistics.addAll(constructionEnStatisticses.subList((page - 1) * size, constructionEnStatisticses.size()));
        } else {
            newConstructionEnStatistics.addAll(constructionEnStatisticses.subList((page - 1) * size, (page - 1) * size + size));
        }
        map.put("rows", newConstructionEnStatistics);
        return map;
    }

    //根据企业和申报起止时间查询
    @RequestMapping(value = "/querytjConstructionEn", method = RequestMethod.GET)
    public List<ConstructionEnInfo> queryConstructionEn(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "quarter") Integer quarter
    ) throws Exception {
        Specification<RealEstateEn> realEstateEnSpecification = this.realEstateEnService.queryByCumulant();
        List<RealEstateEn> realEstateEns = realEstateEnService.findBySepc(realEstateEnSpecification);
        Specification<RealEstateEn> realEstateEnSpecificationM = this.realEstateEnService.queryByAnnualCumulant(year, quarter);
        List<RealEstateEn> realEstateEnsM = realEstateEnService.findBySepc(realEstateEnSpecificationM);
        double totalScaleR = 0;
        double annualScaleR = 0;
        for (int i = 0; i < realEstateEns.size(); i++) {
            if (realEstateEns.get(i).getCumulant() != null) {
                totalScaleR += realEstateEns.get(i).getCumulant();
            }
            Specification<ConstructionEnIndustrialization> ceiSp = this.constructionEnIndustrializationService.queryAnnual(realEstateEns.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(ceiSp);
            for (int x = 0; x < constructionEnIndustrializations.size(); x++) {
                annualScaleR += constructionEnIndustrializations.get(x).getTotalScale();
            }
            Specification<ConstructionEnIndustrialization> ceiSpR = this.constructionEnIndustrializationService.queryTotalScale(realEstateEns.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializationsR = constructionEnIndustrializationService.findBySepc(ceiSpR);
            for (int x = 0; x < constructionEnIndustrializationsR.size(); x++) {
                totalScaleR += constructionEnIndustrializationsR.get(x).getTotalScale();
            }
        }
        for (int i = 0; i < realEstateEnsM.size(); i++) {
            if (realEstateEnsM.get(i).getCumulant() != null) {
                totalScaleR += realEstateEnsM.get(i).getCumulant();
            }
            Specification<ConstructionEnIndustrialization> ceiSp = this.constructionEnIndustrializationService.queryAnnual(realEstateEnsM.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(ceiSp);
            for (int x = 0; x < constructionEnIndustrializations.size(); x++) {
                annualScaleR += constructionEnIndustrializations.get(x).getTotalScale();
            }
            Specification<ConstructionEnIndustrialization> ceiSpR = this.constructionEnIndustrializationService.queryTotalScale(realEstateEnsM.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializationsR = constructionEnIndustrializationService.findBySepc(ceiSpR);
            for (int x = 0; x < constructionEnIndustrializationsR.size(); x++) {
                totalScaleR += constructionEnIndustrializationsR.get(x).getTotalScale();
            }
        }
        Specification<Builder> builderSpecification = this.builderService.queryByCumulant();
        List<Builder> builders = builderService.findBySepc(builderSpecification);
        Specification<Builder> builderSpecificationM = this.builderService.queryByAnnualCumulant(year, quarter);
        List<Builder> buildersM = builderService.findBySepc(builderSpecificationM);
        double totalScaleB = 0;
        double annualScaleB = 0;
        for (int i = 0; i < builders.size(); i++) {
            if (builders.get(i).getCumulant() != null) {
                totalScaleB += builders.get(i).getCumulant();
            }
            Specification<ConstructionEnIndustrialization> ceiSp = this.constructionEnIndustrializationService.queryAnnual(builders.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(ceiSp);
            for (int x = 0; x < constructionEnIndustrializations.size(); x++) {
                annualScaleB += constructionEnIndustrializations.get(x).getTotalScale();
            }
            Specification<ConstructionEnIndustrialization> ceiSpB = this.constructionEnIndustrializationService.queryTotalScale(builders.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializationsB = constructionEnIndustrializationService.findBySepc(ceiSpB);
            for (int x = 0; x < constructionEnIndustrializationsB.size(); x++) {
                totalScaleB += constructionEnIndustrializationsB.get(x).getTotalScale();
            }
        }
        for (int i = 0; i < buildersM.size(); i++) {
            if (buildersM.get(i).getCumulant() != null) {
                totalScaleB += buildersM.get(i).getCumulant();
            }
            Specification<ConstructionEnIndustrialization> ceiSp = this.constructionEnIndustrializationService.queryAnnual(buildersM.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(ceiSp);
            for (int x = 0; x < constructionEnIndustrializations.size(); x++) {
                annualScaleB += constructionEnIndustrializations.get(x).getTotalScale();
            }
            Specification<ConstructionEnIndustrialization> ceiSpB = this.constructionEnIndustrializationService.queryTotalScale(buildersM.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializationsB = constructionEnIndustrializationService.findBySepc(ceiSpB);
            for (int x = 0; x < constructionEnIndustrializationsB.size(); x++) {
                totalScaleB += constructionEnIndustrializationsB.get(x).getTotalScale();
            }
        }
        Specification<Designer> designerSpecification = this.designerService.queryByCumulant();
        List<Designer> designers = designerService.findBySepc(designerSpecification);
        Specification<Designer> designerSpecificationM = this.designerService.queryByAnnualCumulant(year, quarter);
        List<Designer> designersM = designerService.findBySepc(designerSpecificationM);
        double totalScaleD = 0;
        double annualScaleD = 0;
        for (int i = 0; i < designers.size(); i++) {
            if (designers.get(i).getCumulant() != null) {
                totalScaleD += designers.get(i).getCumulant();
            }
            Specification<ConstructionEnIndustrialization> ceiSp = this.constructionEnIndustrializationService.queryAnnual(designers.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(ceiSp);
            for (int x = 0; x < constructionEnIndustrializations.size(); x++) {
                annualScaleD += constructionEnIndustrializations.get(x).getTotalScale();
            }
            Specification<ConstructionEnIndustrialization> ceiSpD = this.constructionEnIndustrializationService.queryTotalScale(designers.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializationsD = constructionEnIndustrializationService.findBySepc(ceiSpD);
            for (int x = 0; x < constructionEnIndustrializationsD.size(); x++) {
                totalScaleD += constructionEnIndustrializationsD.get(x).getTotalScale();
            }
        }
        for (int i = 0; i < designersM.size(); i++) {
            if (designersM.get(i).getCumulant() != null) {
                totalScaleD += designersM.get(i).getCumulant();
            }
            Specification<ConstructionEnIndustrialization> ceiSp = this.constructionEnIndustrializationService.queryAnnual(designersM.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializations = constructionEnIndustrializationService.findBySepc(ceiSp);
            for (int x = 0; x < constructionEnIndustrializations.size(); x++) {
                annualScaleD += constructionEnIndustrializations.get(x).getTotalScale();
            }
            Specification<ConstructionEnIndustrialization> ceiSpD = this.constructionEnIndustrializationService.queryTotalScale(designersM.get(i).getId(), year, quarter);
            List<ConstructionEnIndustrialization> constructionEnIndustrializationsD = constructionEnIndustrializationService.findBySepc(ceiSpD);
            for (int x = 0; x < constructionEnIndustrializationsD.size(); x++) {
                totalScaleD += constructionEnIndustrializationsD.get(x).getTotalScale();
            }
        }
        List<ConstructionEnInfo> constructionEnInfos = new ArrayList<>();
        ConstructionEnInfo integralWallEnInfo = new ConstructionEnInfo("房地产开发企业", (double) (realEstateEns.size() + realEstateEnsM.size()), totalScaleR, annualScaleR);
        constructionEnInfos.add(integralWallEnInfo);
        ConstructionEnInfo integrativeExternalWallEnInfo = new ConstructionEnInfo("施工单位", (double) (builders.size() + buildersM.size()), totalScaleB, annualScaleB);
        constructionEnInfos.add(integrativeExternalWallEnInfo);
        ConstructionEnInfo prebuiltStairsEnInfo = new ConstructionEnInfo("设计单位", (double) (designers.size() + designersM.size()), totalScaleD, annualScaleD);
        constructionEnInfos.add(prebuiltStairsEnInfo);
        return constructionEnInfos;
    }


    //根据企业名称模糊查询
    @RequestMapping(value = "/queryConstructionEnByName", method = RequestMethod.GET)
    public Map<String, Object> queryConstructionEnByName(
            @RequestParam(value = "nameQuery") String query,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size) throws Exception {
        Pageable pageable = new PageRequest(page - 1, size);
        Specification<ConstructionEn> specification = this.constructionEnService.queryName(query);
        Page<ConstructionEn> list = this.constructionEnService.findBySepc(specification, pageable);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.constructionEnService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    /**
     * 获取到所有传统企业
     */
    @RequestMapping(value = "/findAllConstructionEns", method = RequestMethod.GET)
    public List<ConstructionEn> findConstructionEn() throws Exception {
        List<ConstructionEn> constructionEns = constructionEnService.findAllT();
        return constructionEns;
    }

    //实现分页 获取所有传统企业
    @RequestMapping(value = "/displayAllConstructionEns", method = RequestMethod.GET)
    public Map<String, Object> findAllConstructionEn(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size) throws Exception {
        Page<ConstructionEn> list = this.constructionEnService.findAllT(new PageRequest(page - 1, size));
        Map<String, Object> map = new HashMap<String, Object>();
        int total = this.constructionEnService.findAllT().size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //非传统企业审核信息    完成 改
    @RequestMapping(value = "/updateTraditionalEn", method = RequestMethod.POST)
    public Map<String, Object> updateTraditionalEn(
            @RequestParam("id") Long id,
            @RequestParam("checkedStatusId") Long checkedId
    ) throws Exception {
        Organization organization = organizationService.findOne(id);
        organization.setCheckedStatus(checkedStatusService.findOne(checkedId));
        this.organizationService.update(organization);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project", organization);
        return map;
    }

    //实现分页 获取非传统企业
    @RequestMapping(value = "/displayOrganizations", method = RequestMethod.GET)
    public Map<String, Object> findAllOranization(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer size) throws Exception {
        List<Organization> organizations = organizationService.findAllT();
        List<ConstructionEn> constructionEns = constructionEnService.findAllT();
        organizations.removeAll(constructionEns);
        List<Long> property = new ArrayList<>();
        for (int i = 0; i < organizations.size(); i++) {
            property.add(organizations.get(i).getId());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Pageable pageable = new PageRequest(page - 1, size);
        Specification<Organization> specification = this.organizationService.findNoTra(property);
        Page<Organization> list = this.organizationService.findBySepc(specification, pageable);
        int total = this.organizationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //模糊查询 获取非传统企业
    @RequestMapping(value = "/queryOrganizations", method = RequestMethod.GET)
    public Map<String, Object> findAllOranization(
            @RequestParam(value = "queryName") String name,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer size) throws Exception {
        List<Organization> organizations = organizationService.findAllT();
        List<ConstructionEn> constructionEns = constructionEnService.findAllT();
        organizations.removeAll(constructionEns);
        List<Long> property = new ArrayList<>();
        for (int i = 0; i < organizations.size(); i++) {
            property.add(organizations.get(i).getId());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Pageable pageable = new PageRequest(page - 1, size);
        Specification<Organization> specification = this.organizationService.findNoTraAndQueryName(property, name);
        Page<Organization> list = this.organizationService.findBySepc(specification, pageable);
        int total = this.organizationService.findBySepc(specification).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }
}
