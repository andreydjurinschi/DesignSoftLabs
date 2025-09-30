package softLab.genericPipeline.steps.dependet;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;

import java.util.List;

public class UsersWithoutUsernameStep implements IPipelineStep<User, User> {

    @Override
    public Context<User> Execute(Context<User> context) {
        List<User> allUsers = context.getData();
        return new Context<>(allUsers.stream().filter(u-> u.getUsername() == null).toList());
    }
}
