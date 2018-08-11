package ru.bellintegrator.practice.organization.controller;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationListView;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.response.ErrorResponse;
import ru.bellintegrator.practice.response.SuccessResponse;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value="/",produces = APPLICATION_JSON_VALUE)
public class OrganizationController {
    private final OrganizationService organizationService;
    private static Logger log = LoggerFactory.getLogger(OrganizationController.class.getName());

    @Autowired
    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "getOrganization",nickname = "getOrganization",httpMethod = "GET")
    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    @ResponseBody
    public OrganizationView metod (@RequestParam("id") String id ){
            Long _id = new Long(id);
            return organizationService.getOrganization(_id);
    }

    @ApiOperation(value = "getAllOrganization",nickname = "getAllOrganization",httpMethod = "GET")
    @RequestMapping(value = "/organization/all", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationView> metodAll () throws ExceptionValid{
        return organizationService.organizaton();
    }

    @ApiOperation(value = "getOrganization",nickname = "getOrganization",httpMethod = "POST")
    @PostMapping("/organization/list")
    public List<OrganizationListView> organizations(@Valid @RequestBody OrganizationView organizationView, Errors errors) throws ExceptionValid {
        return organizationService.getFilterOrg(organizationView.name,organizationView.inn,organizationView.is_active);
    }

    @ApiOperation(value = "saveOrganization",nickname = "saveOrganization",httpMethod = "POST")
    @PostMapping("/organization/save")
    public SuccessResponse saveOrganization (@RequestBody OrganizationView organizationView) throws ExceptionValid{

        organizationService.save(organizationView);
        return new SuccessResponse("success");
    }

    @ApiOperation(value = "saveOrganization",nickname = "saveOrganization",httpMethod = "POST")
    @PostMapping("/organization/update")
    public SuccessResponse updateOrganization (@RequestBody OrganizationView organizationView, Errors errors) throws ExceptionValid{
        organizationService.update(organizationView);
        return new SuccessResponse("success");
    }
}