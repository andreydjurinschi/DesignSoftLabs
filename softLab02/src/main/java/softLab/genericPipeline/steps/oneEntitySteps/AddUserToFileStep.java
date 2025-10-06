package softLab.genericPipeline.steps.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.utils.JsonWrite;
import softLab.visitor.IVisitor;

public class AddUserToFileStep implements IPipelineStep<User, User> {
    private StringBuilder builder = new StringBuilder();
    private String fileName;

    public AddUserToFileStep(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Context<User> Execute(Context<User> context) {
        JsonWrite.addUser(fileName, context.getUser());
        builder.append("Process of user creating is finished, check the last user in ").append(fileName);
        return context;
    }

    public String accept(IVisitor visitor){
        return visitor.visitAddUser(this, builder);
    }
}
