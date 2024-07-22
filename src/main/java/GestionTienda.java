import models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GestionTienda {

    public static void aplicacion()  {
        ArrayList<Producto> productos = new ArrayList<>();

        boolean salir = false;

        do {
            switch (menu()) {
                case 1:
                    try{
                        crearProducto(productos);
                    }catch(Exception e){
                        System.out.println("Error: "+e.getMessage());
                    }
                    break;
                case 2:
                    ordenarProductos(productos);
                    break;
                case 3:
                    eliminarProducto(productos);
                    break;
                case 4:
                    calcularFechaGarantia(productos);
                    break;
                case 5:
                    rebajarProducto(productos);
                    break;
                case 6:
                    mostrarProductosRebajados(productos);
                    break;
                case 7:
                    mostrarStock(productos);
                    break;
                case 8:
                    aumentarStock(productos);
                    break;
                case 9:
                    quitarStock(productos);
                    break;
                case 0:
                    System.out.println("Gracias por utilizar la aplicación.");
                    salir = true;
                    break;
            }
        } while (!salir);
    }

    public static byte menu() {
        Scanner sc = new Scanner(System.in);
        byte opcion;
        final byte MINIMO = 0;
        final byte MAXIMO = 9;

        do {
            System.out.println("\nMENú PRINCIPAL");
            System.out.println("1. Crear producto.");
            System.out.println("2. Listar productos ordenados de menor a mayor precio.");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Calcular fecha vencimiento de la garantía.");
            System.out.println("5. Aplicar rebajas a productos");
            System.out.println("6. Productos en rebajas.");
            System.out.println("7. Consultar stock de un producto.");
            System.out.println("8. Aumentar stock de un producto");
            System.out.println("9. Quitar stock de un producto");
            System.out.println("0. Salir de la aplicación \n");
            opcion = sc.nextByte();
            if (opcion < MINIMO || opcion > MAXIMO) {
                System.out.println("Escoje una opción válida");
            }
        } while (opcion < MINIMO || opcion > MAXIMO);
        return opcion;
    }

    public static void crearProducto(ArrayList<Producto> productos) throws Exception {
        try {
            String nombre = pedirNombre();
            String marca = pedirMarca();
            double precio = pedirPrecio();
            int stock = pedirStock();
            LocalDate fechaCompra = pedirFecha();
            int porcentaje = pedirPorcentajeRebaja();
            byte opcion = pedirTipoProducto();
            switch (opcion) {
                case 1:
                    int talla = pedirTalla();
                    String tipoTejido = pedirTipoTejido();
                    String tipoPrenda = pedirTipoPrenda();
                    Ropa ropa = new Ropa(nombre, marca, precio, stock
                            , fechaCompra, porcentaje, talla, tipoTejido, tipoPrenda);
                    insertarProducto(productos, ropa);
                    break;
                case 2:
                    int consumo = pedirConsumo();
                    LocalDate fechaFabricacion = pedirFechaFabricacion();
                    int capacidadCarga = pedirCapacidadCarga();
                    Electrodomestico electrodomestico = new Electrodomestico(nombre, marca, precio, stock, fechaCompra
                            , porcentaje, consumo, fechaFabricacion, capacidadCarga);
                    insertarProducto(productos, electrodomestico);
                    break;
                case 3:
                    int resolucionImagen = pedirResolucionImagen();
                    int capacidadBateria = pedirCapacidadBateria();
                    ComponenteElectronico componenteElectronico = new ComponenteElectronico(nombre, marca, precio
                            , stock, fechaCompra, porcentaje, resolucionImagen, capacidadBateria);
                    insertarProducto(productos, componenteElectronico);
                    break;
                case 4:
                    boolean esVegano = saberEsVegano();
                    String temporadaUso = pedirTemporada();
                    Belleza belleza = new Belleza(nombre, marca, precio, stock, fechaCompra, porcentaje, esVegano
                            , temporadaUso);
                    insertarProducto(productos, belleza);
                    break;
                default:
                    throw new Exception("Caracter inválido, introduzca un valor entre el 1 y el 4.");
            }
        } catch (ProductoYaRegistradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Producto buscarProducto(ArrayList<Producto> productos, int codigo) throws ProductoNoEncontradoException {
        return productos.stream().filter(p -> p.getCodigoProductoPropio() == codigo).findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException("El producto no está registrado en el sistema."));
    }

    public static String pedirNombre() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre del producto:");
        String nombre = sc.nextLine();
        return nombre;
    }

    public static String pedirMarca() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la marca del producto:");
        String marca = sc.nextLine();
        return marca;
    }

    public static double pedirPrecio() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el precio del producto:");
        double precio = sc.nextDouble();
        return precio;
    }

    public static int pedirStock() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el stock del producto:");
        int stock = sc.nextInt();
        sc.nextLine();
        return stock;
    }

    public static LocalDate pedirFecha() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la fecha de compra del producto: (aaaaa-mm-dd)");
        String fechaCompraString = sc.nextLine();
        LocalDate fechaCompra = LocalDate.parse(fechaCompraString);
        return fechaCompra;
    }

    public static int pedirPorcentajeRebaja() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el porcentaje de rebaja del producto:");
        int porcentaje = sc.nextInt();
        sc.nextLine();
        return porcentaje;
    }

    public static byte pedirTipoProducto() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Qué tipo de producto deseas añadir? (1. Ropa, 2. Electrodoméstico, " +
                "3. Componente Electrónico, 4. Belleza) :");
        byte opcion = sc.nextByte();
        return opcion;
    }

    public static int pedirTalla() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la talla del producto:");
        int talla = sc.nextInt();
        sc.nextLine();
        return talla;
    }

    public static String pedirTipoTejido() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el tipo de tejido del producto:");
        String tipoTejido = sc.nextLine();
        return tipoTejido;
    }

    public static String pedirTipoPrenda() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el tipo de prenda del producto:");
        String tipoPrenda = sc.nextLine();
        return tipoPrenda;
    }

    public static int pedirConsumo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el consumo eléctrico del producto:");
        int consumo = sc.nextInt();
        sc.nextLine();
        return consumo;
    }

    public static LocalDate pedirFechaFabricacion() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la fecha de fabricación del producto:");
        String fechaFabricacionString = sc.nextLine();
        LocalDate fechaFabricacion = LocalDate.parse(fechaFabricacionString);
        return fechaFabricacion;
    }

    public static int pedirCapacidadCarga() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la capacidad de carga del producto:");
        int capacidad = sc.nextInt();
        sc.nextLine();
        return capacidad;
    }

    public static int pedirResolucionImagen() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la resolución de imagen del producto:");
        int resolucion = sc.nextInt();
        return resolucion;
    }

    public static int pedirCapacidadBateria() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la capacidad de batería del producto:");
        int capacidadBateria = sc.nextInt();
        sc.nextLine();
        return capacidadBateria;
    }

    public static boolean saberEsVegano() {
        Scanner sc = new Scanner(System.in);

        boolean esVegano = false;
        boolean esValido = false;
        String respuesta = " ";
        do {
            try {
                System.out.println("El producto es vegano? (S para Si, N para No");
                respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("S")) {
                    esVegano = true;
                    esValido = true;
                } else if (respuesta.equalsIgnoreCase("N")) {
                    esVegano = false;
                    esValido = true;
                } else {
                    throw new Exception("Carácter inválido, por favor introduzca 'S' o 'N':");
                }

            } catch (Exception eBoolean) {
                System.out.println(eBoolean.getMessage());
            }
        } while (!esValido);
        return esVegano;
    }

    public static String pedirTemporada() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la temporada en la que usarás el producto:");
        String temporada = sc.nextLine();
        return temporada;
    }

    public static void insertarProducto(ArrayList<Producto> productos, Producto producto) {
        productos.add(producto);
        System.out.println("El producto con código: " + producto.getCodigoProductoPropio() + " añadido con éxito.");
    }

    public static void eliminarProductoArray(ArrayList<Producto> productos, Producto producto) {
        productos.remove(producto);
        System.out.println("El producto se ha eliminado con éxito.");
    }

    public static void ordenarProductos(ArrayList<Producto> productos) {
        productos.stream().sorted(Comparator.comparing(Producto::getPrecio)).forEach(p
                -> System.out.println(p.getNombre() + " - " + p.getPrecio()+" €."));
    }

    public static void eliminarProducto(ArrayList<Producto> productos) {
        try {
            int codigo = pedirCodigoProducto();
            Producto producto = buscarProducto(productos, codigo);
            if (producto != null) {
                eliminarProductoArray(productos, producto);
            } else {
                throw new ProductoNoEncontradoException("El producto no está registrado en el sistema");
            }

        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int pedirCodigoProducto() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el código del producto:");
        int codigo = sc.nextInt();
        return codigo;
    }

    public static void calcularFechaGarantia(ArrayList<Producto> productos) {
        try {
            int codigo = pedirCodigoProducto();
            Producto producto = buscarProducto(productos, codigo);
            if (producto != null) {
                LocalDate fechaCompra = pedirFecha();
                LocalDate fechaVencimiento = producto.calcularFechaVencimientoGarantia(fechaCompra);
                System.out.println("La fecha de vencimiento de la garantía es: " + fechaVencimiento);
            } else {
                throw new ProductoNoEncontradoException("El producto no está registrado en el sistema.");
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void rebajarProducto(ArrayList<Producto> productos) {
        try {
            int codigo = pedirCodigoProducto();
            Producto producto = buscarProducto(productos, codigo);
            if (producto != null) {
                double precioRebajado = producto.aplicarRebaja();
                System.out.println("El producto rebajado tiene un precio de: " + precioRebajado + " €.");
            } else {
                throw new ProductoNoEncontradoException("El producto no está registrado en el sistema.");
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarProductosRebajados(ArrayList<Producto> productos) {
    }

    public static void mostrarStock(ArrayList<Producto> productos){
        try {
            int codigo = pedirCodigoProducto();
            Producto producto = buscarProducto(productos, codigo);
            if (producto != null) {
                System.out.println("Stock del producto: "+producto.getStock());
            } else {
                throw new ProductoNoEncontradoException("El producto no está registrado en el sistema.");
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void aumentarStock(ArrayList<Producto> productos){
        try {
            int codigo = pedirCodigoProducto();
            Producto producto = buscarProducto(productos, codigo);
            if (producto != null) {
                int cantidad = pedirCantidad();
                producto.incrementarStock(cantidad);
                System.out.println("Stock del producto: "+producto.getStock());
            } else {
                throw new ProductoNoEncontradoException("El producto no está registrado en el sistema.");
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void quitarStock(ArrayList<Producto> productos){
        try {
            int codigo = pedirCodigoProducto();
            Producto producto = buscarProducto(productos, codigo);
            if (producto != null) {
                int cantidad = pedirCantidad();
                producto.decrementarStock(cantidad);
                System.out.println("Stock del producto: "+producto.getStock());
            } else {
                throw new ProductoNoEncontradoException("El producto no está registrado en el sistema.");
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }

    public static int pedirCantidad(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la cantidad a aumentar o quitar:");
        int cantidad = sc.nextInt();
        return cantidad;
    }

}

