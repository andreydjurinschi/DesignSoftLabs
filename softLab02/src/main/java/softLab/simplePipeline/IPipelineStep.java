package softLab.simplePipeline;

import softLab.simplePipeline.context.Context;

public interface IPipelineStep {
    void execute(Context context);
}
