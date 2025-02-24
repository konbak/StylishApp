package app.example.designssystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.example.designssystem.theme.StylishAppTheme

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 16.sp,
    textStyle: TextStyle = TextStyle(fontSize = textSize),
) {
    Text(
        modifier = modifier
            .padding(
                start = 10.dp,
                end = 10.dp,
            ),
        text = text,
        fontSize = textSize,
        style = textStyle,
    )
}

@Composable
@Preview(showBackground = true)
private fun TextFieldPreview() {
    StylishAppTheme {
        TextField(
            text = "Hello, World!"
        )
    }
}