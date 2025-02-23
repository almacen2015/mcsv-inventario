package backend.mcsvinventario.controllers;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.MovimientoDtoResponse;
import backend.mcsvinventario.services.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Registrar movimiento", description = "Registra un nuevo movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento registrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<MovimientoDtoResponse> registrarMovimiento(@RequestBody MovimientoDtoRequest dto) {
        MovimientoDtoResponse response = movimientoService.registrarMovimiento(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Listar movimientos por producto", description = "Lista los movimientos de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimientos listados"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @GetMapping("/{idProducto}")
    public ResponseEntity<List<MovimientoDtoResponse>> listarMovimientosPorProducto(@PathVariable Integer idProducto) {
        List<MovimientoDtoResponse> response = movimientoService.listarMovimientosPorProducto(idProducto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
