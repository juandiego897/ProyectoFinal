import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Producto> productos = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Venta> ventas = new ArrayList<>();
    private static Informe informe = new Informe();
    private static int nextProductoId = 1;
    private static int nextVentaId = 1;

    public static void main(String[] args) {
        inicializarUsuariosPredeterminados();
        while (true) {
            mostrarMenuPrincipal();
        }
    }

    private static void inicializarUsuariosPredeterminados() {
        usuarios.add(new Usuario("juan diego", "44223366", "administrador"));
        usuarios.add(new Usuario("mahicol", "33333333", "vendedor"));
        usuarios.add(new Usuario("christian", "87654321", "gerente"));
    }

    private static void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al Sistema de Gestión de Inventario");

        System.out.print("Ingrese nombre de usuario(si no recuerda su contraseña esta en trello): ");
        String username = scanner.nextLine();
        if (!username.matches("[a-zA-Z ]+")) {
            System.out.println("El nombre de usuario solo puede contener letras.");
            return;
        }

        System.out.print("Ingrese contraseña del usuario(esta en trello): ");
        String password = scanner.nextLine();
        if (!password.matches("\\d{1,8}")) {
            System.out.println("La contraseña solo puede contener hasta 8 números.");
            return;
        }

        Usuario usuario = autenticarUsuario(username, password);
        if (usuario != null) {
            mostrarMenuPorRol(usuario);
        } else {
            System.out.println("usuario o contraseña incorrecto. Intente nuevamente.");
        }
    }

    private static Usuario autenticarUsuario(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreDeUsuario().equals(username) && usuario.verificarContraseña(password)) {
                return usuario;
            }
        }
        return null;
    }

    private static void mostrarMenuPorRol(Usuario usuario) {
        switch (usuario.getRol()) {
            case "administrador":
                mostrarMenuAdministrador();
                break;
            case "vendedor":
                mostrarMenuVendedor();
                break;
            case "gerente":
                mostrarMenuGerente();
                break;
            default:
                System.out.println("Rol no reconocido. Saliendo del sistema.");
        }
    }

    private static void mostrarMenuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú Administrador:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Generar Informe de Ventas");
            System.out.println("5. Generar Informe de Inventario");
            System.out.println("6. Administrar Usuarios");
            System.out.println("7. Volver al Menú Principal");
            System.out.println("8. Salir Definitivamente");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    modificarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    generarInformeVentas();
                    break;
                case 5:
                    generarInformeInventario();
                    break;
                case 6:
                    administrarUsuarios();
                    break;
                case 7:
                    return;
                case 8:
                    System.out.println("Saliendo definitivamente...");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuVendedor() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú Vendedor:");
            System.out.println("1. Ver Inventario");
            System.out.println("2. Realizar Venta");
            System.out.println("3. Volver al Menú Principal");
            System.out.println("4. Salir Definitivamente");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    verInventario();
                    break;
                case 2:
                    realizarVenta();
                    break;
                case 3:
                    return;
                case 4:
                    System.out.println("Saliendo definitivamente...");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuGerente() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú Gerente:");
            System.out.println("1. Ver Inventario");
            System.out.println("2. Generar Informe de Ventas");
            System.out.println("3. Volver al Menú Principal");
            System.out.println("4. Salir Definitivamente");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    verInventario();
                    break;
                case 2:
                    generarInformeVentas();
                    break;
                case 3:
                    return;
                case 4:
                    System.out.println("Saliendo definitivamente...");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine();
        if (buscarProductoPorId(idProducto) != null) {
            System.out.println("El ID del producto ya existe. Ingrese otro ID.");
            return;
        }

        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese descripción del producto: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese la cantidad que va añadir al stock: ");
        int cantidadEnStock = scanner.nextInt();

        Producto producto = new Producto(idProducto, nombre, descripcion, precio, cantidadEnStock);
        productos.add(producto);
        System.out.println("Producto agregado exitosamente.");
    }

    private static void modificarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto a cambiar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            System.out.print("Ingrese nuevo nombre del producto: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese nueva descripción del producto: ");
            String descripcion = scanner.nextLine();
            System.out.print("Ingrese nuevo precio del producto: ");
            double precio = scanner.nextDouble();
            System.out.print("Ingrese nueva cantidad en el stock: ");
            int cantidadEnStock = scanner.nextInt();

            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCantidadEnStock(cantidadEnStock);
            System.out.println("Producto modificado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = scanner.nextInt();

        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            productos.remove(producto);
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static Producto buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }
    private static void generarInformeVentas() {
        System.out.println("Informe de Ventas:");
        System.out.println("ID\tNombre\tDescripción\tPrecio\tCantidad Vendida");
        for (Venta venta : ventas) {
            for (int i = 0; i < venta.getProductosVendidos().size(); i++) {
                Producto producto = venta.getProductosVendidos().get(i);
                int cantidadVendida = venta.getCantidadesVendidas().get(i);
                System.out.println(producto.getId() + "\t"
                        + producto.getNombre() + "\t"
                        + producto.getDescripcion() + "\t"
                        + producto.getPrecio() + "\t"
                        + cantidadVendida);
            }
        }
    }

    private static void generarInformeInventario() {
        informe.generarInformeInventario(productos);
    }

    private static void administrarUsuarios() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAdministrar Usuarios:");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Modificar Usuario");
            System.out.println("3. Eliminar Usuario");
            System.out.println("4. Volver al Menú Principal");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    agregarUsuario();
                    break;
                case 2:
                    modificarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese nombre de usuario: ");
        String nombreDeUsuario = scanner.nextLine();
        if (!nombreDeUsuario.matches("[a-zA-Z ]+")) {
            System.out.println("El nombre de usuario solo puede contener letras.");
            return;
        }

        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.nextLine();
        if (!contraseña.matches("\\d{1,8}")) {
            System.out.println("La contraseña solo puede contener hasta 8 números.");
            return;
        }

        System.out.print("Ingrese rol (administrador, vendedor, gerente): ");
        String rol = scanner.nextLine();

        Usuario usuario = new Usuario(nombreDeUsuario, contraseña, rol);
        usuarios.add(usuario);
        System.out.println("Usuario agregado exitosamente.");
    }

    private static void modificarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese nombre de usuario a modificar: ");
        String nombreDeUsuario = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorNombreDeUsuario(nombreDeUsuario);
        if (usuario != null) {
            System.out.print("Ingrese nueva contraseña: ");
            String contraseña = scanner.nextLine();
            if (!contraseña.matches("\\d{1,8}")) {
                System.out.println("La contraseña solo puede contener hasta 8 números.");
                return;
            }

            System.out.print("Ingrese nuevo rol (administrador, vendedor, gerente): ");
            String rol = scanner.nextLine();

            usuario.setContraseña(contraseña);
            usuario.setRol(rol);
            System.out.println("Usuario modificado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void eliminarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese nombre de usuario a eliminar: ");
        String nombreDeUsuario = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorNombreDeUsuario(nombreDeUsuario);
        if (usuario != null) {
            usuarios.remove(usuario);
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static Usuario buscarUsuarioPorNombreDeUsuario(String nombreDeUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreDeUsuario().equals(nombreDeUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    private static void verInventario() {
        System.out.println("Inventario de Productos:");
        System.out.println("ID\tNombre\tDescripción\tPrecio\tStock");
        for (Producto producto : productos) {
            System.out.println(producto.getId() + "\t"
                    + producto.getNombre() + "\t"
                    + producto.getDescripcion() + "\t"
                    + producto.getPrecio() + "\t"
                    + producto.getCantidadEnStock());
        }
    }

    private static void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        Venta venta = new Venta(nextVentaId++);

        while (true) {
            System.out.print("Ingrese ID del producto a vender (digite 0 para finalizar): ");
            int idProducto = scanner.nextInt();
            if (idProducto == 0) break;

            Producto producto = buscarProductoPorId(idProducto);
            if (producto != null && producto.getCantidadEnStock() > 0) {
                System.out.print("Ingrese la cantidad a vender del producto: ");
                int cantidadAVenderProducto = scanner.nextInt();
                if (cantidadAVenderProducto <= producto.getCantidadEnStock()) {
                    venta.agregarProducto(producto, cantidadAVenderProducto);
                    producto.actualizarCantidadEnStock(-cantidadAVenderProducto);
                    System.out.println("Producto agregado a la venta.");
                } else {
                    System.out.println("No hay suficiente stock para vender esa cantidad.");
                }
            } else {
                System.out.println("Producto no encontrado o sin stock.");
            }
        }

        if (!venta.getProductosVendidos().isEmpty()) {
            ventas.add(venta);
            System.out.println("Venta realizada exitosamente. Total: " + venta.getTotalVenta());
        } else {
            System.out.println("No se realizó ninguna venta.");
        }
    }
}


