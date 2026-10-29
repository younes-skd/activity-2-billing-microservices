package ma.emsi.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.math.BigDecimal;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(properties={"spring.cloud.config.enabled=false","eureka.client.enabled=false"})
class BillingIntegrationTest {
 @MockitoBean CustomerClient customers; @MockitoBean ProductClient products;
 @Autowired BillService service;
 @Test void snapshotsPriceAndCalculatesTotal(){
  when(customers.get(1L)).thenReturn(new CustomerClient.Customer(1L,"Demo","demo@example.com"));
  when(products.get(2L)).thenReturn(new ProductClient.Product(2L,"Laptop",new BigDecimal("12.50"),10,false));
  Bill b=service.create(new BillRequest(1L,List.of(new BillRequest.LineRequest(2L,3))));
  assertThat(b.getTotal()).isEqualByComparingTo("37.50");
  assertThat(service.get(b.getId()).customer().name()).isEqualTo("Demo");
  when(products.get(2L)).thenReturn(new ProductClient.Product(2L,"Laptop",new BigDecimal("99"),10,false));
  assertThat(service.get(b.getId()).bill().getTotal()).isEqualByComparingTo("37.50");
 }
 @Test void rejectsUnavailableStock(){
  when(products.get(2L)).thenReturn(new ProductClient.Product(2L,"Laptop",BigDecimal.TEN,1,false));
  assertThatThrownBy(()->service.create(new BillRequest(1L,List.of(new BillRequest.LineRequest(2L,2))))).isInstanceOf(IllegalArgumentException.class);
 }
}
