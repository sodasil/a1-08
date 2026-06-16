package br.univali.prettyflights.checkin.api;

import br.univali.prettyflights.checkin.domain.ServicoCheckin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
public class CheckinController {

    private final ServicoCheckin servicoCheckin;

    public CheckinController(ServicoCheckin servicoCheckin) {
        this.servicoCheckin = servicoCheckin;
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmarCheckin(@RequestBody CheckinRequestDTO request) {
        String qrCode = servicoCheckin.processarCheckin(request);
        return ResponseEntity.ok(qrCode);
    }
}