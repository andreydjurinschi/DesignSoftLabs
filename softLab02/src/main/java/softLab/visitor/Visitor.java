package softLab.visitor;

import softLab.genericPipeline.steps.dependet.PrintNewUsernamesStep;
import softLab.genericPipeline.steps.dependet.UsersWithoutUsernameStep;
import softLab.genericPipeline.steps.independet.InitDataStep;
import softLab.genericPipeline.steps.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.oneEntitySteps.NormalizeNameSurnameStep;

public class Visitor implements IVisitor {

    @Override
    public String visitInitDataStep(InitDataStep step, StringBuilder data) {

        return "\n_____________Init data step is called:_____________\n" +
                "\n"
                + data +
                "\n__________________________________________________\n";
    }

    @Override
    public String visitUsersWithoutUsernamesStep(UsersWithoutUsernameStep step, StringBuilder data) {
        return "\n_____________Finding users without usernames step is called:_____________\n" +
                "\n"
                + data +
                "\n________________________________________________________________________\n";
    }

    @Override
    public String visitGenerateUsernamesStep(softLab.genericPipeline.steps.dependet.GenerateUsernameStep step, StringBuilder data) {
        return "\n_____________Generate usernames  step is called:_____________\n" +
                "\n"
                + data +
                "\n_____________________________________________________________\n";
    }

    @Override
    public String visitPrintNewUsernamesStep(PrintNewUsernamesStep step, StringBuilder data) {
        return "\n_____________Print usernames step is called:_____________\n" +
                "\n"
                + data +
                "\n________________________________________________________\n";
    }

    @Override
    public String visitAddUser(AddUserToFileStep step, StringBuilder data) {
        return "\n_____________Add user step is called:_____________\n" +
                "\n"
                + data +
                "\n__________________________________________________\n";
    }

    @Override
    public String visitCheckIfUsernameIsNotNull(CheckIfUsernameIsNotNull step, StringBuilder data) {
        return "\n_____________Check username step called:_____________\n" +
                "\n"
                + data +
                "\n__________________________________________________\n";
    }

    @Override
    public String visitGenerateUsernameStep(GenerateUsernameStep step, StringBuilder data) {
        return "\n_____________Generate username step called:_____________\n" +
                "\n"
                + data +
                "\n_______________________________________________________\n";
    }

    @Override
    public String visitNormalizeNameStep(NormalizeNameSurnameStep step, StringBuilder data) {
        return "\n_____________Normalize name step called:_____________\n" +
                "\n"
                + data +
                "\n__________________________________________________\n";
    }

}

