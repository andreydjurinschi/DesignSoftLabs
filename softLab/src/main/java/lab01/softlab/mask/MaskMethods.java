package lab01.softlab.mask;

import lab01.softlab.entities.User;
import lab01.softlab.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class MaskMethods {

    private final UserRepository repo;
    private final Logger logger = LoggerFactory.getLogger(MaskMethods.class);

    public MaskMethods(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> merge(UserFieldMask mask){
        List<User> allUsers = repo.findAll();
        List<User> merged = new ArrayList<>();
        for(var u : allUsers){

            User exists = (User) merged.stream().filter(
                    u1 -> equalByMask(u1, u, mask)
            ).findFirst().orElse(null);

            if(exists == null){
                User copy = new User();
                copy.setName(u.getName());
                copy.setAge(u.getAge());
                copy.setRole(u.getRole());
                copy.setRating(u.getRating());
                merged.add(copy);
            }else{
                exists.setRating((exists.getRating() + u.getRating()) / 2);
            }
        }
        return merged;
    }

    private boolean equalByMask(User u1, User u2, UserFieldMask mask){
        if(mask.isAge() && u1.getAge() != u2.getAge()){
            return false;
        }
        if(mask.isName() && !u1.getName().equals(u2.getName())){
            return false;
        }
        if(mask.isRating() && u1.getRating() != u2.getRating()){
            return false;
        }
        if(mask.isRole() && !u1.getRole().equals(u2.getRole())){
            return false;
        }
        return true;
    }
}
