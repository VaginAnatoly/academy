package ru.academy;

import java.lang.reflect.Method;

public class MethodPriority implements Comparable<MethodPriority> {
    private Integer number;
    private Method method;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }


    @Override
    public int compareTo(MethodPriority o) {

        int comparenumber = o.getNumber();
        //Прямой порядок сортировки
        //return this.number - comparenumber;

        //Обратный порядок сортировки
        return comparenumber-this.number;

    }
}
