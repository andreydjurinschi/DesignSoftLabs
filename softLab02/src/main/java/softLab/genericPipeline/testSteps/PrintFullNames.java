package softLab.genericPipeline.testSteps;


import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;

public class PrintFullNames implements IPipelineStep<String, String> {
    @Override
    public Context<String> Execute(Context<String> context) {
        context.getData().stream().forEach(System.out::println);
        return context;
    }
}
