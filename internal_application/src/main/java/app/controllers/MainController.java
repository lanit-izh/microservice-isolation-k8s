package app.controllers;



import app.model.OrgUserModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "/get_name", method = RequestMethod.GET,  produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
    public ResponseEntity<String> getServiceName() {
        return new ResponseEntity<>("Production service", HttpStatus.OK);
    }


    @RequestMapping(value = "/get_user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    @ApiOperation(value = "Получения пользователя ")
    public ResponseEntity<OrgUserModel> getUser() {
        OrgUserModel orgUserModel = new OrgUserModel();
        orgUserModel.setId(1);
        orgUserModel.setOrganization_id(2);
        orgUserModel.setUser_name("Иван Иванович");

        return new ResponseEntity<OrgUserModel>(orgUserModel, HttpStatus.OK);
    }
}
