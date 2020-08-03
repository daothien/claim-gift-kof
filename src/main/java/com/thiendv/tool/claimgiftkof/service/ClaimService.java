package com.thiendv.tool.claimgiftkof.service;

import com.thiendv.tool.claimgiftkof.model.LoginRequest;
import com.thiendv.tool.claimgiftkof.model.LoginResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ClaimService {
    @Value("${kof.api.claimgift}")
    String api_claim;
    @Autowired
    GetJToken getJToken;

    @SneakyThrows
    public void claim(String ingame_id, String shopfrontID, String clientKey, String promotionID, String giftID,String serverID) {
        String jtoken = getJToken.getJToken(ingame_id, shopfrontID);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("jtoken", jtoken);
        map.add("clientKey", clientKey);
        map.add("roleID", ingame_id);
        map.add("promotionID", promotionID);
        map.add("giftID",giftID);
        map.add("serverID",serverID);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(api_claim, request, LoginResponse.class);
        System.out.println(shopfrontID+": "+response.getBody().getData().getPromotionMessage());
    }


}
