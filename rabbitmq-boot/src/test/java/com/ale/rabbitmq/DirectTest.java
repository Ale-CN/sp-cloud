package com.ale.rabbitmq;

import com.ale.rabbitmq.direct.DirectSenderErr;
import com.ale.rabbitmq.direct.DirectSenderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DirectTest {
    @Autowired
    DirectSenderInfo senderInfo;
    @Autowired
    DirectSenderErr senderErr;

    @Test
    public void test() {
        senderErr.directSend();
        senderErr.directSend();
        senderErr.directSend();

        senderInfo.directSend();
    }

}
