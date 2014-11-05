package calc;
import java.util.Stack;

public class Calculatrice {

    abstract static class ArithOperator {
        Stack<Integer> operands = new Stack<Integer>();

        abstract Integer exec();

        ArithOperator(Stack<Integer> p) {
            operands.addAll(p);
        }
    }

    static class PlusOperator extends ArithOperator {

        PlusOperator(Stack<Integer> p) {
            super(p);
        }

        @Override
        Integer exec() {
            Integer result = 0;
            while (!operands.isEmpty()) {
                result += operands.pop();
            }
            return result;
        }
    }

    public Calculatrice() {
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length < 3) {
            usage();
            return;
        }

        ArithOperator calc = createOperator(args);
        long result = calc.exec();
        System.out.println("result :" + result);

    }

    static ArithOperator createOperator(String[] args) {

        ArithOperator arithOperator = null;
        String op = args[0];
        Stack<Integer> operands = new Stack<Integer>();

        for (int ii = 1; ii < args.length; ii++)
            try {
                operands.push(Integer.parseInt(args[ii]));
            } catch (NumberFormatException e) {

            }

        if ("+".equals(op)) {
            arithOperator = new PlusOperator(operands);
        } else if ("-".equals(op)) {
            // TODO
        } else if ("/".equals(op)) {
            // TODO
        } else if ("*".equals(op)) {
            // TODO
        }

        return arithOperator;
    }

    static void usage() {
        System.out.println("Calculatrice operateur valeur1 ... valeurn\\n operateur possible = +-/*");
    }
}
