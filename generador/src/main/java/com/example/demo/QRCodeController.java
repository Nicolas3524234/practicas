package com.example.demo;

import com.google.zxing.WriterException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/")
    public ResponseEntity<byte[]> getDynamicQR() {
        try {
            byte[] qrImagen = qrCodeService.generateDynamicQRCode(250, 250);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qr.png\"")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrImagen);
        } catch (WriterException | IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}