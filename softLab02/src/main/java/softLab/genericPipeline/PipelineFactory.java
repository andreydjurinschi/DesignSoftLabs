package softLab.genericPipeline;

import softLab.genericPipeline.context.Context;
import softLab.genericPipeline.steps.dependet.GenerateUsernameStep;
import softLab.genericPipeline.steps.dependet.PrintNewUsernamesStep;
import softLab.genericPipeline.steps.dependet.UsersWithoutUsernameStep;
import softLab.genericPipeline.testSteps.InitDataStep;

import java.util.Collections;

public class PipelineFactory {

    public static void GenerateEmailsPipeline(){
        Context<Void> context = new Context<>(Collections.emptyList());
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new InitDataStep());
        pipeline.addStep(new UsersWithoutUsernameStep());
        pipeline.addStep(new GenerateUsernameStep());
        pipeline.addStep(new PrintNewUsernamesStep());
        pipeline.executeSteps(context);
    }
}
