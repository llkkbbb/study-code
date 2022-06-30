package com.example.blogcode.polymorphism;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.study.abstract1.다형성
 * fileName       : Manager
 * author         : tkdwk567@naver.com
 * date           : 2022/06/30
 */
public class Manager {
    private List<Employee> employees = new ArrayList<>();

    public void orderToDoJop() {
        for (Employee employee : employees) {
            if (employee != null) {
                employee.work();
                employee.wellCome();
            }
        }
    }

    public void addEmployee(Employee employee) {
        if (employee != null) {
            employees.add(employee);
        }
    }

}
