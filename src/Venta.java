import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Venta {
    private int idVenta;
    private List<Producto> productosVendidos;
    private List<Integer> cantidadesVendidas;
    private double totalVenta;
    private Date fechaHora;

    public Venta(int idVenta) {
        this.idVenta = idVenta;
        this.productosVendidos = new ArrayList<>();
        this.cantidadesVendidas = new ArrayList<>();
        this.totalVenta = 0.0;
        this.fechaHora = new Date();
    }

    public int getIdVenta() {
        return idVenta;
    }

    public List<Producto> getProductosVendidos() {
        return productosVendidos;
    }

    public List<Integer> getCantidadesVendidas() {
        return cantidadesVendidas;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        this.productosVendidos.add(producto);
        this.cantidadesVendidas.add(cantidad);
        producto.actualizarCantidadEnStock(-cantidad);
        calcularTotalVenta();
    }
    public void calcularTotalVenta() {
        totalVenta = 0.0; // Reiniciamos el total
        for (int i = 0; i < productosVendidos.size(); i++) {
            Producto producto = productosVendidos.get(i);
            int cantidad = cantidadesVendidas.get(i);
            totalVenta += producto.getPrecio() * cantidad;
        }
    }
    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", totalVenta=" + totalVenta +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
