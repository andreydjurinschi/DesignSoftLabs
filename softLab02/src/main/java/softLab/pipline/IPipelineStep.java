package softLab.pipline;

/**
 * pipeline step interface
 */
public interface IPipelineStep<TContext> {
    Object Execute(TContext context);
}
