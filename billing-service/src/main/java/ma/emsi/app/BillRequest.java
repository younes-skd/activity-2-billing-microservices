package ma.emsi.app;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
public record BillRequest(@NotNull @Positive Long customerId,@NotEmpty List<@Valid LineRequest> lines) {
 public record LineRequest(@NotNull @Positive Long productId,@Min(1) int quantity) {}
}
