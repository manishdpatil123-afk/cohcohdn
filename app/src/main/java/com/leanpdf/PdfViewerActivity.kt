package com.leanpdf

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var pdfView: PDFView
    private lateinit var txtPage: TextView

    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var btnZoomIn: Button
    private lateinit var btnZoomOut: Button

    private lateinit var prefs: SharedPreferences

    private var currentPage = 0
    private var zoom = 1f
    private var pdfPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        pdfView = findViewById(R.id.pdfView)
        txtPage = findViewById(R.id.txtPage)

        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)
        btnZoomIn = findViewById(R.id.btnZoomIn)
        btnZoomOut = findViewById(R.id.btnZoomOut)

        prefs = getSharedPreferences("LeanPDF", MODE_PRIVATE)

        pdfPath = intent.getStringExtra("PDF_PATH") ?: return

        currentPage = prefs.getInt(pdfPath, 0)

        openPdf()

        btnNext.setOnClickListener {
            pdfView.jumpTo(currentPage + 1, true)
        }

        btnPrevious.setOnClickListener {
            if (currentPage > 0) {
                pdfView.jumpTo(currentPage - 1, true)
            }
        }

        btnZoomIn.setOnClickListener {
            zoom += 0.25f
            pdfView.zoomTo(zoom)
            pdfView.invalidate()
        }

        btnZoomOut.setOnClickListener {
            if (zoom > 1f) {
                zoom -= 0.25f
                pdfView.zoomTo(zoom)
                pdfView.invalidate()
            }
        }
    }

    private fun openPdf() {

        pdfView.fromFile(File(pdfPath))
            .defaultPage(currentPage)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .pageSnap(true)
            .pageFling(true)
            .onPageChange { page, pageCount ->
                currentPage = page
                txtPage.text = "${page + 1} / $pageCount"
            }
            .load()
    }

    override fun onPause() {
        super.onPause()

        prefs.edit()
            .putInt(pdfPath, currentPage)
            .apply()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        when (keyCode) {

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (currentPage > 0)
                    pdfView.jumpTo(currentPage - 1, true)
                return true
            }

            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                pdfView.jumpTo(currentPage + 1, true)
                return true
            }

            KeyEvent.KEYCODE_DPAD_UP -> {
                zoom += 0.25f
                pdfView.zoomTo(zoom)
                pdfView.invalidate()
                return true
            }

            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (zoom > 1f) {
                    zoom -= 0.25f
                    pdfView.zoomTo(zoom)
                    pdfView.invalidate()
                }
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}