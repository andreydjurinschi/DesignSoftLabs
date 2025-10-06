package softLab.genericPipeline.steps.independet.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.visitor.IVisitor;

public class GenerateUsernameStep implements IPipelineStep<User, User> {

    private StringBuilder builder = new StringBuilder();

    public StringBuilder getBuilder() {
        return builder;
    }

    @Override
    public Context<User> Execute(Context<User> context) {
        User u = context.getUser();
        if(!context.isValid()){
            builder.append("Creating an username for ").append(u.getName());
            u.setUsername(u.getName().toLowerCase() + "." + u.getSurname().toLowerCase() + "@usm.md");
            builder.append("Username is created ").append(u.getUsername());
            context.setValid(true);
        }else{
            builder.append("Skipping step, username is set");
        }
        return context;
    }

    public String accept(IVisitor visitor){
        return visitor.visitGenerateUsernameStep(this, builder);
    }

}
