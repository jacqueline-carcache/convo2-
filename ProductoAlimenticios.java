package howard1sttry;

import java.util.Date;

  public class ProductoAlimenticios extends Producto {
    private Date fechaCaducidad;

    public ProductoAlimenticios(String nombre, double precio, int Cantidad, Date fechaCaducidad) {
        super(nombre, precio, Cantidad);
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    

}
