package me.cyberpals.javart.parser.arguments;

import me.cyberpals.javart.parser.arguments.elements.ParserElements;

public abstract class PairArgument<T> implements ITokenArgument<T> {

    T value;

    public PairArgument(T defaultValue) {
        this.value = defaultValue;
    }

    @Override
    public T getValue() {
        return value;
    }

    public abstract T interpret(String value);


    @Override
    public void execute(ParserElements elements) {
        if (!elements.isEnd()) {
            elements.next();
            parse(getValue());
        } else if (elements.next().startsWith("-")) {
            parse(getValue());
        } else {
            parse(interpret(elements.next()));
        }

    }
}
