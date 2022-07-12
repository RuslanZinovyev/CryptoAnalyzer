package commands;

import model.Parameter;
import model.Result;
import services.DecryptionService;
import services.ReadService;
import services.WriteService;

public class DecryptorCommand implements Command {
    private Parameter parameter;
    private ReadService readService;
    private DecryptionService decryptionService;
    private WriteService writeService;

    public DecryptorCommand(Parameter parameter) {
        this.parameter = parameter;
        readService = ReadService.getInstance(parameter.getInput());
        decryptionService = DecryptionService.getInstance(parameter.getSecurityKey(), readService.readFile());
        writeService = WriteService.getInstance(parameter.getOutput(), decryptionService.decryptText());
    }

    @Override
    public Result execute() {
        writeService.writeFile();
        return new Result("The file was decrypted and saved in the following path: " + parameter.getOutput().toPath());
    }
}
