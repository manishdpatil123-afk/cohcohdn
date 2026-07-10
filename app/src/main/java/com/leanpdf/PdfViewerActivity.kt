package com.leanpdf

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var pdfView: PDFView

    private var currentPage = 0
    private var currentZoom = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pdf)

        pdfView = findViewById(R.id.pdfView)

        val pdfPath = intent.getStringExtra("PDF_PATH") ?: return

        pdfView.fromUri(Uri.parse(pdfPath))
            .defaultPage(currentPage)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .pageSnap(true)
            .pageFling(true)
            .onPageChange { page, _ ->
                currentPage = page
            }
            .load()

        setupControls()
    }

    private fun setupControls() {

        findViewById<android.view.View>(R.id.btnZoomIn).setOnClickListener {
            currentZoom += 0.25f
            pdfView.zoomTo(currentZoom)
            pdfView.invalidate()
        }

        findViewById<android.view.View>(R.id.btnZoomOut).setOnClickListener {
            if (currentZoom > 1.0f) {
                currentZoom -= 0.25f
                pdfView.zoomTo(currentZoom)
                pdfView.invalidate()
            }
        }

        findViewById<android.view.View>(R.id.btnNext).setOnClickListener {
            pdfView.jumpTo(currentPage + 1, true)
        }

        findViewById<android.view.View>(R.id.btnPrevious).setOnClickListener {
            if (currentPage > 0) {
                pdfView.jumpTo(currentPage - 1, true)
            }
        }
    }
}