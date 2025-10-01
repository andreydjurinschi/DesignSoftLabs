package softLab.visitor;

import softLab.genericPipeline.steps.independet.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.independet.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.NormalizeNameSurnameStep;

/**
 * visitor interface
 */
public interface IVisitor {
    String visitAddUser(AddUserToFileStep step, StringBuilder data);
    String visitCheckIfUsernameIsNotNull(CheckIfUsernameIsNotNull step, StringBuilder data);
    String visitGenerateUsernameStep(GenerateUsernameStep step, StringBuilder data);
    String visitNormalizeNameStep(NormalizeNameSurnameStep  step, StringBuilder data);
}
