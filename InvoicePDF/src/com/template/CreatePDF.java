package com.template;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {

	public static void main(String[] args) throws IOException {
		// TODO upload picture (logo) top centre
		// table 4 columns
		// Description
		// Amount
		Document doc = new Document ();
		String fileName = "Deposit_fund_receipt.pdf";
		try {
			PdfWriter writer = PdfWriter.getInstance (doc, new FileOutputStream(fileName));
			doc.open();

			String imageFile = "logo.png";
			Image image = Image.getInstance(imageFile);
			image.scaleAbsolute(125, 125);
			image.setAbsolutePosition(225, 700);
			doc.add(image);

			// Text title = new Text("Deposit fund receipt");
			Font font = new Font(FontFamily.TIMES_ROMAN, 20.0f);
			Paragraph title = new Paragraph("Deposit fund receipt", font);
			title.setSpacingBefore(200);
			title.setIndentationLeft(200);
			doc.add(title);

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			float[] colWidth = { 2f, 2f, 2f, 2f };
			table.setWidths(colWidth);
			// create cells
			PdfPCell c1 = new PdfPCell(new Paragraph("Amount"));
			PdfPCell c2 = new PdfPCell(new Paragraph(""));
			PdfPCell c3 = new PdfPCell(new Paragraph(""));
			PdfPCell c4 = new PdfPCell(new Paragraph("100.00"));
			// add cells to table
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);
			doc.add(table);
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
