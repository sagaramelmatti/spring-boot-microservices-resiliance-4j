package com.ledzer.report.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ledzer.report.dto.CustomerReportDTO;
import com.ledzer.report.dto.CustomerSalesReportDTO;

public class PDFGenerator {

	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static ByteArrayInputStream employeePDFReport(List<CustomerReportDTO> customers) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Customer Report Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(3);
			// Add PDF Table Header ->
			Stream.of("ID", "Name", "Contact No").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (CustomerReportDTO customer : customers) {
				PdfPCell idCell = new PdfPCell(new Phrase(customer.getId().toString()));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell firstNameCell = new PdfPCell(new Phrase(customer.getName()));
				firstNameCell.setPaddingLeft(4);
				firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(firstNameCell);

				PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(customer.getContactNo())));
				lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				lastNameCell.setPaddingRight(4);
				table.addCell(lastNameCell);
			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	public static ByteArrayInputStream customerSalesPDFReport(List<CustomerSalesReportDTO> customerReportDTOList) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Customer Sales Report Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(5);
			// Add PDF Table Header ->
			Stream.of("ID", "Invoice Name", "Invoice Date", "Invoice Total", "Invoice Tax").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (CustomerSalesReportDTO customerReportDTO : customerReportDTOList) {
				PdfPCell idCell = new PdfPCell(new Phrase(customerReportDTO.getId().toString()));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell nameCell = new PdfPCell(new Phrase(customerReportDTO.getName()));
				nameCell.setPaddingLeft(4);
				nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nameCell);

				PdfPCell invoiceDateCell = new PdfPCell(new Phrase(String.valueOf(customerReportDTO.getInvoiceDate())));
				invoiceDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				invoiceDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				invoiceDateCell.setPaddingRight(4);
				table.addCell(invoiceDateCell);
				
				PdfPCell invoiceTotalCell = new PdfPCell(new Phrase(String.valueOf(customerReportDTO.getInvoiceTotal())));
				invoiceTotalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				invoiceTotalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				invoiceTotalCell.setPaddingRight(4);
				table.addCell(invoiceTotalCell);
				
				PdfPCell invoiceTaxCell = new PdfPCell(new Phrase(String.valueOf(customerReportDTO.getItemTax())));
				invoiceTaxCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				invoiceTaxCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				invoiceTaxCell.setPaddingRight(4);
				table.addCell(invoiceTaxCell);
			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}