package com.thiendv.tool.claimgiftkof.service;

import com.thiendv.tool.claimgiftkof.model.LoginResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class GetJToken {
    @Value("${kof.api.loginbyid}")
    String api_login;

    @SneakyThrows
    public String getJToken(String ingame_id, String shopfrontID){
        LoginResponse loginResponse = getLoginResponse(ingame_id, shopfrontID);
        String jtoken = loginResponse.getData().getJtoken();
        return jtoken;
    }

    @SneakyThrows
    public LoginResponse getLoginResponse(String ingame_id, String shopfrontID){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("roleID", ingame_id);
        map.add("loginType","9");
        map.add("shopfrontID",shopfrontID);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity( api_login, request , LoginResponse.class );
        return response.getBody();
    }
}
