package models;

import java.time.LocalDate;

public class Belleza extends Producto implements CalculoGarantia{
    private boolean sonVeganos;
    private String temporadaUso;
    private final int garantia= 1;

    public Belleza(String nombre, String marca, double precio, int stock, LocalDate fechaCompra, int porcentajeRebaja, boolean sonVeganos, String temporadaUso) {
        super(nombre, marca, precio, stock, fechaCompra, porcentajeRebaja);
        this.sonVeganos = sonVeganos;
        this.temporadaUso = temporadaUso;
    }

    public boolean isSonVeganos() {
        return sonVeganos;
    }

    public String getTemporadaUso() {
        return temporadaUso;
    }

    @Override
    public LocalDate calcularFechaVencimientoGarantia(LocalDate fechaCompra){
        return fechaCompra.plusMonths(garantia);
    }

}
