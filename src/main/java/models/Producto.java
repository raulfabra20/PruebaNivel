package models;

import java.time.LocalDate;

public abstract class Producto implements CalculoGarantia{
    protected int codigoProductoPropio;
    protected static int contadorCodigo = 0;
    protected String nombre;
    protected String marca;
    protected double precio;
    protected int stock;
    protected LocalDate fechaCompra;
    protected int porcentajeRebaja;

    public Producto(String nombre, String marca, double precio, int stock, LocalDate fechaCompra, int porcentajeRebaja) {
        this.codigoProductoPropio = ++contadorCodigo;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.fechaCompra = fechaCompra;
        this.porcentajeRebaja = porcentajeRebaja;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public int getPorcentajeRebaja() {
        return porcentajeRebaja;
    }

    public int getCodigoProductoPropio() {
        return codigoProductoPropio;
    }

    public double aplicarRebaja() {
        return precio = precio - (precio * porcentajeRebaja / 100);
    }
    public void incrementarStock(int cantidad){
        this.stock += cantidad;
    }
    public void decrementarStock(int cantidad) throws Exception {
        if(this.stock < cantidad){
            throw new Exception("No hay suficiente stock disponible");
        } else {
            this.stock -= cantidad;
        }
    }

    public abstract LocalDate calcularFechaVencimientoGarantia(LocalDate fechaCompra);

}
