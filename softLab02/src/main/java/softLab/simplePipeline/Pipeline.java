package softLab.simplePipeline;

import softLab.simplePipeline.context.Context;

import java.util.ArrayList;
import java.util.List;

public class Pipeline implements IPipelineStep {

    List<IPipelineStep> steps = new ArrayList<>();

    public void addStep(IPipelineStep step) {
        steps.add(step);
    }

    @Override
    public void execute(Context context) {
        for(var step : steps){
            step.execute(context);
        }
    }
}
