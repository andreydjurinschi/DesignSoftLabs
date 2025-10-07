package softLab.genericPipeline.steps.dependet;

import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.visitor.IVisitor;

import java.util.List;

public class PrintNewUsernamesStep implements IPipelineStep<String, String> {

    StringBuilder builder = new StringBuilder();

    @Override
    public Context<String> Execute(Context<String> context) {
        List<String> usernames = context.getData();
        usernames.forEach(
                u -> {
                    builder.append(u + "\n");
                }
        );
        context.setDone(true);
        return context;
    }

    public String accept(IVisitor visitor){
        return visitor.visitPrintNewUsernamesStep(this, builder);
    }
}
