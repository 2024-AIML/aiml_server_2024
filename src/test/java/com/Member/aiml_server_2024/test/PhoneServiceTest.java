package com.Member.aiml_server_2024.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class phoneServiceTest {

    @Autowired
    private PhoneService phoneService;

    @Test
    public void saveAndGetPhoneInfo() throws ExecutionException, InterruptedException {
        Phone phone = new Phone("1231231", "samsung", "sk");
        String result = phoneService.savePhoneInfo(phone);
        System.out.println(result);
    }

    @Test
    public void getPhoneInfo() throws ExecutionException, InterruptedException {
        Phone result = phoneService.getPhoneInfo("samsung");
        System.out.println(result.getPhoneName());
        System.out.println(result.getCompany());
        System.out.println(result.getTelecom());
    }

    @Test
    public void updatePhoneInfo()throws ExecutionException, InterruptedException {
        Phone phone = new Phone("123123", "samsung", "kt");
        String result = phoneService.updatePhoneInfo(phone);
        System.out.println(result);
    }

    @Test
    public void deletePhoneInfo() {
        String result = phoneService.deletePhoneInfo("samsung");
        System.out.println(result);
    }
}