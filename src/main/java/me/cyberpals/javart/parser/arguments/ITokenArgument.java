package me.cyberpals.javart.parser.arguments;

import me.cyberpals.javart.parser.arguments.elements.ParserElements;

public interface ITokenArgument<T> {
    public T getValue();

    public void parse(T value);

    public void execute(ParserElements elements);
}
