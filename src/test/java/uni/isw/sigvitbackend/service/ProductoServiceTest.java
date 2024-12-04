package uni.isw.sigvitbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import uni.isw.sigvitbackend.dto.ProductoRequest;
import uni.isw.sigvitbackend.dto.ProductoResponse;
import uni.isw.sigvitbackend.model.Categoria;
import uni.isw.sigvitbackend.model.Producto;
import uni.isw.sigvitbackend.model.Proveedor;
import uni.isw.sigvitbackend.repository.CategoriaRepository;
import uni.isw.sigvitbackend.repository.ProductoRepository;
import uni.isw.sigvitbackend.repository.ProveedorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @Mock
    private CategoriaRepository categoriaRepository;


    private Producto producto;
    private Proveedor proveedor;
    private Categoria categoria;
    private ProductoRequest productoRequest;

    @BeforeEach
    public void setUp() {
        proveedor = new Proveedor();
        proveedor.setRuc(12345678901L);

        categoria = new Categoria();
        categoria.setIdCategoria(1);

        producto = new Producto();
        producto.setIdProducto(2);
        producto.setNombre("Test Producto");
        producto.setDescripcion("Test Descripcion");
        producto.setPrecioVenta(100.0);
        producto.setPrecioCompra(80.0);
        producto.setStock(50);
        producto.setImagen("test-image.jpg");
        producto.setProveedor(proveedor);
        producto.setCategoria(categoria);

        productoRequest = new ProductoRequest();
        productoRequest.setIdProducto(2);
        productoRequest.setNombre("Test Producto");
        productoRequest.setDescripcion("Test Descripcion");
        productoRequest.setPrecioVenta(100.0);
        productoRequest.setPrecioCompra(80.0);
        productoRequest.setStock(50);
        productoRequest.setImagen("test-image.jpg");
        productoRequest.setRucProveedor(12345678901L);
        productoRequest.setIdCategoria(1);
    }

    @Test
    public void testListProductos() {
        List<Producto> mockProductos = new ArrayList<>();
        mockProductos.add(producto);

        when(productoRepository.findAll()).thenReturn(mockProductos);

        List<ProductoResponse> list = productoService.listProductos();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    public void testFindProducto() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        ProductoResponse result = productoService.findProducto(1L);

        assertNotNull(result);
        assertEquals("Test Producto", result.getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }


    @Test
    public void testInsertProducto() {
        when(proveedorRepository.findById(12345678901L)).thenReturn(Optional.of(proveedor));
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoResponse result = productoService.insertProducto(productoRequest);

        assertNotNull(result);
        assertEquals("Test Producto", result.getNombre());
        verify(proveedorRepository, times(1)).findById(12345678901L);
        verify(categoriaRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    // to fix
    // @Test
    // public void testInsertProducto_ProveedorNotFound() {
    //     when(proveedorRepository.findById(12345678901L)).thenReturn(Optional.empty());

    //     ProductoResponse result = productoService.insertProducto(productoRequest);

    //     assertNotNull(result);
    //     assertNull(result.getNombre());
    //     verify(proveedorRepository, times(1)).findById(12345678901L);
    // }

    @Test
    public void testUpdateProducto() {
        when(proveedorRepository.findById(12345678901L)).thenReturn(Optional.of(proveedor));
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoResponse result = productoService.updateProducto(productoRequest);

        assertNotNull(result);
        assertEquals("Test Producto", result.getNombre());
        verify(proveedorRepository, times(1)).findById(12345678901L);
        verify(categoriaRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    // to fix
    // @Test
    // public void testUpdateProducto_CategoriaNotFound() {
    //     when(proveedorRepository.findById(12345678901L)).thenReturn(Optional.of(proveedor));
    //     when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

    //     ProductoResponse result = productoService.updateProducto(productoRequest);

    //     assertNotNull(result);
    //     assertNull(result.getNombre());
    //     verify(proveedorRepository, times(1)).findById(12345678901L);
    //     verify(categoriaRepository, times(1)).findById(1L);
    // }

    @Test
    public void testDeleteProducto() {
        doNothing().when(productoRepository).deleteById(1L);

        productoService.deleteProducto(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }
}