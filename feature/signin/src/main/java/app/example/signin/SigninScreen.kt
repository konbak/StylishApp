package app.example.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.example.designssystem.components.CustomButton
import app.example.designssystem.components.InputField
import app.example.designssystem.components.TextField
import app.example.designssystem.theme.StylishAppTheme
import app.example.domain.navigation.StylishappScreens

@Composable
fun SigninScreen(
    navController: NavController,
    viewModel: SigninViewModel = hiltViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSignedIn) {
        if (uiState.isSignedIn) {
            navController.navigate(StylishappScreens.HomeScreen.name) {
                popUpTo(StylishappScreens.SigninScreen.name) { inclusive = true }
            }
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackBarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short,
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        content = { paddingValues ->
            SigninStatelessScreen(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onSignInClick = { username, password ->
                    viewModel.updateCredentials(username, password)
                    viewModel.signIn()
                },
            )
        }
    )
}

@Composable
internal fun SigninStatelessScreen(
    modifier: Modifier = Modifier,
    uiState: SignInUiState,
    onSignInClick: (username: String, password: String) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        var userNameTextValue by remember { mutableStateOf(TextFieldValue("")) }
        val focusRequesterUserName = remember { FocusRequester() }

        var passwordTextValue by remember { mutableStateOf(TextFieldValue("")) }
        val focusRequesterPassword = remember { FocusRequester() }

        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.signin_title),
            textSize = 54.sp,
        )

        Spacer(modifier = Modifier.height(12.dp))

        InputField(
            value = userNameTextValue,
            onValueChange = { newText -> userNameTextValue = newText },
            labelId = stringResource(R.string.username_text),
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            onAction = KeyboardActions(
                onNext = { focusRequesterPassword.requestFocus() }
            ),
            modifier = Modifier.focusRequester(focusRequesterUserName),
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            value = passwordTextValue,
            onValueChange = { newText -> passwordTextValue = newText },
            labelId = stringResource(R.string.password_text),
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            modifier = Modifier.focusRequester(focusRequesterPassword),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            text = stringResource(R.string.signin_button),
            enabled = !uiState.isLoading,
        ) {
            keyboardController?.hide()
            onSignInClick(
                userNameTextValue.text,
                passwordTextValue.text,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    StylishAppTheme {
        SigninStatelessScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = SignInUiState(),
            onSignInClick = { _, _ -> },
        )
    }
}