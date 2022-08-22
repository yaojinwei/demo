package com.yaojinwei.spring.proxy.service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Log
public class LoginServiceImpl implements LoginService {
    @Override
    public void login() {
        log.info("Interface Login...");
    }
}