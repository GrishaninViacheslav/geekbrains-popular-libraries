package geekbrains.slava_5655380.domain.models.conversions_history

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import geekbrains.slava_5655380.App
import io.reactivex.Completable
import kotlinx.android.parcel.Parcelize
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class Conversion(
    val metadata: ConversionMetadata,
    val bitmap: Bitmap
) {
    private fun mediaScan() =
        with(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)) {
            data = with(File("${metadata.path}//${metadata.name}")) {
                Uri.fromFile(this)
            }
            App.instance.sendBroadcast(this)
        }

    fun convertAndSave(): Completable {
        return Completable.fromCallable {
            try {
                Thread.sleep(5000)
                with(FileOutputStream(File(metadata.path, metadata.name))) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, this)
                    close()
                }
                if (metadata.addToGallery) {
                    mediaScan()
                }
                //conversionStatus = ConversionStatus.DONE // TODO: почему вызывает ошибку "Expected a value of type Any!" ?
                fun changeStatusToDone() {
                    metadata.status = ConversionStatus.DONE
                }
                changeStatusToDone()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

@Parcelize
data class ConversionMetadata(
    val imageUri: Uri,
    val addToGallery: Boolean = true,
    val path: File? = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES
    ),
    val name: String = "resultImage.png",
    var status: ConversionStatus = ConversionStatus.IN_PROGRESS
) : Parcelable

enum class ConversionStatus { DONE, IN_PROGRESS }