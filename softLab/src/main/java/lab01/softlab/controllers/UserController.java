package lab01.softlab.controllers;

import lab01.softlab.mask.UserFieldMask;
import lab01.softlab.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



/**
 * Rest controller
 */
@RestController
@RequestMapping("/api/softlab")
public class UserController {

    private final UserService serv;

    public UserController(UserService serv) {
        this.serv = serv;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<Object>> getAll(@RequestBody UserFieldMask mask){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getAllRefToMask(mask));
    }

}
