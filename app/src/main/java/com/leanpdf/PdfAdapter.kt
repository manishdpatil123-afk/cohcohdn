package com.leanpdf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class PdfAdapter(
    private val pdfFiles: MutableList<File>,
    private val onItemClick: (File) -> Unit
) : RecyclerView.Adapter<PdfAdapter.PdfViewHolder>() {

    class PdfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtPdfName: TextView = itemView.findViewById(R.id.txtPdfName)
        val txtPdfPath: TextView = itemView.findViewById(R.id.txtPdfPath)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pdf, parent, false)
        return PdfViewHolder(view)
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {

        val pdf = pdfFiles[position]

        holder.txtPdfName.text = pdf.name
        holder.txtPdfPath.text = pdf.absolutePath

        holder.itemView.setOnClickListener {
            onItemClick(pdf)
        }

        holder.itemView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.scaleX = 1.05f
                view.scaleY = 1.05f
                view.alpha = 1.0f
            } else {
                view.scaleX = 1.0f
                view.scaleY = 1.0f
                view.alpha = 0.9f
            }
        }
    }

    override fun getItemCount(): Int = pdfFiles.size

    fun updateFiles(newFiles: List<File>) {
        pdfFiles.clear()
        pdfFiles.addAll(newFiles)
        notifyDataSetChanged()
    }
}