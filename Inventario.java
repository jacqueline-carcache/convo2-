package howard1sttry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Inventario 
{

    // maneja productos, ventas y la generación de facturas
    private List<Vendible> vendibles;
    private List<Venta> ventas;
    private static String empresa;
    static Scanner scanner = new Scanner(System.in);

    public Inventario() 
    {
        vendibles = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public static void setEmpresa(String empresa) 
    {
        Inventario.empresa = empresa;
    }

    public void agregarProducto(Vendible vendible) 
    {
        vendibles.add(vendible);
    }

    public void agregarProductoElectronico(ProductoElectronico productoElectronico) 
    {
        vendibles.add(productoElectronico);
    }

    public void agregarProductoAlimentation(ProductoAlimenticios ProductoAlimentation) 
    {
        vendibles.add(ProductoAlimentation);
    }

    public void agregarVenta(Venta venta) 
    {
        ventas.add(venta);
    }

    
    public void mostrarMenu() 
    {
        int opcion;
        boolean salir = false;

        do {
            System.out.println("\nBienvenido al Sistema :)\n");
            System.out.println("==== MENÚ DEL INVENTARIO ====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Realizar Venta");
            System.out.println("4. Eliminar Producto del Inventario");
            System.out.println("5. Mostrar ventas");
            System.out.println("6. SALIR");
            System.out.print("Seleccione una opción: \n");
             opcion = scanner.nextInt();
            try 
            {
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                
                switch (opcion) 
                {
                    case 1:
                        registrarProducto();
                        break;
                    case 2:
                        mostrarInventario(vendibles);
                        break;
                    case 3:
                        realizarVenta();
                        break;
                    case 4:
                        eliminarProducto();
                        break;
                    case 5:
                        mostrarVentas(ventas);
                        break;
                    case 6:
                        System.out.println("\nSaliendo del programa...\n");
                        salir = true;
                        break;
                    default:
                        System.out.println("\nOpción inválida. Por favor, seleccione nuevamente.\n");
                         System.out.println(
                    "------------------------------------------------------------------------------------------------");
                        break;
                }
            
        } else {
            System.out.println("\nDebe ingresar un número válido. Inténtelo nuevamente.\n");
            System.out.println("------------------------------------------------------------------------------------------------");
            scanner.nextLine(); // Limpiar el buffer de entrada
            // Opcional: No es necesario restablecer la opción si deseas que el usuario ingrese un valor válido
            // opcion = 0; 
        }
    } catch (InputMismatchException e) {
        System.out.println("\nDebe ingresar un número válido. Inténtelo nuevamente.\n");
        System.out.println("------------------------------------------------------------------------------------------------");
        scanner.nextLine(); // Limpiar el buffer de entrada solo si estás utilizando nextLine() antes
        // Opcional: No es necesario restablecer la opción si deseas que el usuario ingrese un valor válido
        // opcion = 0; 
    }
                        
            System.out.println("¿Desea volver a utilizar la app? (s/n)");
            char respuesta = scanner.next().charAt(0);

           // Evaluar la respuesta y tomar acciones según sea necesario
          if (respuesta == 's' || respuesta == 'S') {
             // Si la respuesta es 's' o 'S', llamar al método para mostrar el menú
             mostrarMenu();
           } else if (respuesta == 'n' || respuesta == 'N') {
               // Si la respuesta es 'n' o 'N', cerrar la aplicación
           System.out.println("\n¡Muchas gracias por visitarnos!");
           salir = true;
           } else {
             // Si la respuesta no es válida, mostrar un mensaje de error
        System.out.println("\n\nRespuesta no válida. Por favor, ingrese 's' o 'n'.");
    }
        
        } while (!salir);
       
        // Limpiar Consola
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    
    
    

    public void registrarProducto() 
    {
        // *Recopila la información necesaria para crear un nuevo producto y luego lo
        // agrega a la lista de productos en el inventario */
        do {
        
        System.out.println("\n==== REGISTRO DE PRODUCTOS ====\n");
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese las unidades del producto: ");
        int unidades = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1. Producto electrónico");
        System.out.println("2. Producto alimenticio");
        System.out.print("Seleccione el tipo de producto: ");
        int tipoProducto = scanner.nextInt();
        scanner.nextLine();

        Vendible vendible;// Creacion del producto

        switch (tipoProducto) 
        {
            case 1:
                System.out.print("\nIngrese el modelo del producto electrónico: ");
                String modelo = scanner.nextLine();
                vendible = new ProductoElectronico(nombre, precio, unidades, modelo);
                break;
            case 2:
                System.out.print("\nIngrese la fecha de caducidad del producto alimenticio (dd/MM/yyyy): ");
                String fechaCaducidadStr = scanner.nextLine();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaCaducidad = dateFormat.parse(fechaCaducidadStr);
                    vendible = new ProductoAlimenticios(nombre, precio, unidades, fechaCaducidad);
                }
                
                catch (ParseException e) 
                {
                    System.out.println("\nFormato de fecha inválido. No se pudo registrar el producto alimenticio.\n");
                     System.out.println(
                    "------------------------------------------------------------------------------------------------");
                    return;
                }
                break;
            default:
                System.out.println("\nOpción inválida. No se pudo registrar el producto.\n");
                 System.out.println(
                    "------------------------------------------------------------------------------------------------");
                return;
        }
        // Finalmente se agrega
        agregarProducto(vendible);
        System.out.println("\nProducto registrado exitosamente.\n");
         System.out.println(
                    "------------------------------------------------------------------------------------------------");
        // Preguntar al usuario si desea agregar otro producto
        System.out.print("\n¿Desea agregar otro producto? (s/n): ");
        char respuesta = scanner.next().charAt(0);
        scanner.nextLine(); // Limpiar el buffer de entrada

        if (respuesta != 's' && respuesta != 'S') {
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            break;
             // Si la respuesta no es 's' o 'S', salimos del bucle y del método
        }

    } while (true);
    scanner.nextLine();
    }

    public void mostrarInventario(List<Vendible> vendibles) {
        if (vendibles.isEmpty()) {
            System.out.println("\nEl inventario está vacío.\n");
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("                                                              ");
            System.out.println("\n==== INVENTARIO ====\n");
            System.out.println("Numero\tNombre\t\tPrecio\t\tUnidades\t\tTipo\t\tModelo/Fecha Caducidad\n");
            System.out.println("-----------------------------------------------------------------------------------------------------");
    
            // Aplicar ordenamiento por inserción solo si la lista no está ordenada
            if (!estaOrdenada(vendibles)) {
                ordenamientoPorInsercion(vendibles);
            }
    
            // Mostrar el inventario ordenado
            for (int i = 0; i < vendibles.size(); i++) {
                Vendible vendible = vendibles.get(i);
                int NumProducto = i + 1;
    
                System.out.println("                                                                                ");
                String tipoProducto = vendible instanceof ProductoElectronico ? "Electrónico" : "Alimenticio";
                String modeloFechaCaducidad = "";
    
                if (vendible instanceof ProductoElectronico) {
                    modeloFechaCaducidad = ((ProductoElectronico) vendible).getModelo();
                } else if (vendible instanceof ProductoAlimenticios) {
                    Date fechaCaducidad = ((ProductoAlimenticios) vendible).getFechaCaducidad();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    modeloFechaCaducidad = sdf.format(fechaCaducidad);
                }
    
                System.out.printf("%-8s%-17s%-17s%-18s%-24s%-24s\n", NumProducto, vendible.getNombre(),
                        vendible.getPrecio(), vendible.getCantidad(), tipoProducto, modeloFechaCaducidad);
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
        }
    }

    public void realizarVenta() {
        System.out.println("\n==== REALIZAR VENTA ====");
    
        if (vendibles.isEmpty()) {
            System.out.println("\nNo hay productos en el inventario para realizar la venta.\n");
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            return;
        }
    
        // Ordenar la lista de productos antes de mostrar el inventario
        ordenamientoPorInsercion();
        
        mostrarInventario(vendibles);// Se muestra el inventario actual
    
        System.out.print("\nIngrese la cantidad de los diferentes productos a vender: ");
        int numeroProductos = scanner.nextInt();
        scanner.nextLine();
    
        if (numeroProductos <= 0) {// Mayor que 0
            System.out.println("\nCantidad inválida. La venta no se puede realizar.\n");
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            return;
        }
    
        for (int i = 0; i < numeroProductos; i++) {
            System.out.println("\nProducto #" + (i + 1));
            System.out.print("\nIngrese el nombre del producto: ");
            String nombre = scanner.nextLine();
    
            // Se verifica si existe el producto utilizando búsqueda lineal
            int indiceProducto = busquedaLinealProducto(nombre);
    
            if (indiceProducto != -1) {
                Vendible productoEncontrado = vendibles.get(indiceProducto);
    
                System.out.print("Ingrese la cantidad a vender: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();
    
                if (cantidad > 0 && cantidad <= productoEncontrado.getCantidad()) {
                    productoEncontrado.setCantidad(productoEncontrado.getCantidad() - cantidad);
                    System.out.println("\nVenta realizada exitosamente.\n");
                    System.out.println(
                            "------------------------------------------------------------------------------------------------");
    
                    LocalDate fechaVenta = LocalDate.now();
    
                    double gananciasVenta = cantidad * productoEncontrado.getPrecio();
                    Venta venta = new Venta(nombre, fechaVenta, cantidad, gananciasVenta, nombre, vendibles);// Aqui se
                                                                                                            // agrega a las
                                                                                                            // ventas
    
                    agregarVenta(venta);
                } else {
                    System.out.println("\nCantidad inválida. La venta no se puede realizar.\n");
                    System.out.println(
                            "------------------------------------------------------------------------------------------------");
    
                }
            } else {
                System.out.println("\nProducto no encontrado en el inventario.\n");
                System.out.println(
                        "------------------------------------------------------------------------------------------------");
                continue;
            }
        }
    
        if (!ventas.isEmpty()) {
            generarFactura(ventas);
        }
    }
    

    double vta;

    public void generarFactura(List<Venta> ventas) {
       
        System.out.println("\n==== GENERANDO FACTURA ====");
    
        System.out.print("\nIngrese el nombre del cliente: ");
        String cliente = scanner.nextLine();
    
        System.out.println("\n/////// Factura ///////");
        System.out.println("**********************");
        System.out.println("Empresa: " + empresa);
        System.out.println("Cliente: " + cliente);
    
        double totalVenta = 0.0;
    
        // Aplicar búsqueda lineal para encontrar el Vendible correspondiente a cada venta
        for (Venta venta : ventas) {
            totalVenta += venta.getGananciasVenta();
            Vendible productoEncontrado = buscarVendible(venta.getNombre());
    
            System.out.println("\nVenta #" + venta.getNumVenta());
            System.out.println("Nombre del producto: " + venta.getNombre());
            System.out.println("Unidades: " + venta.getCantidad());
            System.out.println("Precio unitario: " + productoEncontrado.getPrecio());
            System.out.println("Precio total de producto: " + venta.getGananciasVenta());
            System.out.println("----------------------------------------");
        }
    
        System.out.println("Pago total: " + totalVenta);
        System.out.println("\n::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    
        vta = totalVenta;
       
    }

    public void mostrarVentas(List<Venta> ventas) {
        System.out.println("\n==== VENTAS REALIZADAS ====\n");
        if (ventas.isEmpty()) {
            System.out.println("\nNo hay ventas realizadas.\n");
            System.out.println("------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Número\tNombre\t\tUnidades\tPrecio Unitario\t\tTotal Venta");
            System.out.println("------------------------------------------------------------------------------------------------");
    
            // Aplicar búsqueda lineal para encontrar el Vendible correspondiente a cada venta
            for (Venta venta : ventas) {
                Vendible productoEncontrado = buscarVendible(venta.getNombre());
                venta.setVendible(productoEncontrado);
            }
    
            // Aplicar ordenamiento por inserción a la lista de ventas
            ordenamientoPorInsercionVentas(ventas);
    
            // Mostrar las ventas ordenadas
            for (int i = 0; i < ventas.size(); i++) {
                Venta venta = ventas.get(i);
                int numVenta = i + 1;
    
                System.out.printf("%-9s%-17s%-17s%-17s%-24s\n", numVenta, venta.getNombre(), venta.getCantidad(),
                        venta.getVendible().getPrecio(), venta.getGananciasVenta());
            }
    
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    }

    public void eliminarProducto() {
        System.out.println("\n==== ELIMINAR PRODUCTO ====");
    
        System.out.print("\nIngrese el nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();
    
        // Se verifica si existe el producto utilizando búsqueda lineal
        int indiceProducto = busquedaLinealProducto(nombre);
    
        if (indiceProducto != -1) {
            Vendible productoEncontrado = vendibles.get(indiceProducto);
            vendibles.remove(indiceProducto);
            System.out.println("\nProducto '" + nombre + "' eliminado exitosamente.\n");
            System.out.println("------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("\nNo se encontró un producto con ese nombre en el inventario.\n");
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    }

    // Método de búsqueda lineal para encontrar el índice de un producto por nombre
    private int busquedaLinealProducto(String nombre) {
        for (int i = 0; i < vendibles.size(); i++) {
            Vendible vendible = vendibles.get(i);
            if (vendible.getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1; // Si no se encuentra el producto
    }

    // Método de ordenamiento por inserción para ordenar la lista de productos
    private void ordenamientoPorInsercion() {
        for (int i = 1; i < vendibles.size(); i++) {
            Vendible valorC = vendibles.get(i);
            int posicion = i;
            while ((posicion > 0) && (vendibles.get(posicion - 1).getNombre().compareToIgnoreCase(valorC.getNombre()) > 0)) {
                vendibles.set(posicion, vendibles.get(posicion - 1));
                posicion = posicion - 1;
            }
            vendibles.set(posicion, valorC);
        }
    }

    // Método para buscar el Vendible por nombre utilizando búsqueda lineal
    private Vendible buscarVendible(String nombre) {
        for (Vendible vendible : vendibles) {
            if (vendible.getNombre().equalsIgnoreCase(nombre)) {
                return vendible;
            }
        }
        return null;
    }
    
    // Método para aplicar ordenamiento por inserción a la lista de ventas
    private void ordenamientoPorInsercionVentas(List<Venta> ventas) {
        for (int i = 1; i < ventas.size(); i++) {
            Venta ventaActual = ventas.get(i);
            int j = i - 1;
    
            while (j >= 0 && ventas.get(j).getGananciasVenta() > ventaActual.getGananciasVenta()) {
                ventas.set(j + 1, ventas.get(j));
                j--;
            }
    
            ventas.set(j + 1, ventaActual);
        }
    }

     // Método para verificar si la lista está ordenada
    private boolean estaOrdenada(List<Vendible> vendibles) {
        for (int i = 1; i < vendibles.size(); i++) {
            if (vendibles.get(i - 1).getNombre().compareToIgnoreCase(vendibles.get(i).getNombre()) > 0) {
                return false;
            }
        }
        return true;
    }
    // Método de ordenamiento por inserción
    private void ordenamientoPorInsercion(List<Vendible> vendibles) {
        for (int i = 1; i < vendibles.size(); i++) {
            Vendible valorC = vendibles.get(i);
            int posicion = i;
            while ((posicion > 0)
                    && (vendibles.get(posicion - 1).getNombre().compareToIgnoreCase(valorC.getNombre()) > 0)) {
                vendibles.set(posicion, vendibles.get(posicion - 1));
                posicion = posicion - 1;
            }
            vendibles.set(posicion, valorC);
        }
    }

}