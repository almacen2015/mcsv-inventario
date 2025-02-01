package backend.mcsvinventario.exceptions;

public class MovimientoException extends RuntimeException {
    public static final String MOVIMIENTO_CANTIDAD_INVALIDA = "La cantidad del movimiento es inválida";
    public static final String MOVIMIENTO_TIPO_INVALIDO = "El tipo de movimiento es inválido";
    public static final String MOVIMIENTO_SIN_STOCK = "No hay stock suficiente";
    public static final String MOVIMIENTO_PRODUCTO_INVALIDO = "El producto no existe";

    public MovimientoException(String message) {
        super(message);
    }
}
