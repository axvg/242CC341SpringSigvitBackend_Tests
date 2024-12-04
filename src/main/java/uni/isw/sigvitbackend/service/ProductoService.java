package uni.isw.sigvitbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigvitbackend.dto.ProductoRequest;
import uni.isw.sigvitbackend.dto.ProductoResponse;
import uni.isw.sigvitbackend.model.Categoria;
import uni.isw.sigvitbackend.model.Producto;
import uni.isw.sigvitbackend.model.Proveedor;
import uni.isw.sigvitbackend.repository.CategoriaRepository;
import uni.isw.sigvitbackend.repository.ProductoRepository;
import uni.isw.sigvitbackend.repository.ProveedorRepository;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    
    public List<ProductoResponse> listProductos(){
        return ProductoResponse.fromEntities(productoRepository.findAll());
    }
    public ProductoResponse findProducto(Long id){
        return ProductoResponse.fromEntity(productoRepository.findById(id).get());
    }
    public ProductoResponse insertProducto(ProductoRequest productoRequest){
        Proveedor proveedor = proveedorRepository.findById(productoRequest.getRucProveedor()).get();
        if(proveedor == null) return new ProductoResponse();
        
        long idCat = productoRequest.getIdCategoria();
        Categoria categoria = categoriaRepository.findById(idCat).get();
        if(categoria == null) return new ProductoResponse();
        
        Producto producto = new Producto(
                productoRequest.getIdProducto(),
                productoRequest.getDescripcion(),
                productoRequest.getNombre(),
                productoRequest.getPrecioVenta(),
                productoRequest.getPrecioCompra(),
                productoRequest.getStock(),
                productoRequest.getImagen(),
                proveedor,
                categoria
                
        );
        
        productoRepository.save(producto);
        ProductoResponse pr = ProductoResponse.fromEntity(producto);
        return pr;
    }
    public ProductoResponse updateProducto(ProductoRequest productoRequest){
        long rucProv = productoRequest.getRucProveedor();
        Proveedor proveedor = proveedorRepository.findById(rucProv).get();
        if(proveedor == null)
            return new ProductoResponse();
        
        long idCate = productoRequest.getIdCategoria();
        Categoria categoria = categoriaRepository.findById(idCate).get();
        if(categoria == null)
            return new ProductoResponse();
        
        Producto producto = new Producto(
                productoRequest.getIdProducto(),
                productoRequest.getDescripcion(),
                productoRequest.getNombre(),
                productoRequest.getPrecioVenta(),
                productoRequest.getPrecioCompra(),
                productoRequest.getStock(),
                productoRequest.getImagen(),
                proveedor,
                categoria
        );
        
        producto = productoRepository.save(producto);
        ProductoResponse productoResponse = ProductoResponse.fromEntity(producto);
        return productoResponse;
    }
    public void deleteProducto(Long id){
        productoRepository.deleteById(id);
    }
}
