package softLab.visitor;

import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.independet.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.NormalizeNameSurnameStep;

public class Visitor implements IVisitor {

    @Override
    public String visitAddUser(AddUserToFileStep step) {
        return "AddUserToFileStep visited\n";
    }

    @Override
    public String visitCheckIfUsernameIsNotNull(CheckIfUsernameIsNotNull step) {
        return "CheckIfUsernameIsNotNull visited\n";
    }

    @Override
    public String visitGenerateUsernameStep(GenerateUsernameStep step) {
        return "GenerateUsernameStep visited\n";
    }

    @Override
    public String visitNormalizeNameStep(NormalizeNameSurnameStep step) {
        return "NormalizeNameSurnameStep visited\n";
    }
}

