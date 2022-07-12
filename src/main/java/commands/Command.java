package commands;

import model.Result;

@FunctionalInterface
public interface Command {
    Result execute();
}
