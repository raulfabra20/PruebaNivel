package models;

import java.time.LocalDate;

public interface CalculoGarantia {
    public LocalDate calcularFechaVencimientoGarantia(LocalDate fechaCompra);

}
