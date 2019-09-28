package com.yqh.autocreatemybatisfiles;

import com.yqh.autocreatemybatisfiles.bean.ProjectProperties;
import com.yqh.autocreatemybatisfiles.service.AutoCreateFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication

public class AutoCreateMybatisFilesApplication {

    @Autowired
    AutoCreateFiles autoCreateFiles;


    public static void main(String[] args) {
        SpringApplication.run(AutoCreateMybatisFilesApplication.class, args);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws IOException {
        autoCreateFiles.creteAll();
    }

}
