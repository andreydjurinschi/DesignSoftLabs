package softLab.simplePipline;

import softLab.simplePipline.context.Context;

public interface IPipelineStep {
    void execute(Context context);
}
