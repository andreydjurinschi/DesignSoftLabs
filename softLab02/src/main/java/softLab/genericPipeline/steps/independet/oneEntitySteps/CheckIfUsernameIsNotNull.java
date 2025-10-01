package softLab.genericPipeline.steps.independet.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;

import java.util.Collections;

public class CheckIfUsernameIsNotNull implements IPipelineStep<User, User> {
    @Override
    public Context<User> Execute(Context<User> context) {
        User u = context.getUser();
        if(u.getUsername() == null){
            context.setValid(false);
        }
        return context;
    }
}
