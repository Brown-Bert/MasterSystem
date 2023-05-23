package cn.edu.yzx.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApplicationTests {

    @Test
    void contextLoads() {
        int[] map = {1, 2, 3};
        change(map);
        System.out.println(map[1]);

    }
    public void change(int[] a){
        a[1] = 10;
    }


}
