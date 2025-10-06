package softLab.visitor;

import softLab.genericPipeline.steps.dependet.PrintNewUsernamesStep;
import softLab.genericPipeline.steps.dependet.UsersWithoutUsernameStep;
import softLab.genericPipeline.steps.independet.InitDataStep;
import softLab.genericPipeline.steps.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.oneEntitySteps.GenerateUsernameStep;

import softLab.genericPipeline.steps.oneEntitySteps.NormalizeNameSurnameStep;

/**
 * visitor interface
 */
public interface IVisitor {

    /*User gmail */
    String visitInitDataStep(InitDataStep step, StringBuilder data);
    String visitUsersWithoutUsernamesStep(UsersWithoutUsernameStep step, StringBuilder data);
    String visitGenerateUsernamesStep(
            softLab.genericPipeline.steps.dependet.GenerateUsernameStep step, StringBuilder data
    );
    String visitPrintNewUsernamesStep(PrintNewUsernamesStep step, StringBuilder data);


    /*User creating steps*/
    String visitAddUser(AddUserToFileStep step, StringBuilder data);
    String visitCheckIfUsernameIsNotNull(CheckIfUsernameIsNotNull step, StringBuilder data);
    String visitGenerateUsernameStep(GenerateUsernameStep step, StringBuilder data);
    String visitNormalizeNameStep(NormalizeNameSurnameStep  step, StringBuilder data);

}
