package org.khairy.brova.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import brovakmp.composeapp.generated.resources.Res
import brovakmp.composeapp.generated.resources.suisseintl_bold
import brovakmp.composeapp.generated.resources.suisseintl_light
import brovakmp.composeapp.generated.resources.suisseintl_medium
import brovakmp.composeapp.generated.resources.suisseintl_regular
import brovakmp.composeapp.generated.resources.suisseintl_semibold
import org.jetbrains.compose.resources.Font

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 4:47 PM
 */

@Composable
fun ProvaFontFamily() = FontFamily(
    Font(Res.font.suisseintl_regular, FontWeight.Normal),
    Font(Res.font.suisseintl_bold, FontWeight.Bold),
    Font(Res.font.suisseintl_medium, FontWeight.Medium),
    Font(Res.font.suisseintl_semibold, FontWeight.SemiBold),
    Font(Res.font.suisseintl_light, FontWeight.Light),
)