package ru.bellintegrator.practice.organization.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationListView;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.response.SuccessResponse;


@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {
  private final OrganizationService organizationService;
  private static Logger log = LoggerFactory.getLogger(OrganizationController.class.getName());

  @Autowired
  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }

  @ApiOperation(value = "getOrganization", nickname = "getOrganization", httpMethod = "GET")
  @RequestMapping(value = "/organization", method = RequestMethod.GET)
  public OrganizationView metod(@RequestParam("id") String id) throws ExceptionValid {
    return organizationService.getOrganization(id);
  }

  @ApiOperation(value = "getAllOrganization", nickname = "getAllOrganization", httpMethod = "GET")
  @RequestMapping(value = "/organization/all", method = RequestMethod.GET)
  public List<OrganizationView> metodAll() throws ExceptionValid {
    return organizationService.organizaton();
  }

  @ApiOperation(value = "getOrganization", nickname = "getOrganization", httpMethod = "POST")
  @PostMapping("/organization/list")
  public List<OrganizationListView> organizations(@Valid
                                                  @RequestBody OrganizationView organizationView,
                                                  Errors errors) throws ExceptionValid {
    return organizationService.getFilterOrg(
            organizationView.name,
            organizationView.inn,
            organizationView.is_active);
  }

  @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
  @PostMapping("/organization/save")
  public SuccessResponse saveOrganization(@RequestBody OrganizationView organizationView) throws ExceptionValid {
    organizationService.save(organizationView);
    return new SuccessResponse("success");
  }

  @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
  @PostMapping("/organization/update")
  public SuccessResponse updateOrganization(@RequestBody OrganizationView organizationView,
                                            Errors errors) throws ExceptionValid {
    organizationService.update(organizationView);
    return new SuccessResponse("success");
  }
}