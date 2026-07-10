package com.leanpdf

import java.io.File

object FileScanner {

    fun scan(root: File): List<File> {

        val pdfFiles = mutableListOf<File>()

        if (!root.exists() || !root.canRead()) {
            return pdfFiles
        }

        scanDirectory(root, pdfFiles)

        return pdfFiles.sortedBy { it.name.lowercase() }
    }

    private fun scanDirectory(
        directory: File,
        pdfFiles: MutableList<File>
    ) {

        val files = directory.listFiles() ?: return

        for (file in files) {

            if (file.isDirectory) {
                scanDirectory(file, pdfFiles)
            } else if (
                file.isFile &&
                file.name.endsWith(".pdf", ignoreCase = true)
            ) {
                pdfFiles.add(file)
            }
        }
    }
}