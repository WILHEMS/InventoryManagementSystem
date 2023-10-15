package com.example.inventorymanagementservice.components.business.services;

import com.example.inventorymanagementservice.components.persistence.entities.Stock;
import com.example.inventorymanagementservice.components.persistence.repositories.StockRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockReportService {
    private StockRepository stockRepository;
    @Autowired
    public StockReportService ( StockRepository stockRepository){
        this.stockRepository  = stockRepository;
    }
    public  String exportStockReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Public\\Documents\\";
        List<Stock> users = stockRepository.findAll();
        File userFile = ResourceUtils.getFile("classpath:JasperReports/StockReport/StockReport.jrxml");
        JasperDesign jasperDesign;
        JasperReport stockJasperReport = JasperCompileManager.compileReport(userFile.getAbsolutePath());
        JRBeanCollectionDataSource userDataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> createdBy = new HashMap<>();
        createdBy.put("Created By", "KNS");
        JasperPrint jasperPrint = JasperFillManager.fillReport(stockJasperReport, createdBy, userDataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\stockReport.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\stockReport.pdf");
        }
        return "Report generated in path: "+ path;
    }
}
