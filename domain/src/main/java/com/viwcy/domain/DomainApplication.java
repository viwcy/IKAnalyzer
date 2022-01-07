package com.viwcy.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.dic.Dictionary;

@SpringBootApplication
public class DomainApplication implements ApplicationRunner, InitializingBean {

    private static IKSegmenter ikSegmenter;

    public static void main(String[] args) {
        SpringApplication.run(DomainApplication.class, args);
    }

    /**
     * 项目启动初始化词典
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ikSegmenter.init();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ikSegmenter = new IKSegmenter();
    }
}
