package calculator.java_files;

public class ONPCalculator {

    /* Types of priorities for ONP calculations */
    private enum PRIORITY {
        VARIABLE,
        CONST_VARIABLE,
        DOT_COMMA,
        PRIORITY_0,
        PRIORITY_1,
        PRIORITY_2,
        PRIORITY_3
    }

    /* Compares two symbols' priorities */
    private static boolean compare_priorities(PRIORITY val1, PRIORITY val2) {
        assert (val1 != null) : "val1 at compare_priorities() method cannot be null";
        assert (val2 != null) : "val2 at compare_priorities() method cannot be null";

        boolean comp_result;

        switch (val1) {

            case PRIORITY_0:
                comp_result = false;
                break;

            case PRIORITY_1:
                comp_result = val2 == PRIORITY.PRIORITY_0;
                break;

            case PRIORITY_2:
                switch (val2) {
                    case PRIORITY_0:
                    case PRIORITY_1:
                        comp_result = true;
                        break;
                    default:
                        comp_result = false;
                        break;
                }
                break;

            case PRIORITY_3:
                switch (val2) {
                    case PRIORITY_0:
                    case PRIORITY_1:
                    case PRIORITY_2:
                        comp_result = true;
                        break;
                    default:
                        comp_result = false;
                        break;
                }
                break;

            default:
                comp_result = true;
                break;
        }
        return comp_result;
    }

    /* Returns the priority of the symbol */
    private static PRIORITY getPriority(String symbol) {
        assert (symbol != null) : "symbol at getPriority() method cannot be null";
        assert (String.class.isInstance(symbol)) : "symbol at getPriority() method must be String";

        PRIORITY symbol_priority;
        switch (symbol) {

            case "(":
                symbol_priority = PRIORITY.PRIORITY_0;
                break;

            case "+":
            case "-":
            case ")":
                symbol_priority = PRIORITY.PRIORITY_1;
                break;

            case "*":
            case "/":
            case "%":
            case "~":
                symbol_priority = PRIORITY.PRIORITY_2;
                break;

            case "^":
                symbol_priority = PRIORITY.PRIORITY_3;
                break;

            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                symbol_priority = PRIORITY.CONST_VARIABLE;
                break;

            case ".":
            case ",":
                symbol_priority = PRIORITY.DOT_COMMA;
                break;

            //Number
            default:
                symbol_priority = PRIORITY.VARIABLE;
                break;
        }
        assert (symbol != null) : "getPriority() cannot return null";
        return symbol_priority;
    }

    /* Return translated ONP equation */
    static String translate_to_ONP(String quation) throws Exception {
        assert (quation != null) : "quation at translate_to_ONP() method cannot be null";
        assert (String.class.isInstance(quation)) : "quation at translate_to_ONP() method must be String";

        StringBuilder onp_quation = new StringBuilder();
        Stack stack = new Stack();

        while (!quation.isEmpty()) {
            String character = quation.substring(0, 1);

            if (quation.length() > 1)
                quation = quation.substring(1);
            else
                quation = "";

            PRIORITY ch_priority = getPriority(character);
            switch (ch_priority) {
                case CONST_VARIABLE:
                case DOT_COMMA:
                    StringBuilder next_character = new StringBuilder(character);
                    if (!quation.isEmpty()) {
                        character = quation.substring(0, 1);
                        while (getPriority(character) == PRIORITY.CONST_VARIABLE || getPriority(character) == PRIORITY.DOT_COMMA) {
                            if (quation.length() > 1)
                                quation = quation.substring(1);
                            else
                                quation = "";
                            next_character.append(character);
                            if (quation.isEmpty())
                                break;
                            character = quation.substring(0, 1);
                        }
                    }
                    onp_quation.append("<").append(next_character).append(">");
                    break;
                case VARIABLE:
                    onp_quation.append(character);
                    break;
                case PRIORITY_0:
                    stack.push(character);
                    character = quation.substring(0, 1);
                    if (character.equals("-")) {
                        stack.push("~");
                        quation = quation.substring(1);
                    }
                    break;
                case PRIORITY_1:
                case PRIORITY_2:
                case PRIORITY_3:
                    if (character.equals(")")) {
                        while (!stack.top().equals("(")) {
                            onp_quation.append(stack.top());
                            stack.pop();
                        }
                        stack.pop();
                        break;
                    }
                    if ((onp_quation.length() == 0) && character.equals("-")) {
                        stack.push("~");
                        break;
                    }
                    while (!stack.isEmpty()) {
                        if (compare_priorities(ch_priority, getPriority(stack.top()))) {
                            stack.push(character);
                            break;
                        } else {
                            onp_quation.append(stack.top());
                            stack.pop();
                        }
                    }
                    if (stack.isEmpty())
                        stack.push(character);
                    break;
                default:
                    break;
            }
        }

        while (!stack.isEmpty()) {
            onp_quation.append(stack.top());
            stack.pop();
        }

        assert (onp_quation.toString() != null) : "translate_to_ONP() cannot return null";
        return onp_quation.toString();
    }

