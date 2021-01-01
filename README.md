# 工程简介

spring cloud alibaba examples

# 延伸阅读



| 服务         | 端口规划  | 说明 |
| ------------ | --------- | ---- |
| admin-server | 8088 | admin监控     |
| example-provider     |    9000       |服务提供者      |
| example-consumer-rest             |    9010       |RestTemple调用服务      |
| example-consumer-openfeign             |   9020        | Openfeign调用服务     |

## api
- 服务提供者
```http request
http://localhost:9000/provider/third?data=provider
```
- RestTemplate 
```http request
http://localhost:9010/consumer/rest/third?data=restTemplate
```
- Openfeign
```http request
http://localhost:9020/consumer/openfeign/third?data=openfeign
```