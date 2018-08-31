package springcloud.servicemiya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceMiyaApplication {
	private static Logger log = LoggerFactory.getLogger(ServiceMiyaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceMiyaApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/miya")
	public String info(){
		return restTemplate.getForObject("http://localhost:8762/info",String.class);
	}

//	@RequestMapping("/miya2")
//	public String miya2(){
//		restTemplate.getForObject("http://localhost:8762/info",String.class);
//		return "hi来访问米呀了";
//	}
}
