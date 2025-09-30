package softLab.genericPipeline.steps.dependet;

import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;

import java.util.List;

public class PrintNewUsernamesStep implements IPipelineStep<String, String> {
    @Override
    public Context<String> Execute(Context<String> context) {
        List<String> usernames = context.getData();
        usernames.forEach(System.out::println);
        return context;
    }
}
