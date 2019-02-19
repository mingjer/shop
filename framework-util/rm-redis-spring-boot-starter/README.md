
## Quick Start

Here are some key points listed, the complete example

```xml
<!--add dependency in pom.xml-->
<dependency>
    <groupId>net.realme</groupId>
    <artifactId>rm-redis-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Usage

```yaml
spring.redis:
  cluster:
    max-redirects: 3
    nodes: 172.16.44.164:6379,172.16.44.164:6380,172.16.44.164:6381
  database: 0
  timeout: 2000ms
```


```java
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RedisCache<ProductSkuDto> cache;

    public void test() {
        //ProductSkuDto productSkuDto = cache.get("cacheKey");

        productSkuDto = productDao.selectByPrimaryKey(1);
        cache.set("cacheKey", productSkuDto);
    }
}
```

