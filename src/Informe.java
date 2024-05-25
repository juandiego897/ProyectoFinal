import java.util.List;
public class Informe {
    public void generarInformeVentas(List<Venta> ventas) {
        System.out.println("Informe de Ventas:");
        ventas.forEach(venta -> System.out.println(venta));
    }
    public void generarInformeInventario(List<Producto> productos) {
        System.out.println("Informe de Inventario:");
        productos.forEach(producto -> System.out.println(producto));
    }
}
