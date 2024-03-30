package com.avility.shared.core.extensions

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun Bitmap.toBase64(): String? {
    val outputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    val b = outputStream.toByteArray()
    return encodeToString(b, DEFAULT)
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"

    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}