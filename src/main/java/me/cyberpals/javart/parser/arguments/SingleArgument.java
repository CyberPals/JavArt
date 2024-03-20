package me.cyberpals.javart.parser.arguments;

import me.cyberpals.javart.parser.arguments.elements.ParserElements;

public abstract class SingleArgument implements ITokenArgument {

    public SingleArgument() {
    }

    @Override
    public void execute(ParserElements elements) {
        parse();
        elements.next();
    }

    public abstract void parse();
}
