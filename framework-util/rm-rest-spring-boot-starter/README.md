
## Quick Start

Here are some key points listed, the complete example

```xml
<!--add dependency in pom.xml-->
<dependency>
    <groupId>net.realme</groupId>
    <artifactId>rm-rest-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Usage

>  以下设置项可选，是默认值

```yaml
rm.rest.client.request-imeout: 5000
rm.rest.client.connect-imeout: 5000
rm.rest.client.read-imeout: 5000
```


```java
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RestHttpClient restHttpClient;

    public void test() {
        //post request
        HashMap<String, String> body = new HashMap<>();
        body.put("msg", msg);
        String response = restHttpClient.postAsForm(queryUrl, body, String.class);

        //get request
        //String response = restHttpClient.getForString(queryUrl);
    }
}
```

