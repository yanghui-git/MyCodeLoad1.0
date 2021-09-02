package com.yh.aop;

import com.yanghui.annotation.annotationandaop.SpringContextUtil;
import com.yanghui.aop.TestAop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextUtilTest {

    /**
     * 测试获取service
     */
    @Test
    public void test() {
        TestAop testAop = (TestAop) SpringContextUtil.getBean("testAop");
        testAop.testOne();
    }


}
