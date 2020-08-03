package com.thiendv.tool.claimgiftkof.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponse {
    private String returnCode;
    private String returnMessage;
    private Data data;
}
