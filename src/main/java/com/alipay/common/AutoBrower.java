package com.alipay.common;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;

/**
 * AutoBrower
 *
 * @author Administrator
 * @date 2020/4/17
 */
@Configuration
public class AutoBrower {
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");

        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + ConData.SERVER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
