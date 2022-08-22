package com.yaojinwei.spring.proxy.service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Log
public class PlainLoginService {
    public void login() {
        log.info("Class Login...");
    }
}