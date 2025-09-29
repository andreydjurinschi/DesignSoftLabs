package softLab.simplePipline.steps.independet;

import softLab.entities.User;
import softLab.simplePipline.Context;
import softLab.simplePipline.IPipelineStep;

import java.util.List;

/**
 * ------------------------------------------------
 * Independent step
 * ------------------------------------------------
 * Step for checking if every user have an username
 */

public class CheckUsernameStep implements IPipelineStep {

    @Override
    public void execute(Context context) {
        List<User> withoutUsernames = context.getAllUsers().stream()
                                      .filter(u -> u.getUsername() == null)
                                      .toList();
        withoutUsernames.forEach(u -> {
            System.out.println("name: " + u.getName());
        });
        context.setUsersWithoutUsernames(withoutUsernames);
    }
}
