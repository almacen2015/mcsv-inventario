package backend.mcsvinventario.repositories;

import backend.mcsvinventario.models.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
}
