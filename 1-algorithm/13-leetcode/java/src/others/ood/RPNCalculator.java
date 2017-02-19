package others.ood;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 */
public class RPNCalculator {

    public static void main(String[] args) {
        String[] tokens = {"2", "3", "+", "1", "-", "4", "!", "+"};
        RPNCalculator calc = new RPNCalculator();
        calc.calculate(tokens);
    }

    public void calculate(String[] tokens) {
        Factory factory = new Factory();
        Stack<Token> stack = new Stack<>();
        for (String t : tokens) {
            factory.create(t).evaluate(stack);
        }
        System.out.println(((Operand) stack.peek()).getValue());
    }

}

class Factory {
    private Map<String, Operator> map = new HashMap<>();
    public Factory() {
        map.put("!", new Fact());
        map.put("+", new Add());
        map.put("-", new Sub());
        map.put("*", new Mul());
    }

    public Token create(String s) {
        if (map.containsKey(s)) return map.get(s);
        else return new IntOperand(s);
    }
}

interface Token {
    public void evaluate(Stack<Token> stack);
}

abstract class Operator implements Token {
    @Override
    public void evaluate(Stack<Token> stack) {}
}

class Add extends Operator {
    @Override
    public void evaluate(Stack<Token> stack) {
        Operand op1 = (Operand) stack.pop();
        Operand op2 = (Operand) stack.pop();
        stack.push(op1.add(op2));
    }
}

class Sub extends Operator {
    @Override
    public void evaluate(Stack<Token> stack) {
        Operand op1 = (Operand) stack.pop();
        Operand op2 = (Operand) stack.pop();
        stack.push(op1.sub(op2));
    }
}

class Mul extends Operator {
    @Override
    public void evaluate(Stack<Token> stack) {
        Operand op1 = (Operand) stack.pop();
        Operand op2 = (Operand) stack.pop();
        stack.push(op1.mul(op2));
    }
}

class Fact extends Operator {
    @Override
    public void evaluate(Stack<Token> stack) {
        Operand op = (Operand) stack.pop();
        stack.push(op.fact());
    }
}

abstract class Operand<T> implements Token {
    protected T val;

    public Operand(T val) {
        this.val = val;
    }

    public T getValue() {
        return val;
    }

    @Override
    public void evaluate(Stack<Token> stack) {
        stack.push(this);
    }

    public abstract Operand<T> fact();
    public abstract Operand<T> add(Operand<T> op2);
    public abstract Operand<T> sub(Operand<T> op2);
    public abstract Operand<T> mul(Operand<T> op2);
}

class IntOperand extends Operand<Integer> {

    public IntOperand(int val) {
        super(val);
    }

    public IntOperand(String val) {
        super(Integer.parseInt(val));
    }

    @Override
    public Operand<Integer> fact() {
        int fact = 1;
        for (int i = val; i >= 1; i--) {
            fact *= i;
        }
        return new IntOperand(fact);
    }

    @Override
    public Operand<Integer> add(Operand<Integer> op2) {
        return new IntOperand(val + op2.val);
    }

    @Override
    public Operand<Integer> sub(Operand<Integer> op2) {
        return new IntOperand(val - op2.val);
    }

    @Override
    public Operand<Integer> mul(Operand<Integer> op2) {
        return new IntOperand(val * op2.val);
    }
}
