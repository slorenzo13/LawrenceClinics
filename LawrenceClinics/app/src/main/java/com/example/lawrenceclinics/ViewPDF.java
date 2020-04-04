package com.example.lawrenceclinics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewPDF extends AppCompatActivity {

    private PDFView vistPDF;
    private File fichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        vistPDF = (PDFView)findViewById(R.id.pdfView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            fichero = new File(bundle.getString("ruta", ""));
        }

        vistPDF.fromFile(fichero)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();
    }
}
