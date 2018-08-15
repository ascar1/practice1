package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.response.ErrorResponse;
import ru.bellintegrator.practice.response.SuccessResponse;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserListView;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/",produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;
    private static Logger log = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }

    @ApiOperation(value = "FilterUser",nickname = "FilterUser",httpMethod = "POST")
    @PostMapping(value = "/user/list")
    public List<UserListView> Users (@RequestBody UserView userView) throws ExceptionValid{
        return userService.getFilter(userView.office_id,userView.first_name,userView.second_name,userView.middle_name,userView.position,userView.doc_Code,userView.citizenship_code);
    }

    @ApiOperation(value = "getAllUser",nickname = "getAllUser",httpMethod = "GET")
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<UserView> metodAll ()
    {
        return userService.User();
    }

    @ApiOperation(value = "getUser",nickname = "getUser",httpMethod = "GET")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public User metod (@RequestParam("id") String id)throws ExceptionValid{
            return userService.getByID(id);
    }

    @ApiOperation(value = "UpdateUser", nickname = "UpdateUser",httpMethod = "POST")
    @PostMapping("/user/update")
    public SuccessResponse updateUser (@RequestBody UserView userView, Errors result) throws ExceptionValid{
        userService.update(userView);
        return new SuccessResponse("success");
    }

    @ApiOperation(value = "UpdateUser", nickname = "UpdateUser",httpMethod = "POST")
    @PostMapping("/user/save")
    public SuccessResponse saveUser (@RequestBody UserView userView, Errors result) throws ExceptionValid {
        userService.save(userView);
        return new SuccessResponse("success");
    }
}
