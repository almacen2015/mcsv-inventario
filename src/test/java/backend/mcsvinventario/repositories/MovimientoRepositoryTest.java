package backend.mcsvinventario.repositories;

import backend.mcsvinventario.models.entities.Movimiento;
import backend.mcsvinventario.models.entities.TipoMovimiento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class MovimientoRepositoryTest {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    public void testGuardarMovimiento_DadoMovimiento_RetornaMovimiento() {
        // Arrange
        Movimiento movimiento = Movimiento.builder()
                .productoId(1)
                .tipoMovimiento(TipoMovimiento.ENTRADA)
                .cantidad(10)
                .build();

        // Act
        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);

        // Assert
        assertNotNull(movimientoGuardado);
        assertEquals(1, movimientoGuardado.getProductoId());
        assertEquals(TipoMovimiento.ENTRADA, movimientoGuardado.getTipoMovimiento());
        assertEquals(10, movimientoGuardado.getCantidad());
    }

}