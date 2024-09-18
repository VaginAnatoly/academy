package ru.academy;

import ru.academy.annotation.AfterSuite;
import ru.academy.annotation.BeforeSuite;
import ru.academy.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class TestRunner {

    public static void runTests(Class c) throws InvocationTargetException, IllegalAccessException,InstantiationException, NoSuchMethodException {
        int countBeforeSuite = 0;
        int conutAfterSuite = 0;

        //Загружаем класс в память
        Object obj = c.getDeclaredConstructor().newInstance();

        //Пробегаем по методам находим BeforeSuite
        for (Method method : c.getDeclaredMethods()) {

            //Для аннотации BeforeSuite
            if (method.isAnnotationPresent(BeforeSuite.class)){
                System.out.println("Метод: " + method.getName());
                countBeforeSuite++;
                if (method.getModifiers() != Modifier.STATIC) {
                    System.out.println("Аннотация @BeforeSuite не может быть указана на методе " + method.getName() + " т.к он не являеться статическим");
                }
                if (countBeforeSuite > 1) {
                    System.out.println("Методов помеченных аннотацией BeforeSuite больше чем один");
                }
                method.invoke(obj);
            }
        }


        //Массив для сортировки методов
        ArrayList<MethodPriority> massiveMethodSort = new ArrayList<>();
        //Пробегаем по методам находим Test
        for (Method method : c.getDeclaredMethods()) {

            if (method.isAnnotationPresent(Test.class)){

                Test annotation = method.getAnnotation(Test.class);
                MethodPriority methodTest = new MethodPriority();
                methodTest.setNumber(annotation.priority());
                methodTest.setMethod(method);
                //Добавим в массив для дальнейшей сортировки и выводу по значению приоритета
                massiveMethodSort.add(methodTest);

            }
        }

            Collections.sort(massiveMethodSort);

            for (MethodPriority m : massiveMethodSort) {
                if (m != null) {
                    System.out.println("Метод: " + m.getMethod().getName());
                    m.getMethod().invoke(obj);
                }
            }

        //Пробегаем по методам находим AfterSuite
        for (Method method : c.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterSuite.class)){
                System.out.println("Метод: " + method.getName());
                conutAfterSuite++;
                if (method.getModifiers() != Modifier.STATIC) {
                    System.out.println(" Аннотация @AfterSuite не может быть указана на методе " + method.getName() + " т.к он не являеться статическим");
                }
                if (conutAfterSuite > 1) {
                    System.out.println("Методов помеченных аннотацией AfterSuite больше чем один");
                }
                method.invoke(obj);
            }
        }

    }
}
