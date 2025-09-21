package lab01.softlab.mask;

import lab01.softlab.entities.User;
import lab01.softlab.printer.Printer;
import lab01.softlab.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MaskMethods {

    private final UserRepository repo;

    public MaskMethods(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> merge(UserFieldMask mask){
        List<User> allUsers = repo.findAll();
        Map<String, List<User>> groups = new HashMap<>();

        for(User u : allUsers){
            String key = createKey(u, mask);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(u);
        }

        List<User> merged = new ArrayList<>();

        for(List<User> group : groups.values()){
            if(group.size() > 1){
                User representative = new User();
                User first = group.get(0);

                representative.setName(first.getName());
                representative.setAge(first.getAge());
                representative.setRole(first.getRole());

                double avgRating = group.stream()
                        .mapToDouble(User::getRating)
                        .average().getAsDouble();
                representative.setRating((float) avgRating);

                merged.add(representative);
            }
        }
        return merged;
    }

    private String createKey(User user, UserFieldMask mask){
        StringBuilder key = new StringBuilder();
        if(mask.isAge()) key.append("age:").append(user.getAge()).append(";");
        if(mask.isName()) key.append("name:").append(user.getName()).append(";");
        if(mask.isRole()) key.append("role:").append(user.getRole()).append(";");
        if(mask.isRating()) key.append("rating:").append(user.getRating()).append(";");
        return key.toString();
    }
}
