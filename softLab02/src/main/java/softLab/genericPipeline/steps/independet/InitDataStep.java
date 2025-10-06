package softLab.genericPipeline.steps.independet;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.utils.JsonRead;
import softLab.visitor.IVisitor;
import softLab.visitor.Visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitDataStep implements IPipelineStep<Void, User> {

    private StringBuilder builder = new StringBuilder();

    @Override
    public Context<User> Execute(Context<Void> context) {
        List<User> users = new ArrayList<>();
        try {
            users = JsonRead.deserialize();
            builder.append("Data is deserialized successfully");
        } catch (IOException e) {
            context.setDone(true);
            builder.append("Problem with deserialization - ").append(e.getMessage());
        }
        Context<User> newContext = new Context<>(users);
        newContext.setDone(context.isDone());
        return newContext;
    }

    public String accept(IVisitor visitor){
        return visitor.visitInitDataStep(this, builder);
    }

}
