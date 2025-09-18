package lab01.softlab.mask;

import lab01.softlab.entities.User;
import lab01.softlab.printer.Printer;
import lab01.softlab.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MaskMethods {

    private final UserRepository repo;

    public MaskMethods(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> merge(UserFieldMask mask){
        List<User> allUsers = repo.findAll();
        List<User> merged = new ArrayList<>();
        for(var u : allUsers){
            User exists = merged.stream().filter(u1 -> equalByMask(u1, u, mask)).
                    findFirst().orElse(null);
            if(exists == null){
                boolean hasDuplicate = allUsers.stream().filter(u1 -> u1 != u)
                        .anyMatch(u1 -> equalByMask(u1, u , mask));
                if(hasDuplicate){
                    exists = new User();
                    exists.setName(u.getName());
                    exists.setAge(u.getAge());
                    exists.setRole(u.getRole());
                    exists.setRating(u.getRating());
                    merged.add(exists);
                }
            }else{
                double avg = allUsers.stream()
                        .filter(u1 -> equalByMask(u1, u, mask))
                        .mapToDouble(User::getRating)
                        .average()
                        .orElse(u.getRating());
                exists.setRating((float) avg);
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
