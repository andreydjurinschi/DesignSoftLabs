package softLab.genericPipeline;

import softLab.genericPipeline.context.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pipeline{
    private List<IPipelineStep<?, ? >> steps = new ArrayList<>();

    public void addStep(IPipelineStep<?, ? > step){
        steps.add(step);
    }

    public <T> Context<T> executeSteps(Context<?> data){
        Context<?> intermediateData = data;
        for(IPipelineStep<?, ? > step : steps){
            intermediateData = ((IPipelineStep<Object, Object>) step).Execute((Context<Object>) intermediateData);
        }
        return (Context<T>) intermediateData;
    }
}
