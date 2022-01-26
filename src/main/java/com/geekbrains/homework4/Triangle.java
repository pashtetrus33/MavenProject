package com.geekbrains.homework4;

public class Triangle {

    public static double TriangleSquareCount(int a, int b, int c) throws WrongTriangleSidesException {
        if ((a == (b + c)) || (b == (a + c)) || (c == (a + b))) {
            System.out.println("Треугольник являестя вырожденным, его площадь = 0");
            return 0;
        }
        if ((a * a == (b * b + c * c)) || (b * b == (a * a + c * c)) || (c * c == (a * a + b * b)))
            System.out.print("Это прямоугольный треугольник. ");
        if ((a < (b + c)) && (b < (a + c)) && (c < (a + b))) {
            double semiperimetr = 0.5 * (a + b + c);
            double square = Math.sqrt(semiperimetr * (semiperimetr - a) * (semiperimetr - b) * (semiperimetr - c));
            System.out.println("Площадь треугольника равна = " + square);
            return square;
        } else {
            System.out.println("Треугольника с такими сторонами не существует!");
            throw new WrongTriangleSidesException("Треугольника с такими сторонами не существует!");
        }

    }
}




