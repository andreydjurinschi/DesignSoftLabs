package softLab.visitor;

import softLab.genericPipeline.steps.independet.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.independet.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.NormalizeNameSurnameStep;

/**
 * visitor interface
 */
public interface IVisitor {
    String visitAddUser(AddUserToFileStep step);
    String visitCheckIfUsernameIsNotNull(CheckIfUsernameIsNotNull step);
    String visitGenerateUsernameStep(GenerateUsernameStep step);
    String visitNormalizeNameStep(NormalizeNameSurnameStep  step);
}
