package uni.isw.sigvitbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import uni.isw.sigvitbackend.dto.ProductoRequest;
import uni.isw.sigvitbackend.dto.ProductoResponse;
import uni.isw.sigvitbackend.service.ProductoService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ProductoService productoService;

        private ProductoRequest productoRequest;
        private ProductoResponse productoResponse, productoResponse2, productoResponse3;

        @BeforeEach
        public void init() {
        productoRequest = new ProductoRequest();
        productoRequest.setIdProducto(1);
        productoRequest.setNombre("Producto 1");
        productoRequest.setDescripcion("Descripcion 1");
        productoRequest.setPrecioVenta(100.0);
        productoRequest.setPrecioCompra(80.0);
        productoRequest.setStock(50);
        productoRequest.setImagen("imagen1.jpg");
        productoRequest.setRucProveedor(12345678901L);
        productoRequest.setIdCategoria(2);

        productoResponse = new ProductoResponse();
        productoResponse.setIdProducto(1);
        productoResponse.setNombre("Producto 1");
        productoResponse.setDescripcion("Descripcion 1");
        productoResponse.setPrecioVenta(100.0);
        productoResponse.setStock(50);
        productoResponse.setImagen("imagen1.jpg");

        productoResponse2 = new ProductoResponse();
        productoResponse2.setIdProducto(22);
        productoResponse2.setNombre("Producto 2");
        productoResponse2.setDescripcion("Descripcion 2");
        productoResponse2.setPrecioVenta(200.0);
        productoResponse2.setStock(30);
        productoResponse2.setImagen("imagen2.jpg");

        productoResponse3 = new ProductoResponse();
        productoResponse3.setIdProducto(3);
        productoResponse3.setNombre("Producto 3");
        productoResponse3.setDescripcion("Descripcion 3");
        productoResponse3.setPrecioVenta(300.0);
        productoResponse3.setStock(20);
        productoResponse3.setImagen("imagen3.jpg");
        }

        @Test
        public void testInsertProducto() throws Exception {
        when(productoService.insertProducto(productoRequest)).thenReturn(productoResponse);

        ResultActions response = mockMvc.perform(post("/api/v1/producto/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(productoRequest)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", CoreMatchers.is(productoResponse.getNombre())));
        }

        @Test
        public void testDeleteProducto() throws Exception {
        given(productoService.findProducto(ArgumentMatchers.anyLong())).willReturn(productoResponse);
        doNothing().when(productoService).deleteProducto(ArgumentMatchers.anyLong());

        ResultActions response = mockMvc.perform(delete("/api/v1/producto/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(productoRequest)));

        response.andExpect(status().isOk());
        }

        // to fix
        // @Test
        // public void testDeleteProductoWithEmptyOptional() throws Exception {
        // ResultActions response = mockMvc.perform(delete("/api/v1/producto/delete")
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .content((new ObjectMapper()).writeValueAsString(Optional.empty())));

        // response.andExpect(status().isBadRequest());
        // }

        @Test
        public void testUpdateProducto() throws Exception {
        given(productoService.findProducto(ArgumentMatchers.anyLong())).willReturn(productoResponse);

        productoRequest.setNombre("Producto 1");

        when(productoService.updateProducto(productoRequest)).thenReturn(productoResponse);

        ResultActions response = mockMvc.perform(put("/api/v1/producto/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(productoRequest)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", CoreMatchers.is(productoRequest.getNombre())));
        }

        @Test
        public void testGetProductos() throws Exception {
        when(productoService.listProductos()).thenReturn(Arrays.asList(productoResponse2, productoResponse3));

        ResultActions response = mockMvc.perform(get("/api/v1/producto")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(2)))
                .andExpect(jsonPath("$[0].nombre", CoreMatchers.is(productoResponse2.getNombre())));
        }

        @Test
        public void testFindProductoById() throws Exception {
        when(productoService.findProducto(1L)).thenReturn(productoResponse);

        ResultActions response = mockMvc.perform(get("/api/v1/producto/find")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(productoRequest)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", CoreMatchers.is(productoResponse.getNombre())));
        }

        // to fix
        // @Test
        // public void testFindProductoByIdWithEmptyOptional() throws Exception {
        //         ResultActions response = mockMvc.perform(get("/api/v1/producto/find")
        //                 .contentType(MediaType.APPLICATION_JSON)
        //                 .content((new ObjectMapper()).writeValueAsString(Optional.empty())));
        //         response.andExpect(status().isBadRequest());
        // }
}