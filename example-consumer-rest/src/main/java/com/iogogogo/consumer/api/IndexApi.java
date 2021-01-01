package com.iogogogo.consumer.api;

import com.iogogogo.commons.base.BaseApi;
import com.iogogogo.commons.domain.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tao.zeng on 2021/1/1.
 */
@RestController
@RequestMapping("/consumer/rest")
public class IndexApi implements BaseApi {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/third")
    public ResponseWrapper<?> getThird(@RequestParam String data) {
        return restTemplate.
                getForObject("http://example-provider/provider/third?data=" + data, ResponseWrapper.class);
    }
}
