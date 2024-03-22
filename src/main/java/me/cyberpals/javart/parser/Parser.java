package me.cyberpals.javart.parser;

import me.cyberpals.javart.parser.arguments.ITokenArgument;
import me.cyberpals.javart.parser.arguments.elements.ParserElements;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private Map<String, ITokenArgument> arguments;
    private ParserElements args;

    public Parser(String[] args) {
        this.args = new ParserElements(args);
        this.arguments = new HashMap<>();
    }

    public void addArgument(String name, ITokenArgument arg) {
        arguments.put(name, arg);
    }

    public void parse() {
        while (args.hasNext()) {
            String arg = args.peek();
            if (arguments.containsKey(arg)) {
                arguments.get(arg).execute(args);
            } else {
                args.next();
            }
        }
    }

    public int size() {
        return args.size();
    }

    public ITokenArgument getArgument(String name) {
        return arguments.get(name);
    }
}
