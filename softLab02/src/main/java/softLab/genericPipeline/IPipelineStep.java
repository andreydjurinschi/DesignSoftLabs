package softLab.genericPipeline;

public interface IPipelineStep<TContext, TOut> {
    TOut Execute(TContext data);
}
