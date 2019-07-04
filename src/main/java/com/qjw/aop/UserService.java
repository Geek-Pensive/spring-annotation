package com.qjw.aop;

import com.qjw.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : qjw
 * @data : 2019/6/18
 */

@Service
public class UserService {

    @Autowired
    private Car car;

    public int div(int a, int b) {
        try {
            return a / b;
        }catch (Exception e){
            throw new RuntimeException("分母为0");
        }

    }

    public int add(int a, int b) {
        System.out.println("计算a+b");
        return a + b;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "car=" + car +
                '}';
    }
}
