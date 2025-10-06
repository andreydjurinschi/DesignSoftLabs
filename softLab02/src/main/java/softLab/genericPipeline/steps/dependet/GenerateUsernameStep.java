package softLab.genericPipeline.steps.dependet;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.visitor.IVisitor;

import java.util.List;

public class GenerateUsernameStep implements IPipelineStep<User, String> {

    private StringBuilder builder = new StringBuilder();

    @Override
    public Context<String> Execute(Context<User> context) {
        List<User> users = context.getData();
        builder.append("Generating usernames for all users above");
        return new Context<>(
                context.getData()
                        .stream().map(u -> u.getName().toLowerCase() + "." + u.getSurname().toLowerCase() + "@usm.md")
                        .toList()
        );
    }

    public String accept(IVisitor visitor){
        return visitor.visitGenerateUsernamesStep(this, builder);
    }
}
