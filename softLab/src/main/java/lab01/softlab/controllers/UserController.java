package lab01.softlab.controllers;


import lab01.softlab.entities.Role;
import lab01.softlab.entities.User;
import lab01.softlab.mask.MaskMethods;
import lab01.softlab.mask.UserFieldMask;
import lab01.softlab.printer.Printer;
import lab01.softlab.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



/**
 * Rest controller
 */
@RestController
@RequestMapping("/api/softlab/users")
public class UserController {

    private final UserService serv;
    private final MaskMethods maskMethods;

    public UserController(UserService serv, MaskMethods maskMethods) {
        this.serv = serv;
        this.maskMethods = maskMethods;
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
    @GetMapping("/merge")
    public ResponseEntity<List<Object>> mergeUsers(@RequestBody UserFieldMask mask){
        List<User> merged = maskMethods.merge(mask);
        List<Object> result = new ArrayList<>();
        UserFieldMask mask1 = new UserFieldMask();
        mask1.setName(true);
        mask1.setAge(true);
        mask1.setRating(true);
        for(var u : merged){
            result.add(Printer.print(u, mask1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