    /* Translates the String text into ONP equation */
    public static String ONP_translate(String equation) throws Exception {
        assert (equation != null) : "equation at ONP_translate() method cannot be null";
        assert (!equation.equals("=")) : "equation at ONP_translate() method cannot be \"=\"";
        assert (equation.length() < 0) : "equation at ONP_translate() .length() error";

        /* Chech if there is equal sign */
        if (!equation.substring(equation.length() - 1).equals("="))
            throw new Exception("There is no equal sign at the end - SYNTAX ERROR!");

        /* Remove equal sign */
        equation = equation.substring(0, equation.length() - 1);

        /* Return translated ONP equation */
        return translate_to_ONP(equation) + "=";
    }

    /* Returns the String value of ONP equation */
    public static String ONP_calculate(String equation) throws Exception {
        assert (equation != null) : "equation at ONP_calculate() method cannot be null";
        assert (!equation.equals("=")) : "equation at ONP_calculate() method cannot be \"=\"";
        assert (equation.length() < 0) : "equation at ONP_calculate() .length() error";

        /* Chech if there is equal sign */
        if (!equation.substring(equation.length() - 1).equals("="))
            throw new Exception("There is no equal sign at the end - SYNTAX ERROR!");

        //On the top of stack will be the result of ONP
        Stack stack = new Stack();
        float out;
        float temp;
        StringBuilder tempString;

        //First character in equation
        String character = equation.substring(0, 1);

        //Rest of equation
        equation = equation.substring(1);

        while (!character.equals("=")) {
            tempString = new StringBuilder();
            switch (character) {
                case "<":
                    character = equation.substring(0, 1);
                    equation = equation.substring(1);
                    do {
                        tempString.append(character);
                        character = equation.substring(0, 1);
                        equation = equation.substring(1);
                    } while (!character.equals(">"));
                    stack.push(tempString.toString());
                    break;
                case "+":
                    temp = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) + temp;
                    stack.push("" + out);
                    break;
                case "-":
                    temp = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) - temp;
                    stack.push("" + out);
                    break;
                case "/":
                    temp = Float.parseFloat(stack.pop());
                    if (temp == 0)
                        throw new Exception("Div by 0");
                    out = Float.parseFloat(stack.pop()) / temp;
                    stack.push("" + out);
                    break;
                case "*":
                    temp = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) * temp;
                    stack.push("" + out);
                    break;
                case "%":
                    temp = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) % temp;
                    stack.push("" + out);
                    break;
                case "^":
                    temp = Float.parseFloat(stack.pop());
                    out = (float) Math.pow(Float.parseFloat(stack.pop()), temp);
                    stack.push("" + out);
                    break;
                case "~":
                    out = -Float.parseFloat(stack.pop());
                    stack.push("" + out);
                    break;
                default:
                    throw new Exception("Unknow symbol - SYNTAX ERROR!");
            }
            character = equation.substring(0, 1);
            if (equation.length() > 1)
                equation = equation.substring(1);
            else
                equation = "";
        }
        if (stack.top().equals("-0.0")) return "0.0";
            else return stack.top();
    }
}