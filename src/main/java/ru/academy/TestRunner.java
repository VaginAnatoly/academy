package ru.academy;

import ru.academy.annotation.AfterSuite;
import ru.academy.annotation.BeforeSuite;
import ru.academy.annotation.Test;
import ru.academy.exceptions.AnnotationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class TestRunner {

    public static void runTests(Class c) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, AnnotationException {
        int countBeforeSuite = 0;
        int countAfterSuite = 0;

        //Загружаем класс в память
        Object obj = c.getDeclaredConstructor().newInstance();

        //Пробегаем по методам находим BeforeSuite
        for (Method method : c.getDeclaredMethods()) {

            //Для аннотации BeforeSuite
            if (method.isAnnotationPresent(BeforeSuite.class)){
                System.out.println("Метод: " + method.getName());
                countBeforeSuite++;

                checkedAnnotation(method, countBeforeSuite, "BeforeSuite");

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
                countAfterSuite++;
               try {
                   checkedAnnotation(method, countAfterSuite, "AfterSuite");
               } catch (AnnotationException e) {
                   System.out.println(e.getMessage());
               }


                method.invoke(obj);
            }
        }

    }

    private static void checkedAnnotation(Method method, int countBeforeSuite, String annotation) throws AnnotationException {
        if (method.getModifiers() != Modifier.STATIC) {
            throw new AnnotationException("Аннотация " + annotation + " не может быть указана на методе " + method.getName() + " т.к он не являеться статическим");
        }
        if (countBeforeSuite > 1) {
            throw new AnnotationException("Методов помеченных аннотацией " + annotation + " больше чем один");

        }
    }
}
