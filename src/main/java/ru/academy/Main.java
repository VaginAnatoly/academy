package ru.academy;

import ru.academy.annotation.AfterSuite;
import ru.academy.annotation.BeforeSuite;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException {

        MyClass myClass = new MyClass();

        TestRunner.runTests(myClass.getClass());
    }
}