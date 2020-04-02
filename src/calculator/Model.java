package calculator;

public class Model {

    public float Calculate(long numb1, long numb2, String operator) {

        switch(operator) {
            case "+": return numb1 + numb2;
            case "-": return numb1 - numb2;
            case "*": return numb1 * numb2;
            case "/":
                if (numb2 != 0)
                    return numb1 / numb2;
                else return 0;
            default: return 0;
        }
    }
}
