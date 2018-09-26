package springcloud.sericefeign.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springcloud.sericefeign.cofig.SchedualServiceHi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne( name );
    }


    public static void  printValur(String str){
        System.out.println("print value : "+str);
    }

    /**
     * stream()小用
     */
    @Test
    public void text(){
        List<String> al = Arrays.asList("a", "b", "c", "d", "a");
        al.forEach(MyController::printValur);
        System.out.println(al.stream().filter(n -> n.equals("a")).count());//统计a有几个
        al = al.stream().map(n -> n.toUpperCase()).collect(Collectors.toList());//转大写
        al.forEach(MyController::printValur);
    }
}
