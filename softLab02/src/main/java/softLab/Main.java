package softLab;

import softLab.simplePipline.context.Context;
import softLab.simplePipline.PipelineFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Context context = new Context();
        PipelineFactory.generateUsernames(context);
        System.out.println("__________________________________");
        System.out.println();
        PipelineFactory.generateReport(context);
    }
}