package lab01.softlab.printer;

import lab01.softlab.entities.User;
import lab01.softlab.mask.UserFieldMask;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class Printer {
    /**
     * Prints the user data specified in mask
     *
     * @param user {@link User}
     * @param mask {@link UserFieldMask}
     * @return map of key and user field values
     */
    public static Map<String, Object> print(User user, UserFieldMask mask){
        Map<String, Object> res = new HashMap<>();

        if(mask.isId()){
            res.put("id", user.getId());
        }
        if(mask.isName()){
            res.put("name", user.getName());
        }
        if(mask.isAge()){
            res.put("age", user.getAge());
        }
        if(mask.isRating()){
            res.put("rating", user.getRating());
        }
        if(mask.isRole()){
            res.put("role", user.getRole());
        }
        return res;
    }
}
