package uni.isw.sigvitbackend.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uni.isw.sigvitbackend.model.Categoria;
import uni.isw.sigvitbackend.model.Producto;
import uni.isw.sigvitbackend.model.Proveedor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Proveedor proveedor;
    private Categoria categoria;
    private Producto producto;

    @BeforeEach
    public void setUp() {
        proveedor = Proveedor.builder()
                .ruc(123456701L)
                .nombreEmpresa("Proveedor test")
                .direccion("Direccion test")
                .email("proveedor@test.com")
                .build();;
        proveedor = proveedorRepository.save(proveedor);

        categoria = Categoria.builder()
                .idCategoria(1)
                .nombre("Categoria Test")
                .descripcion("Descripcion Test")
                .build();
        categoria = categoriaRepository.save(categoria);

        producto = Producto.builder()
                .nombre("Producto test")
                .descripcion("Descripcion test")
                .precioVenta(100.0)
                .precioCompra(80.0)
                .stock(50)
                .imagen("imagen-test.jpg")
                .proveedor(proveedor)
                .categoria(categoria)
                .build();
        producto = productoRepository.save(producto);
    }

    @Test
    public void testFindById() {
        Optional<Producto> foundProducto = productoRepository.findById((long) producto.getIdProducto());
        assertTrue(foundProducto.isPresent());
        assertEquals(producto.getNombre(), foundProducto.get().getNombre());
    }

    @Test
    public void testSave() {
        Producto newProducto = Producto.builder()
                .nombre("Nuevo producto")
                .descripcion("Nueva descripcion")
                .precioVenta(200.0)
                .precioCompra(150.0)
                .stock(30)
                .imagen("nueva-imagen.jpg")
                .proveedor(proveedor)
                .categoria(categoria)
                .build();

        Producto savedProducto = productoRepository.save(newProducto);
        assertNotNull(savedProducto.getIdProducto());
        assertEquals(newProducto.getNombre(), savedProducto.getNombre());
    }

    @Test
    public void testDelete() {
        productoRepository.delete(producto);
        Optional<Producto> deletedProducto = productoRepository.findById((long) producto.getIdProducto());
        assertFalse(deletedProducto.isPresent());
    }

    @Test
    public void testFindAll() {
        Iterable<Producto> productos = productoRepository.findAll();
        assertNotNull(productos);
        assertTrue(productos.iterator().hasNext());
    }
}