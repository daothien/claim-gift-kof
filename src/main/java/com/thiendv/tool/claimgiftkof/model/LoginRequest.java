package com.thiendv.tool.claimgiftkof.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String roleID;
    private String loginType;
    private String shopfrontID;
}
