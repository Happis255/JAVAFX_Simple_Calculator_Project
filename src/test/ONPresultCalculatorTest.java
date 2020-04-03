package test;

import calculator.java_files.ONPCalculator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ONPresultCalculatorTest {

    @ParameterizedTest(name = "For given equation = {0} should return value = {1}")
    @CsvSource({
            "1+1= , 2.0",
            "3+3+2= , 8.0",
            "8.0+3+6= , 17.0",
            "36+36+6+36+(-3)= , 111.0",
            "36+36= , 72.0",
            "36.055+12+23.3+.5= , 71.854996"
    })
    void tester_adding_ONPcalculate(String equation, String expected) throws Exception {
        /** When **/
        String onp_ver = ONPCalculator.ONP_translate(equation);
        String result = ONPCalculator.ONP_calculate(onp_ver);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_calculate(onp_ver)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return value = {1}")
    @CsvSource({
            "1-1=, 0.0",
            "3-3-2=, -2.0",
            "8.0-3+6=, 11.0",
            "36-36+6+36+(-3)=, 39.0",
            "36-36=, 0.0",
            "36.055-12-23.3-.5=, 0.25500107"
    })
    void tester_subbing_ONcalculate(String equation, String expected) throws Exception {
        /** When **/
        String onp_ver = ONPCalculator.ONP_translate(equation);
        String result = ONPCalculator.ONP_calculate(onp_ver);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_calculate(onp_ver)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return value = {1}")
    @CsvSource({
            "1*1=, 1.0",
            "1*0=, 0.0",
            "3*3*2=, 18.0",
            "8.0*3+6=, 30.0",
            "36*36+6*36*(-3)=, 648.0",
            "36*36=, 1296.0",
            "36.055*12-23.3-.5=, 408.86002"
    })
    void tester_multing_ONPtranslator(String equation, String expected) throws Exception {
        /** When **/
        String onp_ver = ONPCalculator.ONP_translate(equation);
        String result = ONPCalculator.ONP_calculate(onp_ver);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_calculate(onp_ver)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return value = {1}")
    @CsvSource({
            "1/1=, 1.0",
            "3/3*2=, 2.0",
            "8.0*3/6=, 4.0",
            "36/36/6*36*(-3)=, -18.0",
            "36.055/12-23.3-.5=, -20.795416",
    })
    void tester_diving_ONPcalculate(String equation, String expected) throws Exception {
        /** When **/
        String onp_ver = ONPCalculator.ONP_translate(equation);
        String result = ONPCalculator.ONP_calculate(onp_ver);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_calculate(onp_ver)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return value = {1}")
    @CsvSource({
            "1^1=, 1.0",
            "1^0=, 1.0",
            "3/3^2=, 0.33333334",
            "8.0^3/6=, 85.333336",
            "36/36^6^36*(-3)=, 0.0",
            "36.055^12-23.3^.5=, 4.8259858E18",
    })
    void tester_power_ONPcalculate(String equation, String expected) throws Exception {
        /** When **/
        String onp_ver = ONPCalculator.ONP_translate(equation);
        String result = ONPCalculator.ONP_calculate(onp_ver);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_calculate(onp_ver)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return value = {1}")
    @CsvSource({
            "1%1=, 0.0",
            "1%0=, NaN",
            "3/3%2=, 1.0",
            "8.0%3/6=, 0.33333334",
            "36.055^12%23.3%.5=, 0.20318222",
    })
    void tester_modulo_ONPcalculate(String equation, String expected) throws Exception {
        /** When **/
        String onp_ver = ONPCalculator.ONP_translate(equation);
        String result = ONPCalculator.ONP_calculate(onp_ver);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_calculate(onp_ver)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should trow Exception SYNTAX ERROR!")
    @CsvSource({
            "1/0=",
            "1/0=",
            "3/0*0=",
            "0.0*3/0=",
            "36/0/6*36*(-3)=",
            "36/3/0=",
            "36.055/0-23.3-.0=",
    })
    void tester_zeroDiv_ONcalculateException(String equation) throws Exception {
        /** When **/
        String onp_ver = ONPCalculator.ONP_translate(equation);

        /** expected **/
        Assertions.assertThrows(Exception.class, () -> {
            ONPCalculator.ONP_calculate(onp_ver);
        });
    }
}
