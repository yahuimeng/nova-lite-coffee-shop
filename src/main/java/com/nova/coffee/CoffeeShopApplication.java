package com.nova.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 咖啡在线点单示例应用。
 *
 * <p>这是一个“模块化单体”脚手架：
 * system 模块负责登录与用户上下文，
 * product 模块负责咖啡商品目录，
 * order 模块负责下单主链路。
 */
@EnableAsync
@SpringBootApplication
public class CoffeeShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopApplication.class, args);
    }
}
