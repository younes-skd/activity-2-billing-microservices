package ma.emsi.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
@FeignClient(name="inventory-service",url="${clients.inventory.url:}")
public interface ProductClient {
 @GetMapping("/products/{id}") Product get(@PathVariable Long id);
 record Product(Long id,String name,BigDecimal price,int quantity,boolean selected) {}
}
