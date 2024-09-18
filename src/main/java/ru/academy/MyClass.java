package ru.academy;

import ru.academy.annotation.AfterSuite;
import ru.academy.annotation.BeforeSuite;
import ru.academy.annotation.Test;

public class MyClass {
    public int goal;
    public String message = "default message";

    @BeforeSuite
    static void printStartMessaage(){
        System.out.println("Hello academy!!!");
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

        @Test()
    public void printMessageTest5_1() {
            System.out.println("Test priority: 5");
    }
        @Test()
    public void printMessageTest5_2() {
            System.out.println("Test priority: 5");
    }


        @Test(priority = 9)
    public void printMessageTest9() {

        System.out.println("Test priority: 9");
    }
    @Test(priority = 1)
    public void printMessageTest1() {

        System.out.println("Test priority: 1");
    }

    @Test(priority = 4)
    public void printMessageTest4() {

        System.out.println("Test priority: 4");
    }

    @Test(priority = 2)
    public void printMessageTest2() {

        System.out.println("Test priority: 2");
    }

    @AfterSuite
    static void printFinalMessaage(){
        System.out.println("God by academy!!!");
    }
}
