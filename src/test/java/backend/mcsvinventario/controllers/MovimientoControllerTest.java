package backend.mcsvinventario.controllers;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.MovimientoDtoResponse;
import backend.mcsvinventario.services.MovimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovimientoController.class)
class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovimientoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegistrarMovimiento() throws Exception {
        // Arrange
        MovimientoDtoRequest dto = new MovimientoDtoRequest(1, 1, "ENTRADA");
        String json = objectMapper.writeValueAsString(dto);
        MovimientoDtoResponse response = new MovimientoDtoResponse(1, 1, 1, "ENTRADA", "2025-01-01");

        when(service.registrarMovimiento(any(MovimientoDtoRequest.class))).thenReturn(response);
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movimiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        // Assert
    }
}