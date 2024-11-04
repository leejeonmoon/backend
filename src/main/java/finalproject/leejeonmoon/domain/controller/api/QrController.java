package finalproject.leejeonmoon.domain.controller.api;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import finalproject.leejeonmoon.domain.dto.response.DeviceInfoDto;
import finalproject.leejeonmoon.domain.service.DeviceService;
import finalproject.leejeonmoon.domain.service.QrService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QrController {
    private final QrService qrService;
    private final DeviceService deviceService;

    @GetMapping("/qr")
    public Object createQr() throws WriterException, IOException {
        int width = 200;
        int height = 200;

        String url = qrService.getQrUrl();
//        String url = "http://localhost:8080/qrcode/";
        BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            MatrixToImageWriter.writeToStream(matrix, "PNG", out);
            String qrCodeUrl = "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(out.toByteArray());
            qrService.updateMemberQrCode(qrCodeUrl);
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(out.toByteArray());
        }
    }

    @PostMapping("/qr/register")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceInfoDto deviceInfo) {
        deviceService.registerDevice(deviceInfo);
        return ResponseEntity.ok().build();
    }
}
