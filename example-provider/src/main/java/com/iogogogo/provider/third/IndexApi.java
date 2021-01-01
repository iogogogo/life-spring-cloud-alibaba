package com.iogogogo.provider.third;

import com.iogogogo.commons.base.BaseApi;
import com.iogogogo.commons.domain.ResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by tao.zeng on 2021/1/1.
 */
@RestController
@RequestMapping("/provider")
public class IndexApi implements BaseApi {

    @GetMapping("/third")
    public ResponseWrapper<?> index(@RequestParam("data") String data) {
        return ok(String.join(".", data, UUID.randomUUID().toString().replace("-", "")));
    }
}
