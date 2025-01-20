package backend.mcsvinventario.client;

import backend.mcsvinventario.models.dtos.ProductoDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mcsv-productos", url = "http://localhost:8081/productos")
public interface ProductoClient {

    @GetMapping("/{id}")
    ProductoDtoResponse obtenerProducto(@PathVariable Integer id);
}
