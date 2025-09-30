package softLab.genericPipeline;

import softLab.genericPipeline.context.Context;

public interface IPipelineStep<I, O> {
    Context<O> Execute(Context<I> context);
}
