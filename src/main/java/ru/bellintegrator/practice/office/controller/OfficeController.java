package ru.bellintegrator.practice.office.controller;

import java.util.List;
import io.swagger.annotations.ApiOperation;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeListView;
import ru.bellintegrator.practice.office.view.OfficeOutListView;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.response.SuccessResponse;


@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OfficeController {
  private static Logger log = LoggerFactory.getLogger(OfficeController.class.getName());
  private final OfficeService officeService;

  @Autowired
  public OfficeController(OfficeService officeService) {
    this.officeService = officeService;
  }

  @ApiOperation(value = "getOffice", nickname = "getOffice", httpMethod = "GET")
  @RequestMapping(value = "/office", method = RequestMethod.GET)
  public Office metod(@RequestParam("id") String id) throws ExceptionValid {
    return officeService.getById(id);
  }

  @ApiOperation(value = "getAllOoffice", nickname = "getAllOffice", httpMethod = "GET")
  @RequestMapping(value = "/office/all", method = RequestMethod.GET)
  public List<OfficeOutListView> metodAll() {
    return officeService.office();
  }

  @ApiOperation(value = "getOfficeFilter", nickname = "getFilter", httpMethod = "POST")
  @RequestMapping(value = "/office/list", method = RequestMethod.POST)
  public List<OfficeOutListView> offices(@RequestBody OfficeListView officeView,
                                         Errors result) throws ExceptionValid {
    return officeService.getFilter(officeView.org_id, officeView.name, officeView.is_active);
  }

  @ApiOperation(value = "saveoffice", nickname = "saveoffice", httpMethod = "POST")
  @PostMapping("/office/save")
  public SuccessResponse saveOrganization(@RequestBody OfficeView officeView,
                                          Errors result) throws ExceptionValid {
    log.info(officeView.org_id.toString());
    officeService.save(officeView);
    return new SuccessResponse("success");
  }

  @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
  @PostMapping("/office/update")
  public SuccessResponse updateOrganization(@RequestBody OfficeView officeView,
                                            Errors result) throws ExceptionValid {
    officeService.update(officeView);
    return new SuccessResponse("success");
  }
}
