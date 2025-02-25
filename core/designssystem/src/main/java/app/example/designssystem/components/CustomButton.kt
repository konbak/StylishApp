package app.example.designssystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.example.designssystem.theme.StylishAppTheme

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        enabled = enabled
    ) {
        Text(text = text, style = TextStyle(fontSize = 18.sp))
    }
}

@Preview
@Composable
fun PreviewCustomButton() {
    StylishAppTheme {
        CustomButton(
            text = "test text",
            onClick = {}
        )
    }
}
