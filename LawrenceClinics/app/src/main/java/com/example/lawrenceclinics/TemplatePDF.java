package com.example.lawrenceclinics;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class TemplatePDF {

    private Context contexto;
    private File pdfFile;
    private Document documento;
    private PdfWriter pdfWriter;
    private Paragraph parrafo;

    private Font formatoTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font formatoSubtitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font formatoTexto = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font formatoTextoResaltado = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.RED);

    public TemplatePDF(Context contexto) {
        this.contexto=contexto;
    }

    public void openDocument(){
        createFile();

        try {

            documento = new com.itextpdf.text.Document(PageSize.A4);
            pdfWriter=PdfWriter.getInstance(documento, new FileOutputStream(pdfFile));
            documento.open();

        } catch (Exception e){
            Log.e("openDocument", e.toString());
        }
    }

    private void createFile(){
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");

        if (!folder.exists()){
            folder.mkdir();
            pdfFile=new File(folder, "TemplatePDF.pdf");
        }
    }

    public void closeDocument(){
        documento.close();
    }

    public void addMetaData(String titulo, String tema, String autor){
        documento.addTitle(titulo);
        documento.addSubject(tema);
        documento.addAuthor(autor);
    }

    public void addTitle(String Titulo, String SubTitulo, String Fecha){
        try {

            parrafo = new Paragraph();
            addChildParagraph(new Paragraph(Titulo, formatoTitulo));
            addChildParagraph(new Paragraph(SubTitulo, formatoSubtitulo));
            addChildParagraph(new Paragraph("Generado a las: " + Fecha, formatoTextoResaltado));
            parrafo.setSpacingAfter(30);
            documento.add(parrafo);

        } catch (Exception e){
            Log.e("addTitle", e.toString());
        }

    }

    public void addParagraph(String text){
        try {

            parrafo = new Paragraph(text, formatoTexto);
            parrafo.setSpacingAfter(5);
            parrafo.setSpacingBefore(5);
            documento.add(parrafo);
        }
        catch (Exception e){
            Log.e("addParagraph", e.toString());
        }
    }

    private void addChildParagraph(Paragraph parrafoHijo){
        parrafoHijo.setAlignment(Element.ALIGN_CENTER);
        parrafo.add(parrafoHijo);
    }

    public void createTable(String[]header, ArrayList<String[]>usuarios){

        try {
            parrafo = new Paragraph();
            parrafo.setFont(formatoTexto);

            PdfPTable tablaPDF = new PdfPTable(header.length);
            tablaPDF.setWidthPercentage(100);
            PdfPCell celdaTabla;
            int indexC=0;

            while (indexC<header.length){
                celdaTabla = new PdfPCell(new Phrase(header[indexC++], formatoSubtitulo));
                celdaTabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaTabla.setBackgroundColor(BaseColor.GREEN);
                tablaPDF.addCell(celdaTabla);
            }

            for (int indexRow=0;indexRow<usuarios.size();indexRow++){
                String[]row = usuarios.get(indexRow);
                for ( indexC=0;indexC<header.length;indexC++){
                    celdaTabla=new PdfPCell(new Phrase(row[indexC]));
                    celdaTabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celdaTabla.setFixedHeight(40);
                    tablaPDF.addCell(celdaTabla);
                }
            }
            parrafo.add(tablaPDF);
            documento.add(parrafo);
        }
        catch (Exception e){
            Log.e("createTable", e.toString());
        }

    }

    public void viewPDF(){
        Intent intent = new Intent(contexto, ViewPDF.class);
        intent.putExtra("directorio",pdfFile.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(intent);
    }
}
