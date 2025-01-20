package backend.mcsvinventario.exceptions;

public class MovimientoException extends RuntimeException {
    public static final String MOVIMIENTO_CANTIDAD_INVALIDA = "La cantidad del movimiento es inválida";
    public static final String MOVIMIENTO_TIPO_INVALIDO = "El tipo de movimiento es inválido";
    public static final String MOVIMIENTO_PRODUCTO_INVALIDO = "El producto del movimiento es inválido";


    public MovimientoException(String message) {
        super(message);
    }
}
