package lab01.softlab.service;

import lab01.softlab.entities.Role;
import lab01.softlab.entities.User;
import lab01.softlab.mask.UserFieldMask;
import lab01.softlab.printer.Printer;
import lab01.softlab.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository repo;;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    /**
     *
     * @param mask
     * @return
     */
    public List<Object> getAllRefToMask(UserFieldMask mask){
        List<User> allUsers = repo.findAll();
        List<Object> res = new ArrayList<>();
        for(var user : allUsers){
           res.add(Printer.print(user, mask));
        }
        return res;
    }

    public List<User> getAllByName(String name){
        return repo.findByName(name);
    }

    public List<User> getRetired(Role role){
        return repo.findRetiredUsersByRole(role);
    }
}
