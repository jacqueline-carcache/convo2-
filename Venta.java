
package howard1sttry;

import java.time.LocalDate;
import java.util.List;

    class Venta extends Producto {
    private LocalDate fechaVenta;
    private int cantidad;
    private double gananciasVenta;
    private List<Vendible> items;

    public Venta(String nombre, LocalDate fechaVenta, int cantidad, double gananciasVenta, String modelo, List<Vendible> items) {
        super(nombre, 0, cantidad);
        this.fechaVenta = fechaVenta;
        this.cantidad = cantidad;
        this.gananciasVenta = gananciasVenta;
        this.items=items;
    }
    public List<Vendible> getItems() {
        return items;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getGananciasVenta() {
        return gananciasVenta;
    }

    public void setGananciasVenta(double gananciasVenta) {
        this.gananciasVenta = gananciasVenta;
    }
    public Vendible getVendible() {
        return null;
    }
    public void setVendible(Vendible productoEncontrado) {
    }
    public String getNumVenta() {
        return null;
    }
}