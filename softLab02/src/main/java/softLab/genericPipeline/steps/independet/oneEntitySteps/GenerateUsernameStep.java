package softLab.genericPipeline.steps.independet.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.visitor.IVisitor;

public class GenerateUsernameStep implements IPipelineStep<User, User> {
    @Override
    public Context<User> Execute(Context<User> context) {
        User u = context.getUser();
        if(!context.isValid()){
            u.setUsername(u.getName() + "." + u.getSurname() + "@usm.md");
            context.setValid(true);
        }
        return context;
    }

    public String accept(IVisitor visitor){
        return visitor.visitGenerateUsernameStep(this);
    }

}
