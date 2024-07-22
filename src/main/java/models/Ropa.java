package models;

import java.time.LocalDate;

public class Ropa extends Producto implements CalculoGarantia{
    private int talla;
    private String tipoTejido;
    private String tipoPrenda;
    private final int garantia= 2;

    public Ropa(String nombre, String marca, double precio, int stock, LocalDate fechaCompra, int porcentajeRebaja, int talla, String tipoTejido, String tipoPrenda) {
        super(nombre, marca, precio, stock, fechaCompra, porcentajeRebaja);
        this.talla = talla;
        this.tipoTejido = tipoTejido;
        this.tipoPrenda = tipoPrenda;
    }

    public int getTalla() {
        return talla;
    }

    public String getTipoTejido() {
        return tipoTejido;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    @Override
    public LocalDate calcularFechaVencimientoGarantia(LocalDate fechaCompra){
        return fechaCompra.plusMonths(garantia);
    }

}
