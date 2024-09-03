package com.Member.aiml_server_2024.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Phone {
    private String company;
    private String phoneName;
    private String telecom;

    public Phone(String company, String phoneName, String telecom) {
        this.company = company;
        this.phoneName = phoneName;
        this.telecom = telecom;
    }
}
