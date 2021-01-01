package com.iogogogo.consumer.api;

import com.iogogogo.commons.domain.ResponseWrapper;
import com.iogogogo.consumer.third.ThirdProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tao.zeng on 2021/1/1.
 */
@RestController
@RequestMapping("/consumer/openfeign")
public class IndexApi {

    @Autowired
    private ThirdProvider thirdProvider;

    @GetMapping("/third")
    public ResponseWrapper<?> index(@RequestParam String data) {
        return thirdProvider.thirdIndex(data);
    }
}
