package id.bootcamp.generatepdf.service;

import com.lowagie.text.DocumentException;
import id.bootcamp.generatepdf.dto.DownloadPdfDto;
import id.bootcamp.generatepdf.dto.OrderHeaderDto;
import id.bootcamp.generatepdf.dummies.DummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DownloadPdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public OrderHeaderDto getReceiptData() {
        //ambil dari dummy data,
        //jika udah bisa ambil dari db, nanti diupdate ya...
        return DummyData.getDummyData();
    }

    public DownloadPdfDto downloadReceipt(Long userId) {
        //Receipt Data
        OrderHeaderDto receiptData = getReceiptData();

        //Path Lokasi folder pdf
        String path = new FileSystemResource("").getFile().getAbsolutePath();
        path += "\\uploads\\pdf\\";

        //Path baru dengan nama file
        String fileName = "receipt-" + userId + ".pdf";
        Path newPath = Paths.get(path + fileName);

        return generateReceiptPdf(newPath.toString(), receiptData, fileName);
    }

    public DownloadPdfDto generateReceiptPdf(String path, OrderHeaderDto receiptData, String fileName) {
        Context context = new Context();
        context.setVariable("orderHeader", receiptData);

        DownloadPdfDto downloadPdfDto = new DownloadPdfDto();

        try {
            String htmlContent = templateEngine.process("pdf-templates/receipt", context);

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();

            String pdfLocation = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/pdf/").path(fileName).toUriString();
            downloadPdfDto.setDownloadUrl(pdfLocation);
        } catch (DocumentException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        return downloadPdfDto;
    }
}
