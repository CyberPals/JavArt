package me.cyberpals.javart.parser.arguments;

import me.cyberpals.javart.parser.arguments.elements.ParserElements;

public abstract class SingleArgument implements ITokenArgument<Boolean> {

    Boolean value;

    public SingleArgument() {
        this.value = false;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void execute(ParserElements elements) {
        parse(true);
        elements.next();
    }
}
