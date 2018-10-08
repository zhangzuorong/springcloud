package springcloud.servicehi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springcloud.servicehi.core.redis.IRedisService;
import springcloud.servicehi.service.MyRedisService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/9/6.
 */
@Controller
@RequestMapping("/redisLock")
public class MyRedisController {
    @Autowired
    MyRedisService myRedisService;
    @Autowired
    IRedisService iRedisService;

    Lock lock = new ReentrantLock();

    @PostMapping("/testRedisLock")
    public void testRedisLock(@RequestBody Map map){
        String key = map.get("id").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
        new Thread(){
            public void run(){
                try {
                    if(lock.tryLock()){
                        System.out.println("线程一得到了锁");
                    }else {
                        System.out.println("锁被占用");
                    }
                    Boolean result = myRedisService.setConcurrentLock(key,1l);
                    if(result){
                        iRedisService.expire(key,10);
                        System.out.println("线程一抢单成功-----"+"资源"+key+"时间："+sdf.format(new Date()));
//                        TimeUnit.MILLISECONDS.sleep(500);
//                        myRedisService.deleteConcurrentLock(key);
                    }else {
                        System.out.println("线程一抢单失败-----"+"资源"+key+"时间："+sdf.format(new Date()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    if(lock.tryLock()){
                        System.out.println("线程二得到了锁");
                    }else {
                        System.out.println("锁被占用");
                    }
                    Boolean result = myRedisService.setConcurrentLock(key,1l);
                    if(result){
                        iRedisService.expire(key,10);
                        System.out.println("线程二抢单成功-----"+"资源"+key+"时间："+sdf.format(new Date()));
                        //TimeUnit.MILLISECONDS.sleep(500);
                        //myRedisService.deleteConcurrentLock(key);
                    }else {
                        System.out.println("线程二抢单失败-----"+"资源"+key+"时间："+sdf.format(new Date()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    Boolean result = myRedisService.setConcurrentLock(key,1l);
                    if(result){
                        iRedisService.expire(key,10);
                        System.out.println("线程三抢单成功-----"+"资源"+key+"时间："+sdf.format(new Date()));
//                        TimeUnit.MILLISECONDS.sleep(500);
//                        myRedisService.deleteConcurrentLock(key);
                    }else {
                        System.out.println("线程三抢单失败-----"+"资源"+key+"时间："+sdf.format(new Date()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    Boolean result = myRedisService.setConcurrentLock(key,1l);
                    if(result){
                        iRedisService.expire(key,10);
                        System.out.println("线程四抢单成功-----"+"资源"+key+"时间："+sdf.format(new Date()));
//                        TimeUnit.MILLISECONDS.sleep(500);
//                        myRedisService.deleteConcurrentLock(key);
                    }else {
                        System.out.println("线程四抢单失败-----"+"资源"+key+"时间："+sdf.format(new Date()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    Boolean result = myRedisService.setConcurrentLock(key,1l);
                    if(result){
                        iRedisService.expire(key,10);
                        System.out.println("线程五抢单成功-----"+"资源"+key+"时间："+sdf.format(new Date()));
//                        TimeUnit.MILLISECONDS.sleep(500);
//                        myRedisService.deleteConcurrentLock(key);
                    }else {
                        System.out.println("线程五抢单失败-----"+"资源"+key+"时间："+sdf.format(new Date()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    Boolean result = myRedisService.setConcurrentLock(key,1l);
                    if(result){
                        iRedisService.expire(key,10);
                        System.out.println("线程六抢单成功-----"+"资源"+key+"时间："+sdf.format(new Date()));
//                        TimeUnit.MILLISECONDS.sleep(500);
//                        myRedisService.deleteConcurrentLock(key);
                    }else {
                        System.out.println("线程六抢单失败-----"+"资源"+key+"时间："+sdf.format(new Date()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
