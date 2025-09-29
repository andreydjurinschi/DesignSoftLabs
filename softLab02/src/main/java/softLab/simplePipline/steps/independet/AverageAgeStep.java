package softLab.simplePipline.steps.independet;

import softLab.simplePipline.Context;
import softLab.simplePipline.IPipelineStep;

/**
 * ------------------------------------------------
 * Independent step
 * ------------------------------------------------
 * Step that shows the average year of birth
 */

public class AverageAgeStep implements IPipelineStep {
    @Override
    public void execute(Context context) {
        int avg = (int) context.getAllUsers()
                .stream()
                .mapToInt(u -> u.getDateOfBirth().getYear())
                .average().getAsDouble();
        System.out.println("Average year of birth: " + avg);
    }
}
