package id.bootcamp.generatepdf.rest;

import id.bootcamp.generatepdf.dto.DownloadPdfDto;
import id.bootcamp.generatepdf.service.DownloadPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class DownloadPdfRest {

    @Autowired
    DownloadPdfService dps;

    @GetMapping("/receiptPdf")
    public DownloadPdfDto downloadReceiptPdf() {
        DownloadPdfDto data = dps.downloadReceipt(1L);
        return data;
    }


}
