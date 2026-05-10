package io.github.dougllasfps.salesapi.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class SalesServiceReport {

    @Value("classpath:reports/sales-report.jrxml")
    private Resource reportSalesSource;

    @Value("classpath:reports/sales-report.jasper")
    private Resource reportSalesCompiled;

    @Autowired
    private DataSource dataSource;

    public byte[] generateReport(Long idCustomer, Date dateStart, Date dateEnd) {
        try (
                Connection connection = dataSource.getConnection();
        ) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ID_CUSTOMER", idCustomer);
            parameters.put("DATE_START", dateStart);
            parameters.put("DATE_END", dateEnd);
            return JasperRunManager.runReportToPdf(
                    reportSalesCompiled.getInputStream(),
                    parameters,
                    connection);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] generateCompiledReport() {
        //try with resources
        try (
                Connection connection = dataSource.getConnection();
        ) {
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint print = JasperFillManager
                    .fillReport(reportSalesCompiled.getInputStream(),
                            parameters, connection);
            return JasperExportManager.exportReportToPdf(print);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] generatingReportCompiling() {
        //try with resources
        try (
                Connection connection = dataSource.getConnection();
        ) {

            JasperReport compiledReport = JasperCompileManager
                    .compileReport(reportSalesSource.getInputStream());
            Map<String, Object> parameters = new HashMap<>();

            JasperPrint print = JasperFillManager
                    .fillReport(compiledReport, parameters, connection);

            return JasperExportManager.exportReportToPdf(print);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
