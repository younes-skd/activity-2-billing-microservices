package ma.emsi.app;

import jakarta.persistence.*;
import java.time.Instant;
import java.math.BigDecimal;
import java.util.*;
@Entity
public class Bill {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long customerId;
 private Instant issuedAt=Instant.now();
 @ElementCollection(fetch=FetchType.EAGER) private List<Line> lines=new ArrayList<>();
 @Embeddable public static class Line {
   private Long productId; private String productName; private int quantity;
   @Column(precision=19,scale=2) private BigDecimal unitPrice;
   public Line(){} public Line(Long id,String name,int q,BigDecimal p){productId=id;productName=name;quantity=q;unitPrice=p;}
   public Long getProductId(){return productId;} public String getProductName(){return productName;}
   public int getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;}
   public BigDecimal getTotal(){return unitPrice.multiply(BigDecimal.valueOf(quantity));}
 }
 public Long getId(){return id;} public Long getCustomerId(){return customerId;} public void setCustomerId(Long v){customerId=v;}
 public Instant getIssuedAt(){return issuedAt;} public List<Line> getLines(){return lines;} public void setLines(List<Line> v){lines=v;}
 public BigDecimal getTotal(){return lines.stream().map(Line::getTotal).reduce(BigDecimal.ZERO,BigDecimal::add);}
}
