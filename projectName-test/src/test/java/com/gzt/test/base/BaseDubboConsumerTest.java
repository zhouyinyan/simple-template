package com.gzt.test.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by zyinyan on 2015/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dubbo-consumer.xml"})
public class BaseDubboConsumerTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp() throws Exception {
        logger.info("begin test......");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("end test......");
    }

    @Test
    public void testName() throws Exception {
        assertTrue(true);
    }
}
