package me.cyberpals.javart.parser.arguments;

import me.cyberpals.javart.parser.arguments.elements.ParserElements;

public abstract class PairArgument<T> implements ITokenArgument {

    private T value;

    public PairArgument(T defaultValue) {
        this.value = defaultValue;
    }

    public abstract T interpret(String value);


    @Override
    public void execute(ParserElements elements) {
        if (!elements.isEnd()) {
            elements.next();
            parse(value);
        } else if (elements.next().startsWith("-")) {
            parse(value);
        } else {
            parse(interpret(elements.peek()));
            elements.next();
        }

    }

    public abstract void parse(T value);
}
