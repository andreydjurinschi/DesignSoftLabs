package lab01.softlab.controllers;

import lab01.softlab.entities.Role;
import lab01.softlab.entities.User;
import lab01.softlab.mask.UserFieldMask;
import lab01.softlab.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * Rest controller
 */
@RestController
@RequestMapping("/api/softlab/users")
public class UserController {

    private final UserService serv;

    public UserController(UserService serv) {
        this.serv = serv;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAll(@RequestBody UserFieldMask mask){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getAllRefToMask(mask));
    }
    @GetMapping("/find")
    public ResponseEntity<List<User>> findByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getAllByName(name));
    }
    @GetMapping("/retired")
    public ResponseEntity<List<User>> getRetiredUsers(@RequestParam Role role){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getRetired(role));
    }
}
