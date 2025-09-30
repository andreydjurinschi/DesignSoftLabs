package softLab.genericPipeline.steps.dependet;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import java.util.List;

public class GenerateUsernameStep implements IPipelineStep<User, String> {
    @Override
    public Context<String> Execute(Context<User> context) {
        List<User> users = context.getData();
        return new Context<>(
                context.getData()
                        .stream().map(u -> u.getName() + "." + u.getSurname() + "@usm.md")
                        .toList()
        );
    }
}
