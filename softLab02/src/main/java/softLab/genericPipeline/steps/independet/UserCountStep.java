package softLab.genericPipeline.steps.independet;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;

import java.util.List;

public class UserCountStep implements IPipelineStep<User, User> {
    @Override
    public Context<User> Execute(Context<User> context) {
        System.out.println(context.count());
        return context;
    }
}
