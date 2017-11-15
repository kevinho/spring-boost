package com.ihuanqu.springkit.sms;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by changbinhe on 2017/1/20.
 */
public class LsmSmsDeliver implements SmsDeliver {

    private RestTemplate restTemplate;
    private static final String sendUrl = "http://sms-api.luosimao.com/v1/send.json";
    private static final String sendBatchUrl = "http://sms-api.luosimao.com/v1/send_batch.json";

    public LsmSmsDeliver(String apiKey) {
        restTemplate = new RestTemplate();

        restTemplate.setMessageConverters(Arrays.asList(
                new FormHttpMessageConverter(),
                new StringHttpMessageConverter()
        ));
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("api", apiKey));
    }

    @Override
    public boolean send(String mobile, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("mobile", mobile);
        map.add("message", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(sendUrl, request, String.class);

        try {
            JSONObject jsonObj = new JSONObject(response.getBody());
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if (error_code == 0) {
                return true;
            } else {
                System.out.println("Send message failed,code is " + error_code + ",msg is " + error_msg);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
