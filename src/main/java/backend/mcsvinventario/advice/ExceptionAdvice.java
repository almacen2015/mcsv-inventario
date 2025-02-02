package backend.mcsvinventario.advice;

import backend.mcsvinventario.exceptions.MovimientoException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MovimientoException.class)
    public ResponseEntity<?> exceptionHandlerMovimiento(MovimientoException e) {
        if (e.getMessage().equals(MovimientoException.MOVIMIENTO_CANTIDAD_INVALIDA) ||
                e.getMessage().equals(MovimientoException.MOVIMIENTO_TIPO_INVALIDO) ||
                e.getMessage().equals(MovimientoException.MOVIMIENTO_PRODUCTO_INVALIDO)) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
