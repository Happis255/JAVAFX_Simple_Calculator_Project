package test;

import calculator.java_files.ONPCalculator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ONPtranslatorCalculatorTest {

    @ParameterizedTest(name = "For given equation = {0} should return ONP version = {1}")
    @CsvSource({
            "1+1=, <1><1>+=",
            "3+3+2=, <3><3>+<2>+=",
            "8.0+3+6=, <8.0><3>+<6>+=",
            "36+36+6+36+(-3)=, <36><36>+<6>+<36>+<3>~+=",
            "36++36=, <36>+<36>+=",
            "36.055+12+23.3+.5=, <36.055><12>+<23.3>+<.5>+="
    })
    void tester_adding_ONPtranslator(String equation, String expected) throws Exception {
        /** When **/
        String result = ONPCalculator.ONP_translate(equation);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_translate(equation)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return ONP version = {1}")
    @CsvSource({
            "1-1=, <1><1>-=",
            "3-3-2=, <3><3>-<2>-=",
            "8.0-3+6=, <8.0><3>-<6>+=",
            "36-36+6+36+(-3)=, <36><36>-<6>+<36>+<3>~+=",
            "36-+36=, <36>-<36>+=",
            "36.055-12-23.3-.5=, <36.055><12>-<23.3>-<.5>-="
    })
    void tester_subbing_ONPtranslator(String equation, String expected) throws Exception {
        /** When **/
        String result = ONPCalculator.ONP_translate(equation);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_translate(equation)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return ONP version = {1}")
    @CsvSource({
            "1*1=, <1><1>*=",
            "1*0=, <1><0>*=",
            "3*3*2=, <3><3>*<2>*=",
            "8.0*3+6=, <8.0><3>*<6>+=",
            "36*36+6*36*(-3)=, <36><36>*<6><36>*<3>~*+=",
            "36-+36=, <36>-<36>+=",
            "36.055*12-23.3-.5=, <36.055><12>*<23.3>-<.5>-="
    })
    void tester_multing_ONPtranslator(String equation, String expected) throws Exception {
        /** When **/
        String result = ONPCalculator.ONP_translate(equation);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_translate(equation)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return ONP version = {1}")
    @CsvSource({
            "1/1=, <1><1>/=",
            "1/0=, <1><0>/=",
            "3/3*2=, <3><3>/<2>*=",
            "8.0*3/6=, <8.0><3>*<6>/=",
            "36/36/6*36*(-3)=, <36><36>/<6>/<36>*<3>~*=",
            "36/+36=, <36>/<36>+=",
            "36.055/12-23.3-.5=, <36.055><12>/<23.3>-<.5>-=",
    })
    void tester_diving_ONPtranslator(String equation, String expected) throws Exception {
        /** When **/
        String result = ONPCalculator.ONP_translate(equation);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_translate(equation)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return ONP version = {1}")
    @CsvSource({
            "1^1=, <1><1>^=",
            "1^0=, <1><0>^=",
            "3/3^2=, <3><3><2>^/=",
            "8.0^3/6=, <8.0><3>^<6>/=",
            "36/36^6^36*(-3)=, <36><36><6>^<36>^/<3>~*=",
            "36^^36=, <36>^<36>^=",
            "36.055^12-23.3^.5=, <36.055><12>^<23.3><.5>^-=",
    })
    void tester_power_ONPtranslator(String equation, String expected) throws Exception {
        /** When **/
        String result = ONPCalculator.ONP_translate(equation);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_translate(equation)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should return ONP version = {1}")
    @CsvSource({
            "1%1=, <1><1>%=",
            "1%0=, <1><0>%=",
            "3/3%2=, <3><3>/<2>%=",
            "8.0%3/6=, <8.0><3>%<6>/=",
            "36%36^6^36*(-3)=, <36><36><6>^<36>^%<3>~*=",
            "36%^36=, <36><36>^%=",
            "36.055^12%23.3%.5=, <36.055><12>^<23.3>%<.5>%=",
    })
    void tester_modulo_ONPtranslator(String equation, String expected) throws Exception {
        /** When **/
        String result = ONPCalculator.ONP_translate(equation);

        /** expected **/
        assertEquals(expected, result);
        Assertions.assertDoesNotThrow(
                ()->ONPCalculator.ONP_translate(equation)
        );
    }

    @ParameterizedTest(name = "For given equation = {0} should trow Exception SYNTAX ERROR!")
    @CsvSource({
            "1/1",
            "1/0",
            "3/3*2",
            "8.0*3/6",
            "36/36/6*36*(-3)",
            "36/+36",
            "36.055/12-23.3-.5",
    })
    void tester_noEqual_ONPtranslatorException(String equation) throws Exception {
        /** expected **/
        Assertions.assertThrows(Exception.class, () -> {
            ONPCalculator.ONP_translate(equation);
        });
    }
}
