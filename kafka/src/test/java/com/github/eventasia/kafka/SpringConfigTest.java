package com.github.eventasia.kafka;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringConfigTest {
    private final CountDownLatch latch1 = new CountDownLatch(1);

    @Autowired
    private KafkaTemplate<Integer, String> template;

    @Ignore
    @Test
    public void testSimple() throws Exception {
        template.send("booking.event", 0, "foo");
        latch1.await(5, TimeUnit.SECONDS);
        System.out.println("Waited");
    }

}
