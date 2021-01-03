package com.iogogogo.consumer.third.fallback;

import com.iogogogo.commons.base.BaseApi;
import com.iogogogo.commons.domain.ResponseWrapper;
import com.iogogogo.consumer.third.ThirdProvider;
import org.springframework.stereotype.Component;

/**
 * Created by tao.zeng on 2021/1/1.
 */
@Component
public class ThirdProviderFallback implements ThirdProvider, BaseApi {

    @Override
    public ResponseWrapper<?> thirdIndex(String data) {
        return result(400, "请求失败，熔断降级处理。", data);
    }
}
