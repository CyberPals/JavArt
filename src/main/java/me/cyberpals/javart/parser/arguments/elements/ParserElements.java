package me.cyberpals.javart.parser.arguments.elements;

public class ParserElements {
    int index;
    String[] args;

    public ParserElements(String[] args) {
        this.args = args;
        index = 0;
    }

    public boolean hasNext() {
        return index < args.length;
    }

    public boolean isEnd() {
        return index < args.length - 1;
    }

    public String next() {
        index++;
        return hasNext() ? args[index] : null;
    }

    public String peek() {
        return args[index];
    }

    public int size() {
        return args.length;
    }
}
