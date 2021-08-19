package geekbrains.slava_5655380.domain.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.util.Log
import geekbrains.slava_5655380.App
import java.io.IOException

class BitmapFromUriFactory {
    companion object{
        // Источник: https://stackoverflow.com/questions/29971319/image-orientation-android/32747566#32747566
        // TODO: Не получается избавится от бага:
        //              при получении изображения из галереи, если оно снято на камеру устройство,
        //              то bitmap может оказаться повёрнутым на 90 градусов. Попробовал
        //              решение по ссылке, но оно игогда работает, а иногда нет.
        private fun restoreImageOrientation(bitmap: Bitmap, imageUri: Uri): Bitmap {
            try {
                val rotationInDegrees = ImageOrientationUtil.getExifRotation(
                    ImageOrientationUtil
                        .getFromMediaUri(
                            App.instance,
                            App.instance.contentResolver,
                            imageUri
                        )
                )
                val matrix = Matrix()
                if (rotationInDegrees.toFloat() != 0f) {
                    matrix.preRotate(rotationInDegrees.toFloat())
                }
                return Bitmap.createBitmap(
                    bitmap,
                    0,
                    0,
                    bitmap.width,
                    bitmap.height,
                    matrix,
                    true
                )
            } catch (e: IOException) {
                Log.e("[ImageOrientationUtil]", "Failed to get Exif data", e)
                return bitmap
            }
        }

        fun decodeImageUri(imageUri: Uri): Bitmap {
            return BitmapFactory.decodeStream(
                App.instance.contentResolver.openInputStream(
                    imageUri
                )
            ).apply { restoreImageOrientation(this, imageUri) }
        }
    }
}