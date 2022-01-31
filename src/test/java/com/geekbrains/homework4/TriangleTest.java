package com.geekbrains.homework4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.geekbrains.homework4.Triangle.TriangleSquareCount;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger("TriangleTest.class");
    @BeforeAll
    static void beforeAll() {
        //System.out.println("Выполнимся 1 раз перед всеми тестами, например загрузка данных в базу!");
        //TRACE, DEBUG, INFO, ERROR
        logger.info("log data");
        logger.trace("trace log data");
        logger.error("err log data");
    }

    @BeforeEach
    void beforeEach() {
        //System.out.println("Выполняемся перед каждым тестом");
    }

    @Test
    @DisplayName("Позитивная проверка метода подсчета площади прямоугольного треугольника")
    void givenCorrectSquareResultWhenRunTriangleSquareCountMethod() throws WrongTriangleSidesException {
        double result = TriangleSquareCount(3,4,5);
        assertEquals(6,result);
    }

    @Test
    @DisplayName("Позитивная проверка метода подсчета площади вырожденного треугольника")
    void givenCorrectSquareResultWhenRunTriangleSquareCountMethodForUniqueCase() throws WrongTriangleSidesException {
        double result = TriangleSquareCount(2,3,5);
        assertEquals(0,result);

    }

    @ParameterizedTest
    @DisplayName("Позитивная проверка метода подсчета площади произвольного треугольника")
    @CsvSource({"7, 4, 5, 9.797958","8, 9, 10, 34.197039","6, 8, 12, 21.330729"})
    void givenPositiveTriangleWhenRunTriangleSquareCountTest(int a, int b, int c, double result) throws WrongTriangleSidesException {
        assertEquals(result, TriangleSquareCount(a,b,c), 0.000001);
    }

    @Test
    @DisplayName("Проверка выброса исключения при ошибочном вводе длинны сторон треугольника")
    void exceptionWhenTryWrongSides(){
        assertThrows(WrongTriangleSidesException.class, () -> TriangleSquareCount(6,4,50));
    }

    @Test
    @DisplayName("Позитивная проверка c помощью библиотеки AssertJ")
    void AssertJTest () throws WrongTriangleSidesException {
        assertThat(TriangleSquareCount(7,3,9)).isEqualTo(8.78564169540279);
    }
    @Test
    @DisplayName("Позитивная проверка c помощью библиотеки AssertJ-2")
    void AssertJTest2 () throws WrongTriangleSidesException {
        assertThat(TriangleSquareCount(5,6,9)).isGreaterThan(14).isLessThan(15);
    }


}


