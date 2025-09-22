package lab01.softlab.mask;

import lab01.softlab.entities.User;
import lab01.softlab.repo.UserRepository;
import lab01.softlab.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.CompositeUriComponentsContributor;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MaskMethods {

    private final UserRepository repo;
    private final UserService userService;
    private final CompositeUriComponentsContributor compositeUriComponentsContributor;

    public MaskMethods(UserRepository repo, UserService userService, CompositeUriComponentsContributor compositeUriComponentsContributor) {
        this.repo = repo;
        this.userService = userService;
        this.compositeUriComponentsContributor = compositeUriComponentsContributor;
    }



    public List<User> merge(UserFieldMask mask){
        List<User> allUsers = repo.findAll();
        Map<String, List<User>> duplicates = allUsers.stream()
                .collect(Collectors.groupingBy(u -> keyGenerator(u, mask)));

        List<User> merged = new ArrayList<>();

        for(var userGroup : duplicates.values()){
            if(userGroup.size() > 1){
                User target = new User();
                setFieldsAccordingToMask(target, userGroup.get(0), mask);
                double rating = 0;
                int userCount = 0;
                for(var u : userGroup){
                    rating += u.getRating();
                    userCount++;
                }
                target.setRating((float) (rating / userCount));
                merged.add(target);
            }
        }
        return merged;
    }

    /**
     * Key generator searching duplicates
     * @param user {@link User}
     * @param mask {@link UserFieldMask}
     * @return String, key value
     */
    private String keyGenerator(User user, UserFieldMask mask){
        StringBuilder key = new StringBuilder();
        if(mask.isId()){
            key.append("id: ").append(user.getId()).append(";");
        }
        if(mask.isAge()){
            key.append("age: ").append(user.getAge()).append(";");
        }
        if(mask.isName()){
            key.append("name: ").append(user.getAge()).append(";");
        }
        if(mask.isRating()){
            key.append("rating: ").append(user.getRating()).append(";");
        }
        if(mask.isRole()){
            key.append("role: ").append(user.getRole()).append(";");
        }
        return key.toString();
    }

    private void setFieldsAccordingToMask(User targetUser, User sourceUser, UserFieldMask mask) {
        if (mask.isAge()) {
            targetUser.setAge(sourceUser.getAge());
        }
        if (mask.isName()) {
            targetUser.setName(sourceUser.getName());
        }
        if (mask.isRating()) {
            targetUser.setRating(sourceUser.getRating());
        }
        if (mask.isRole()) {
            targetUser.setRole(sourceUser.getRole());
        }
    }
}
