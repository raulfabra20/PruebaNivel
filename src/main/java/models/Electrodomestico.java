package models;

import java.time.LocalDate;

public class Electrodomestico extends Producto implements CalculoGarantia{
    private int consumoEnergetico;
    private LocalDate fechaFabricacion;
    private int capacidadCarga;
    private final int garantia = 18;

    public Electrodomestico(String nombre, String marca, double precio, int stock, LocalDate fechaCompra, int porcentajeRebaja, int consumoEnergetico, LocalDate fechaFabricacion, int capacidadCarga) {
        super(nombre, marca, precio, stock, fechaCompra, porcentajeRebaja);
        this.consumoEnergetico = consumoEnergetico;
        this.fechaFabricacion = fechaFabricacion;
        this.capacidadCarga = capacidadCarga;
    }

    public int getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public int getGarantia() {
        return garantia;
    }

    @Override
    public LocalDate calcularFechaVencimientoGarantia(LocalDate fechaCompra){
        return fechaCompra.plusMonths(garantia);

    }

}
