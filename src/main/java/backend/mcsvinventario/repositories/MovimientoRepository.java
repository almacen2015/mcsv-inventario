package backend.mcsvinventario.repositories;

import backend.mcsvinventario.models.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByProductoId(Integer idProducto);
}
