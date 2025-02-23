package backend.mcsvinventario.controllers;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.MovimientoDtoResponse;
import backend.mcsvinventario.services.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
@SecurityRequirement(name = "BearerAuth")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Registrar movimiento", description = "Registra un nuevo movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento registrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    @PostMapping
    public ResponseEntity<MovimientoDtoResponse> registrarMovimiento(@RequestBody MovimientoDtoRequest dto) {
        MovimientoDtoResponse response = movimientoService.registrarMovimiento(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Listar movimientos por producto", description = "Lista los movimientos de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimientos listados"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    @GetMapping("/{idProducto}")
    public ResponseEntity<List<MovimientoDtoResponse>> listarMovimientosPorProducto(@PathVariable Integer idProducto) {
        List<MovimientoDtoResponse> response = movimientoService.listarMovimientosPorProducto(idProducto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
