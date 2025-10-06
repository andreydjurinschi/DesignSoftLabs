package softLab.genericPipeline.steps.independet.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.visitor.IVisitor;

import java.util.Collections;

public class CheckIfUsernameIsNotNull implements IPipelineStep<User, User> {

    private StringBuilder builder = new StringBuilder();

    @Override
    public Context<User> Execute(Context<User> context) {
        User u = context.getUser();
        if(u.getUsername() == null){
            builder.append("Username of ").append(u.getName()).append(" is not set\n");
            context.setValid(false);
        }else{
            builder.append("Username is set: ").append(u.getUsername());
        }
        return context;
    }
    public String accept(IVisitor visitor){
        return visitor.visitCheckIfUsernameIsNotNull(this, builder);
    }
}
