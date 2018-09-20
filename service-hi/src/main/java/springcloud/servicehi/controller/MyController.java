package springcloud.servicehi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import springcloud.servicehi.core.redis.IRedisService;

import java.util.Map;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/8/21.
 */
@RestController
public class MyController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    IRedisService iRedisService;

    private static Logger log = LoggerFactory.getLogger(MyController.class);
    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        String str =  "hi " + name + " ,i am from port:" + port;
        log.info(str);
        return  str;
    }

    @RequestMapping("/hello")
    public String home() {
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }

    @RequestMapping("/info")
    public String info(){
        log.info("miya过来访问hi了 ");
        return "miya过来访问hi了";

    }

    @PostMapping("/mySetRedis")
    public String mySetRedis(@RequestBody Map map){
        Boolean result = iRedisService.set("name", map.get("name").toString());
        iRedisService.expire("name", 3600);
        if(result){
            return "插入成功";
        }
        return "插入失败";
    }

    @PostMapping("/getMyRedis")
    public String getMyRedis(){
        String result = iRedisService.get("name");
        return "my name is:"+result;
    }


    @RequestMapping(value = "/getVisitorSucess")
    public ModelAndView getVisitorSucess(ModelMap modelMap){
        return new ModelAndView("/visitor_sucess",modelMap);
    }
}
