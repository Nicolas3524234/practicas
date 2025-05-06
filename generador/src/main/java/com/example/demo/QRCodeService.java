package com.example.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.security.SecureRandom;

@Service
public class QRCodeService {

    public byte[] generateDynamicQRCode(int width, int height) throws WriterException, IOException {
    	
    	SecureRandom rand = new SecureRandom();
    	
    	int cod = rand.nextInt();
    	if(cod<0) {
    		cod = cod * -1;
    	}
    	System.out.println(cod);
    	
        String baseUrl = Integer.toString(cod);
        String auxiliar = "#r=" + UUID.randomUUID(); // Esto cambia la imagen pero no afecta la navegación

        String url = baseUrl + auxiliar;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
            return stream.toByteArray();
        }
    }
}
