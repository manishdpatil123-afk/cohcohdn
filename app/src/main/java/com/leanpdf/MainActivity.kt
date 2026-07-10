package com.leanpdf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var pdfList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        pdfList = findViewById(R.id.rvPdfList)

        pdfList.layoutManager = LinearLayoutManager(this)

        // TODO:
        // 1. Scan Internal Storage
        // 2. Scan USB Storage
        // 3. Populate RecyclerView
        // 4. Open PdfViewerActivity on item click
    }

    private fun openPdf(pdfPath: String) {

        val intent = Intent(this, PdfViewerActivity::class.java)
        intent.putExtra("PDF_PATH", pdfPath)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        // Refresh list when returning from viewer
    }
}