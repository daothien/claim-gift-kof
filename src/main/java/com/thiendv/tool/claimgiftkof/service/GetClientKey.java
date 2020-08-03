package com.thiendv.tool.claimgiftkof.service;

import com.thiendv.tool.claimgiftkof.constants.Constants;
import com.thiendv.tool.claimgiftkof.model.LoginResponse;
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
public class GetClientKey {
    @Value("${kof.api.getinfo}")
    String api_getinfo;

    public void setClientKey(){
        Constants.CLIENT_KEY_VN = getClientKey("snkvn");
        Constants.CLIENT_KEY_INDO = getClientKey("snkindo");
        Constants.CLIENT_KEY_PHILIP = getClientKey("snkph");
        Constants.CLIENT_KEY_THAILAND = getClientKey("snkth");
    }

    public String getClientKey(String shopfrontID){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("shopfrontID",shopfrontID);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity( api_getinfo, request , LoginResponse.class );
        return response.getBody().getData().getClientKey();
    }

}
