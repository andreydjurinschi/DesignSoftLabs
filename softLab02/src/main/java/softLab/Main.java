package softLab;

import softLab.genericPipeline.Pipeline;
import softLab.genericPipeline.PipelineFactory;
import softLab.genericPipeline.context.Context;
import softLab.genericPipeline.testSteps.CreateFullNameStep;
import softLab.genericPipeline.testSteps.InitDataStep;
import softLab.genericPipeline.testSteps.PrintFullNames;

import java.io.IOException;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        PipelineFactory.GenerateEmailsPipeline();
    }
}