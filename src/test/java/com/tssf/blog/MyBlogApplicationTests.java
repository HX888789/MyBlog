package com.tssf.blog;

import com.tssf.blog.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MyBlogApplicationTests {

    @Test
    void contextLoads() {
        String huangxu123 = MD5Util.getMD5("huangxu123");
        log.info("md5加密结果 : {}",huangxu123);
    }

}
