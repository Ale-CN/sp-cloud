package com.ale.rabbitmq;

import com.ale.rabbitmq.fanout.FanoutSender1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FanoutTest {
    @Autowired
    FanoutSender1 fanoutSender;

    @Test
    public void test() {
        for (int i = 0; i < 4; i++) {
            fanoutSender.fanoutSend();
        }
    }
}
