package calc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Calculatrice {

    abstract static class ArithOperator {
        Queue<Integer> operands = new LinkedList<Integer>();

        abstract Integer exec();

        ArithOperator(List<Integer> p) {
            operands.addAll(p);
        }
    }

    static class PlusOperator extends ArithOperator {

        PlusOperator(List<Integer> p) {
            super(p);
        }

        @Override
        Integer exec() {
            Integer result = 0;
            while (!operands.isEmpty()) {
                result += operands.remove();
            }
            return result;
        }
    }

    static class MoinsOperator extends ArithOperator {

        MoinsOperator(List<Integer> p) {
            super(p);
        }

        @Override
        Integer exec() {
            Integer result = 0;
            if (!operands.isEmpty())
                result = operands.remove();
            while (!operands.isEmpty()) {
                result -= operands.remove();
            }
            return result;
        }
    }

    static class MultOperator extends ArithOperator {

        MultOperator(List<Integer> p) {
            super(p);
        }

        @Override
        Integer exec() {
            Integer result = 1;
            while (!operands.isEmpty()) {
                result *= operands.remove();
            }
            return result;
        }
    }

    static class DivOperator extends ArithOperator {

        DivOperator(List<Integer> p) {
            super(p);
        }

        @Override
        Integer exec() {
            Integer result = 0;
            if (!operands.isEmpty())
                result = operands.remove();
            while (!operands.isEmpty()) {
                result /= operands.remove();
            }
            return result;
        }
    }

    static class MaxOperator extends ArithOperator {

        MaxOperator(List<Integer> p) {
            super(p);
        }

        @Override
        Integer exec() {
            Integer result = 0;
            if (!operands.isEmpty())
                result = operands.remove();
            while (!operands.isEmpty()) {
                int op = operands.remove();
                result = result > op ? result : op;
            }
            return result;
        }
    }
    static class MinOperator extends ArithOperator {

        MinOperator(List<Integer> p) {
            super(p);
        }

        @Override
        Integer exec() {
            Integer result = 0;
            if (!operands.isEmpty())
                result = operands.remove();
            while (!operands.isEmpty()) {
                int op = operands.remove();
                result = result > op ? op : result;
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
        List<Integer> operands = new ArrayList<Integer>();

        for (int ii = 1; ii < args.length; ii++)
            try {
                operands.add(Integer.parseInt(args[ii]));
            } catch (NumberFormatException e) {

            }

        if ("a".equalsIgnoreCase(op)) {
            arithOperator = new PlusOperator(operands);
        } else if ("s".equalsIgnoreCase(op)) {
            arithOperator = new MoinsOperator(operands);
        } else if ("d".equalsIgnoreCase(op)) {
            arithOperator = new DivOperator(operands);
        } else if ("m".equalsIgnoreCase(op)) {
            arithOperator = new MultOperator(operands);
        } else if ("max".equalsIgnoreCase(op)) {
            arithOperator = new MaxOperator(operands);
        } else if ("min".equalsIgnoreCase(op)) {
            arithOperator = new MinOperator(operands);
        }

        return arithOperator;
    }

    static void usage() {
        System.out.println("Calculatrice operateur valeur1 ... valeurn\\n operateur possible = +-/*");
    }
}
