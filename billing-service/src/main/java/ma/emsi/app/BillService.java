package ma.emsi.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
@Service @Transactional
public class BillService {
 private final BillRepository repo; private final CustomerClient customers; private final ProductClient products;
 public BillService(BillRepository r,CustomerClient c,ProductClient p){repo=r;customers=c;products=p;}
 public List<Bill> all(){return repo.findAll();}
 public Bill entity(Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Bill not found"));}
 public BillDetails get(Long id){Bill b=entity(id);return new BillDetails(b,customers.get(b.getCustomerId()));}
 public Bill create(BillRequest r){
   customers.get(r.customerId());
   Bill bill=new Bill();bill.setCustomerId(r.customerId());
   bill.setLines(r.lines().stream().map(l->{var p=products.get(l.productId());if(l.quantity()>p.quantity())throw new IllegalArgumentException("Quantity exceeds product stock");return new Bill.Line(p.id(),p.name(),l.quantity(),p.price());}).toList());
   return repo.save(bill);
 }
 public record BillDetails(Bill bill,CustomerClient.Customer customer) {}
}
