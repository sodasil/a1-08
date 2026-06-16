package br.univali.prettyflights.checkin.api;

import br.univali.prettyflights.checkin.domain.ServicoCheckin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CheckinControllerIT.FakeApp.class)
@AutoConfigureMockMvc
@DisplayName("Testes de Integração - API de Check-in (PrettyFlights)")
class CheckinControllerIT {

    @SpringBootApplication
    static class FakeApp {} 

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicoCheckin servicoCheckinMock;

    @Test
    @DisplayName("Deve retornar 200 OK com o QR Code gerado")
    void deveConfirmarCheckinGerarQrCodeEDispararBagagem() throws Exception {
        
        String qrCodeEsperado = "QR_CODE_PREFIX_123456_ASSENTO_12A";
        when(servicoCheckinMock.processarCheckin(any())).thenReturn(qrCodeEsperado);

        String payloadJson = """
            {
                "localizador": "ABC1234",
                "sobrenome": "Silva",
                "assentoEscolhido": "12A",
                "quantidadeBagagens": 1
            }
            """;

        mockMvc.perform(post("/api/checkin/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andExpect(status().isOk())
                .andExpect(content().string(qrCodeEsperado)); 

        verify(servicoCheckinMock, times(1)).processarCheckin(any(CheckinRequestDTO.class));
    }
}