package com.ale.rabbitmq;

import com.ale.rabbitmq.many2many.Sender;
import com.ale.rabbitmq.many2many.Sender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Many2ManyTest {

    @Autowired
    Sender sender;

    @Autowired
    Sender2 sender2;

    @Test
    public void test() {
        for (int i = 0; i < 6; i++) {
            sender.sende();
            sender2.sende();
        }
    }
}
