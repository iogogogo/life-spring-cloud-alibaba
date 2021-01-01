package com.iogogogo.consumer.third;

import com.iogogogo.commons.domain.ResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tao.zeng on 2021/1/1.
 */
// @FeignClient(name = "example-provider", fallback = ThirdProviderFallback.class)
@FeignClient("example-provider")
public interface ThirdProvider {

    @RequestMapping(value = "/provider/third", method = RequestMethod.GET)
    ResponseWrapper<?> thirdIndex(@RequestParam("data") String data);

}
