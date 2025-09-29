package softLab.simplePipline.steps.dependet;

import softLab.entities.User;
import softLab.simplePipline.Context;
import softLab.simplePipline.IPipelineStep;
import softLab.simplePipline.steps.independet.CheckUsernameStep;

import java.util.*;

/**
 * -----------------------------------------
 * IMPORTANT! before calling this step needs to be called
 * you need to call {@link CheckUsernameStep}
 * -----------------------------------------
 * step generate the username for user
 */
public class GenerateUsernameStep implements IPipelineStep {
    @Override
    public void execute(Context context) {
        List<User> usersWithoutUsernames = context.getUsersWithoutUsernames();
        Map<String,String> generatedUsernames = new HashMap<>();

        usersWithoutUsernames.forEach(user -> {
            int date = user.getDateOfBirth().getYear();
            String username = user.getName().toLowerCase()
                   + "."
                   + user.getSurname().toLowerCase()
                   + date;
            generatedUsernames.put(user.getName(), username);
            user.setUsername(username);
        });
        for(var item : generatedUsernames.entrySet()){
            System.out.println("Username generated for: " + item.getKey() + ": " + item.getValue());
        }

    }
}
