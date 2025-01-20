package backend.mcsvinventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class McsvInventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsvInventarioApplication.class, args);
    }

}
