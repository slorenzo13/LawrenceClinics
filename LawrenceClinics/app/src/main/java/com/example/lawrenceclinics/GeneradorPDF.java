package com.example.lawrenceclinics;


import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;

public class GeneradorPDF {

    public File imprimirComprobante(Cita cita, File carpeta) {
        String fecha = cita.gettFecha().replaceAll("[^a-zA-Z0-9.\\-]", "_");
        String horario = cita.gettHorario().replaceAll("[^a-zA-Z0-9.\\-]", "_");

        File fichero = new File(carpeta,String.format("%s_%s_Comprobante_cita.pdf",fecha,horario));
        try {
            PdfWriter writer = new PdfWriter(fichero);
            PdfDocument docPdf = new PdfDocument(writer);
            Document document = new Document(docPdf, PageSize.A5);

            Paragraph p1 = new Paragraph("Estos son los datos de su cita:");
            Paragraph p2 = new Paragraph("Especialidad: "+cita.gettEspecialidad());
            Paragraph p3 = new Paragraph("Doctor: "+cita.gettDoctor());
            Paragraph p4 = new Paragraph("Fecha: "+cita.gettFecha());
            Paragraph p5 = new Paragraph("Horario: "+cita.gettHorario());

            document.add(p1);
            document.add(p2);
            document.add(p3);
            document.add(p4);
            document.add(p5);

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fichero;
    }



}
