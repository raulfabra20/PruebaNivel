package models;

import java.time.LocalDate;

public class ComponenteElectronico extends Producto implements CalculoGarantia{
    private int resolucionImagen;
    private int capacidadBateria;
    private final int garantia = 9;

    public ComponenteElectronico(String nombre, String marca, double precio, int stock, LocalDate fechaCompra, int porcentajeRebaja, int resolucionImagen, int capacidadBateria) {
        super(nombre, marca, precio, stock, fechaCompra, porcentajeRebaja);
        this.resolucionImagen = resolucionImagen;
        this.capacidadBateria = capacidadBateria;
    }

    public int getResolucionImagen() {
        return resolucionImagen;
    }

    public int getCapacidadBateria() {
        return capacidadBateria;
    }

    public int getGarantia() {
        return garantia;
    }

    @Override
    public LocalDate calcularFechaVencimientoGarantia(LocalDate fechaCompra){
        return fechaCompra.plusMonths(garantia);

    }
}
