package commands;

import model.Parameter;
import model.Result;
import services.BruteForceService;

public class BruteForceCommand implements Command {
    private Parameter parameter;
    private BruteForceService bruteForceService;

    public BruteForceCommand(Parameter parameter) {
        this.parameter = parameter;
        this.bruteForceService = new BruteForceService(parameter.getInput());
    }

    @Override
    public Result execute() {
        Result returnValue = new Result("The result key is found");
        int key = bruteForceService.analyzeByBruteForce();
        returnValue.setSecurityKey(key);
        return returnValue;
    }
}
