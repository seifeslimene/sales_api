package io.github.dougllasfps.salesapi.rest.sales;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.dougllasfps.salesapi.model.Sale;
import io.github.dougllasfps.salesapi.model.repository.ItemSaleRepository;
import io.github.dougllasfps.salesapi.model.repository.SaleRepository;
import io.github.dougllasfps.salesapi.service.SalesServiceReport;
import io.github.dougllasfps.salesapi.util.DateUtils;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SalesController {

    @Autowired
    private SaleRepository repository;
    @Autowired
    private ItemSaleRepository itemSaleRepository;
    @Autowired
    private SalesServiceReport salesServiceReport;

    @PostMapping
    @Transactional
    public void makeSale(@RequestBody Sale sale) {
        repository.save(sale);
        sale.getItems().stream().forEach(iv -> iv.setSale(sale));
        itemSaleRepository.saveAll(sale.getItems());
    }

    @GetMapping("/sales-report")
    public ResponseEntity<byte[]> reportSales(
            @RequestParam(value = "id", required = false, defaultValue = "") Long id,
            @RequestParam(value = "start", required = false, defaultValue = "") String start,
            @RequestParam(value = "end", required = false, defaultValue = "") String end
    ) {
        Date dateStart = DateUtils.fromString(start);
        Date dateEnd = DateUtils.fromString(end, true);

        if (dateStart == null) {
            dateStart = DateUtils.DEFAULT_START_DATE;
        }

        if (dateEnd == null) {
            dateEnd = DateUtils.today(true);
        }

        var reportGenerated = salesServiceReport.generateReport(id, dateStart, dateEnd);
        var headers = new HttpHeaders();
        var fileName = "sales-report.pdf";
        headers.setContentDispositionFormData("inline; filename=\"" + fileName + "\"", fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        var responseEntity = new ResponseEntity<>(reportGenerated, headers, HttpStatus.OK);
        return responseEntity;
    }
}
