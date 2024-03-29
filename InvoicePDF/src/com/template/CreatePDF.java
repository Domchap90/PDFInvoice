package com.template;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {

	public static String formatToCurrency(BigDecimal bd) {
		String currencyFormat = NumberFormat.getCurrencyInstance().format(bd);
		return currencyFormat;
	}

	public static void main(String[] args) throws IOException {
		// TODO upload picture (logo) top centre
		// table 4 columns
		// Description
		// Amount
		Document doc = new Document ();
		String fileName = "Invoice.pdf";
		try {
			PdfWriter writer = PdfWriter.getInstance (doc, new FileOutputStream(fileName));
			doc.open();

			String imageFile = "Logo.png";
			Image image = Image.getInstance(imageFile);
			image.scaleAbsolute(125, 125);
			image.setAbsolutePosition(225, 700);
			doc.add(image);

			// Title
			Font font = new Font(FontFamily.TIMES_ROMAN, 30.0f);
			Paragraph title = new Paragraph("Invoice", font);
			title.setSpacingBefore(100);
			title.setIndentationLeft(220);
			doc.add(title);

			// Today's Date.
			Paragraph p1 = new Paragraph("Date: " + LocalDate.now().toString());
			p1.setIndentationLeft(420f);
			doc.add(p1);

			// To (Client name) please pay the Total amount by direct bank transfer to the
			Client client = new Client("John Smith", false);
			String message1 = " For the attention of " + client.getName() + ", please pay the Total amount"
					+ " listed by direct bank transfer to the following account details:";
			String message2 = "\nAccount number: XXXX XXXX\n" +
			"Sort Code:XX XX XX\nReference: (your first intial) + (surname)";
			Paragraph p2 = new Paragraph(message1, new Font(FontFamily.COURIER, 14.0f, 2));
			p2.setSpacingBefore(100);
			doc.add(p2);
			Paragraph p3 = new Paragraph(message2, new Font(FontFamily.COURIER, 14.0f, 1));
			doc.add(p3);
			// following account details

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			float[] colWidth = { 2f, 2f, 2f, 2f };
			table.setWidths(colWidth);

			// Create content of cells to be added to table.
			PdfPCell cell = new PdfPCell();

			int productNum = 0;
			BigDecimal sum = new BigDecimal(0);
			for (int c = 0; c < 24; c++) {
				if (c % 4 == 0 && c < 12) {
					productNum = (c / 4) + 1;
					cell.setPhrase(new Phrase(new Product(productNum).getProduct()));
				} else if (c % 4 == 3 && c < 12) {
					BigDecimal price = new Product(productNum).getPrice();
					cell.setPhrase(new Phrase(formatToCurrency(price)));
					sum = sum.add(price);
				} else if (c == 18) {
					cell.setPhrase(new Phrase("Subtotal\n + VAT"));
				} else if (c == 19) {
					cell.setPhrase(new Phrase(formatToCurrency(sum)));
				} else if (c == 22) {
					cell.setPhrase(new Phrase("Total"));
				} else if (c == 23) {
					sum = sum.multiply(new BigDecimal(1.20));
					cell.setPhrase(new Phrase(formatToCurrency(sum)));
				} else {
					cell.setPhrase(new Phrase(" "));
				}
				cell.disableBorderSide(15);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table.addCell(cell);
				}
			doc.add(table);
			table.getHorizontalAlignment();
			// add table to doc
			doc.close();
			writer.close();
		} catch(DocumentException de) {
			de.printStackTrace();
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}
	}
}
