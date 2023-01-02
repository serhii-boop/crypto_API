package com.oril.cryptoapp.util;

import com.oril.cryptoapp.entity.request.CsvExportRq;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CsvExporter {

    public void export(List<CsvExportRq> currencyList, HttpServletResponse httpServletResponse) throws IOException {
        setResponseHeader(httpServletResponse,"text/csv", ".csv");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(httpServletResponse.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Cryptocurrency", "Min price", "Max price"};
        String[] fieldMapping = {"name", "minPrice", "maxPrice"};
        csvWriter.writeHeader(csvHeader);

        for (CsvExportRq token : currencyList) {
            csvWriter.write(token, fieldMapping);
        }

        csvWriter.close();
    }

    public void setResponseHeader(HttpServletResponse httpServletResponse, String contentType, String extension) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        String fileName = "crypto_prices_" + timestamp + extension;

        httpServletResponse.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        httpServletResponse.setHeader(headerKey, headerValue);
    }
}
