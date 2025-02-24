package app.example.designssystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.example.designssystem.R
import app.example.designssystem.theme.StylishAppTheme

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    onValueChange: (TextFieldValue) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val isPasswordType = keyboardType == KeyboardType.Password

    val transformation = when {
        isPasswordType && !passwordVisible -> PasswordVisualTransformation()
        isPasswordType && passwordVisible -> VisualTransformation.None
        else -> visualTransformation
    }

    var isFocused by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp, end = 10.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
            },
            label = { Text(text = labelId) },
            singleLine = isSingleLine,
            textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
            enabled = enabled,
            visualTransformation = transformation,
            trailingIcon = {
                if (isPasswordType) {
                    PasswordVisibilityToggle(
                        passwordVisible = passwordVisible,
                        onToggleVisibility = { passwordVisible = !passwordVisible }
                    )
                }
            },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = onAction
        )
    }
}

@Composable
private fun PasswordVisibilityToggle(passwordVisible: Boolean, onToggleVisibility: () -> Unit) {
    IconButton(onClick = onToggleVisibility) {
        Icon(
            painter = painterResource(id = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
            contentDescription = if (passwordVisible) "Hide password" else "Show password",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyInputFieldPreview() {
    StylishAppTheme {
        var textState by remember { mutableStateOf(TextFieldValue("")) }

        InputField(
            value = textState,
            onValueChange = { newText -> textState = newText },
            labelId = "Enter text",
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions(onDone = { /* Handle action here */ })
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPassPreview() {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    InputField(
        value = textState,
        onValueChange = { newText -> textState = newText },
        labelId = "Enter text",
        enabled = true,
        isSingleLine = true,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done,
        onAction = KeyboardActions(onDone = { /* Handle action here */ }),
        visualTransformation = PasswordVisualTransformation()
    )
}
