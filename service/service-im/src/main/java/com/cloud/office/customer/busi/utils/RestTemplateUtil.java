package com.cloud.office.customer.busi.utils;

import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.vo.PageVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/17 00:24
 */
@Component
public class RestTemplateUtil {

    ObjectMapper objectMapper = new ObjectMapper();

    String USERCENTER_URL = "http://localhost:8904";
    RestTemplate restTemplate = new RestTemplate();

    //    T RemoteClient(MultiValueMap<String, String> requestBody,String url){
//        User user= restTemplate.postForObject(url, requestBody,T.class);
//    }
//
    public User findByUsername(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username", username);
//        User user= restTemplate.postForObject(USERCENTER_URL+"/user/findUserByUsername", requestBody, User.class);
        return restTemplate.exchange(USERCENTER_URL + "/user/findUserByUsername?username=" + username, HttpMethod.POST, new HttpEntity<>(headers), User.class).getBody();
    }

    public Integer addUser(@RequestBody UserDto userDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        restTemplate.postForObject(USERCENTER_URL+"/user", userDto,null);
        Map<String, Object> body = restTemplate.exchange(USERCENTER_URL + "/user", HttpMethod.POST, new HttpEntity<>(userDto, headers), Map.class).getBody();
        LinkedHashMap<String, Object> data = (LinkedHashMap) body.get("data");
        Integer id = (Integer) data.get("id");
        return id;
    }

    public User getById(Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange(USERCENTER_URL + "/user/getById?id=" + id, HttpMethod.POST, new HttpEntity<>(headers), User.class).getBody();
    }

    public List<User> findUserPageList(UserPageDto userPageDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> body = restTemplate.exchange(USERCENTER_URL + "/user/list", HttpMethod.POST, new HttpEntity<>(userPageDto, headers), Map.class).getBody();
        PageVo<User> objectPageVo = new PageVo();
        LinkedHashMap<String, Object> data = (LinkedHashMap) body.get("data");
        ArrayList<Object> objects = new ArrayList<>();
        List list1 = (List) data.get("list");

        List<User> collect = (List<User>) list1.stream().map(item -> {
            User user = objectMapper.convertValue(item, User.class);
            return user;
        }).collect(Collectors.toList());
        return collect;
    }

    public User getUserById(Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        User body = restTemplate.exchange(USERCENTER_URL + "/user/getUserById/" + id, HttpMethod.GET, new HttpEntity<>(headers), User.class).getBody();
        return body;
    }

}
