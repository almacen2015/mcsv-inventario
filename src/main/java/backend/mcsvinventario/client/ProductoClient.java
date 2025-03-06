package backend.mcsvinventario.client;

import backend.mcsvinventario.models.dtos.ProductoDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "mcsv-productos", url = "http://localhost:8081/api/producto")
public interface ProductoClient {

    @GetMapping("/{id}")
    ProductoDtoResponse getProduct(@PathVariable Integer id);

    @PutMapping("/stock/{id}/{cantidad}/{tipoMovimiento}")
    void updateStock(@PathVariable Integer id, @PathVariable Integer cantidad, @PathVariable String tipoMovimiento);
}
