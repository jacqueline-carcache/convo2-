package howard1sttry;

class Producto implements Vendible{
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
   
}