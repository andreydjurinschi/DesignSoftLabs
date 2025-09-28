package softLab;

import softLab.pipline.Pipeline;
import softLab.pipline.context.Context;
import softLab.pipline.steps.ExtractNameAndSurname;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Context context = new Context();
        Pipeline mainPipeline = new Pipeline();
        mainPipeline.addStep(new ExtractNameAndSurname());
        mainPipeline.Execute(context);
    }
}