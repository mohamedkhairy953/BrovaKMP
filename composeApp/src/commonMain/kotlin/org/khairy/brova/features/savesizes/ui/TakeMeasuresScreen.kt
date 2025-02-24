package org.khairy.brova.features.savesizes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.khairy.brova.utils.SpacerWidth8

@Composable
fun TakeMeasuresScreen(
    image: Painter,
    textFieldLabel: String,
    measurementValue: String?,
    onMeasurementValueChange: (String) -> Unit,
    nextButtonAction: () -> Unit,
    confirmButtonAction: (() -> Unit)? = null,
    previousButtonAction: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "خلينا ناخد مقاساتك",
            style = MaterialTheme.typography.h6,
            color = Color.Blue,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "عشان تختار هدومك أسهل بعد كده خلينا نحدد المقاسات دلوقتي",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // T-shirt illustration
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "قم بأخذ القياس من الجزء المشار إليه بالسهم",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )

        // Input field for measurement
        OutlinedTextField(
            value = measurementValue ?: "",
            onValueChange = onMeasurementValueChange,
            label = { Text(text = textFieldLabel) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )

        // Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            previousButtonAction?.let {
                Button(
                    onClick = it,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "رجوع")
                }

                SpacerWidth8()
            }

            Button(
                enabled = measurementValue.isNullOrEmpty().not(),
                onClick = {
                    if (measurementValue.isNullOrEmpty().not()) {
                        confirmButtonAction?.invoke() ?: nextButtonAction()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = if (confirmButtonAction == null) "التالي" else "تأكيد")
            }
        }
    }
}