package cn.edu.yzx.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.yzx.order.dao")
public class OrderApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(OrderApplication.class, args);
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

}
