package calculator.java_files;


public class Calculator {

    private static class ONP {

        private enum PRIORITY{
            VARIABLE,
            CONST_VARIABLE,
            DOT_COMMA,
            PRIORITY_0,
            PRIORITY_1,
            PRIORITY_2,
            PRIORITY_3
        }

        private static boolean compare_priority(PRIORITY pr1, PRIORITY pr2) throws Exception
        {
            boolean comp_result;

            switch (pr1)
            {
                case PRIORITY_0:
                    comp_result = false;
                    break;
                case PRIORITY_1:
                    comp_result = pr2 == PRIORITY.PRIORITY_0;
                    break;
                case PRIORITY_2:
                    switch (pr2)
                    {
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
                    switch (pr2)
                    {
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

        private static PRIORITY getPriority(String ch)
        {
            PRIORITY ch_priority;
            switch (ch)
            {
                case "(":
                    ch_priority = PRIORITY.PRIORITY_0;
                    break;
                case "+": case "-": case ")":
                ch_priority = PRIORITY.PRIORITY_1;
                break;
                case "*": case "/": case "%": case "~":
                ch_priority = PRIORITY.PRIORITY_2;
                break;
                case "^":
                    ch_priority = PRIORITY.PRIORITY_3;
                    break;
                case "0": case "1": case "2": case "3": case "4":
                case "5": case "6": case "7": case "8": case "9":
                ch_priority = PRIORITY.CONST_VARIABLE;
                break;
                case ".": case ",":
                ch_priority = PRIORITY.DOT_COMMA;
                break;
                default:
                    ch_priority = PRIORITY.VARIABLE;
                    break;
            }
            return ch_priority;
        }

        public static String translate(String quation) throws Exception
        {
            String out = "";
            Stack stack = new Stack();

            while(!quation.isEmpty())
            {
                String ch = quation.substring(0, 1);
                if(quation.length() > 1)
                    quation = quation.substring(1, quation.length());
                else
                    quation = "";
                PRIORITY ch_priority = getPriority(ch);

                switch (ch_priority)
                {
                    case CONST_VARIABLE:
                    case DOT_COMMA:
                        String str2 = ch;
                        if(!quation.isEmpty())
                        {
                            ch = quation.substring(0, 1);
                            while(getPriority(ch) == PRIORITY.CONST_VARIABLE || getPriority(ch) == PRIORITY.DOT_COMMA)
                            {
                                if(quation.length() > 1)
                                    quation = quation.substring(1, quation.length());
                                else
                                    quation = "";
                                str2 += ch;
                                if(quation.isEmpty())
                                    break;
                                ch = quation.substring(0, 1);
                            }
                        }
                        out += "[" + str2 + "]";
                        break;
                    case VARIABLE:
                        out += ch;
                        break;
                    case PRIORITY_0: // znak "("
                        stack.push(ch);
                        ch = quation.substring(0, 1);
                        if(ch.equals("-"))
                        {
                            stack.push("~");
                            quation = quation.substring(1, quation.length());
                        }
                        break;
                    case PRIORITY_1:
                    case PRIORITY_2:
                    case PRIORITY_3:
                        if(ch.equals(")"))
                        {
                            while(!stack.top().equals("("))
                            {
                                out += stack.top();
                                stack.pop();
                            }
                            stack.pop();
                            break;
                        }
                        if(out.isEmpty() && ch.equals("-"))
                        {
                            stack.push("~");
                            break;
                        }
                        while(!stack.isEmpty())
                        {
                            if(compare_priority(ch_priority, getPriority(stack.top())))
                            {
                                stack.push(ch);
                                break;
                            }
                            else
                            {
                                out += stack.top();
                                stack.pop();
                            }
                        }
                        if(stack.isEmpty())
                            stack.push(ch);
                        break;
                    default:
                        break;
                }
            }

            while(!stack.isEmpty())
            {
                out = out + stack.top();
                stack.pop();
            }
            return out;
        }
    }

    public static String ONP_translate(String quation) throws Exception
    {
        if(!quation.substring(quation.length()-1).equals("="))
            throw new Exception("Not a quation!");
        quation = quation.substring(0, quation.length()-1);

        return ONP.translate(quation) + "=";
    }

    public static String calculate(String quation) throws Exception
    {
        if(!quation.substring(quation.length()-1).equals("="))
            throw new Exception("Not a quation!");

        Stack stack = new Stack();
        float out ;
        float help_val;
        String help_str;

        String ch = quation.substring(0, 1);
        quation = quation.substring(1);


        while(!ch.equals("="))
        {
            help_str = "";
            switch (ch)
            {
                case "[":
                    ch = quation.substring(0, 1);
                    quation = quation.substring(1);
                    do
                    {
                        help_str += ch;
                        ch = quation.substring(0, 1);
                        quation = quation.substring(1);
                    }while(!ch.equals("]"));
                    stack.push(help_str);
                    break;
                case "+":
                    help_val = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) + help_val;
                    stack.push(""+out);
                    break;
                case "-":
                    help_val = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) - help_val;
                    stack.push("" + out);
                    break;
                case "/":
                    help_val = Float.parseFloat(stack.pop());
                    if(help_val == 0)
                        return new String("err, divide by 0");
                    out = Float.parseFloat(stack.pop()) / help_val;
                    stack.push("" + out);
                    break;
                case "*":
                    help_val = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) * help_val;
                    stack.push("" + out);
                    break;
                case "%":
                    help_val = Float.parseFloat(stack.pop());
                    out = Float.parseFloat(stack.pop()) % help_val;
                    stack.push("" + out);
                    break;
                case "^":
                    help_val = Float.parseFloat(stack.pop());
                    out = (float)Math.pow(Float.parseFloat(stack.pop()), help_val);
                    stack.push("" + out);
                    break;
                case "~":
                    out = -Float.parseFloat(stack.pop());
                    stack.push("" + out);
                    break;
                default:
                    throw new Exception("Unknow");
            }

            ch = quation.substring(0, 1);
            if(quation.length() > 1)
                quation = quation.substring(1);
            else
                quation = "";
        }


        return stack.top();
    }

}