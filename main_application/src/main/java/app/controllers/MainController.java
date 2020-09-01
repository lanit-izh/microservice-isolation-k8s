package app.controllers;

import app.model.OrgUserModel;
import app.service.requerst.RestServiceRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private RestServiceRequest restServiceRequest;

    @RequestMapping(value = "/get", method = RequestMethod.GET,  produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
    @ApiOperation(value = "Получить имя вызванного сервиса", consumes = "Имя вызванного сервиса")
    public ResponseEntity<String> getServiceName() {
        String response = restServiceRequest.getName(String.class);
        if(!response.contains("Production")){
            response="Simulator Service";
        }
        return new ResponseEntity<>("Вызван метод сервиса '"+response+"'\n", HttpStatus.OK);
    }



    @RequestMapping(value = "/get_user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    @ApiOperation(value = "Получения пользователя организации")
    public ResponseEntity<OrgUserModel> getUser() {
        OrgUserModel orgUserModel = restServiceRequest.getUser(OrgUserModel.class);

        return new ResponseEntity<OrgUserModel>(orgUserModel, HttpStatus.OK);
    }



}
