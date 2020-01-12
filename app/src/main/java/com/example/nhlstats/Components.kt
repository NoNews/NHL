package com.example.nhlstats

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.WithDensity
import androidx.ui.graphics.Color
import androidx.ui.graphics.Image
import androidx.ui.graphics.ImageConfig
import androidx.ui.graphics.colorspace.ColorSpaces
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.Size
import androidx.ui.res.vectorResource

@Composable
fun VectorImage(
    modifier: Modifier = Modifier.None, @DrawableRes id: Int,
    tint: Color = Color.Transparent
) {
    val vector = +vectorResource(id)
    WithDensity {
        Container(
            modifier = modifier wraps Size(
                50.toDp(),
                50.toDp()
            )
        ) {
            DrawVector(vector, tint)
        }
    }
}

class RemoteImage(private val bitmap: Bitmap) : Image {
    override val width = bitmap.width
    override val height = bitmap.height
    override val config = ImageConfig.Argb8888
    override val colorSpace = ColorSpaces.Srgb
    override val hasAlpha = bitmap.hasAlpha()
    override val nativeImage = bitmap
    override fun prepareToDraw() = bitmap.prepareToDraw()
}