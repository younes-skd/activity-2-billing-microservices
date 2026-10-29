package ma.emsi.app;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
@RestController @RequestMapping("/bills") @CrossOrigin(origins="http://localhost:4200")
public class BillController {
 private final BillService s; public BillController(BillService s){this.s=s;}
 @GetMapping public List<Bill> all(){return s.all();}
 @GetMapping("/{id}") public BillService.BillDetails get(@PathVariable Long id){return s.get(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Bill create(@Valid @RequestBody BillRequest r){return s.create(r);}
}
