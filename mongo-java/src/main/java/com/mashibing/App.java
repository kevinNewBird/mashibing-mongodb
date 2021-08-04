package com.mashibing;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {
        log.info("我是日志--!!!!");
        logger.info("我是日志!!!!");
        System.out.println("Hello World!");
    }
}
