# LeanPDF ProGuard Rules

# Keep PDF Viewer library
-keep class com.github.barteksc.pdfviewer.** { *; }
-dontwarn com.github.barteksc.pdfviewer.**

# Keep Pdfium classes
-keep class com.shockwave.pdfium.** { *; }
-dontwarn com.shockwave.pdfium.**

# Keep Activities
-keep class com.leanpdf.MainActivity { *; }
-keep class com.leanpdf.PdfViewerActivity { *; }

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }

# Keep annotations
-keepattributes *Annotation*

# Preserve source/debug info
-keepattributes SourceFile,LineNumberTable

# Don't obfuscate AndroidX
-dontwarn androidx.**