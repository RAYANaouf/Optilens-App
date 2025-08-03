package com.example.optilens.presentation.view.screens.logIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.optilens.R // Assuming you have a logo in drawable
import com.example.optilens.presentation.theme.OptilensTheme // Your app's theme
import com.example.optilens.presentation.theme.background_color_0
import com.example.optilens.presentation.theme.customWhite0
import com.example.optilens.presentation.theme.p_color1
import com.example.optilens.presentation.theme.p_color1_dark
import com.example.optilens.presentation.theme.p_color2
import com.example.optilens.presentation.view.screens.logIn.event.LogInEvent

// Dummy ViewModel State and Events for illustration
data class ClientLoginUiState(
    val clientCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    uiState: ClientLoginUiState = ClientLoginUiState(),
    onEvent: (LogInEvent) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var hasAttemptedLogin by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient( // Subtle gradient background
                    colors = listOf(
                        background_color_0,
                        background_color_0,
                        p_color1,
                        p_color1,
                        p_color1_dark
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Make it scrollable for smaller screens
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Fill the Box
                .padding(horizontal = 16.dp), // Overall horizontal padding for content
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(45.dp))


            // 1. Logo
            Image(
                painter = painterResource(id = R.drawable.optilens_logo), // Replace with your logo
                contentDescription = "Optilens Logo",
                modifier = Modifier
                    .fillMaxWidth(0.80f)// Adjust size as needed
                    .padding(bottom = 48.dp)
                    .background(Color.Red),
                contentScale = ContentScale.FillWidth
            )

            // 2. Welcome Text
            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Please enter your Client Code to continue.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
            )

            // Card for input and button for a slightly elevated look
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(
                        elevation = 8.dp, // Adjust the size of the shadow
                        shape = RoundedCornerShape(16.dp), // Match the card's shape
                        clip = false, // Allow shadow to extend beyond the bounds if needed (usually false for cards)
                        ambientColor = p_color2, // Color in shaded areas
                        spotColor = p_color2    // Color from the light source
                    )
                    .background(customWhite0)

                // You can add other modifiers for the card here as well, like .padding or .width
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp) // Padding inside the card
                        .width(IntrinsicSize.Max), // Make card width fit content or expand
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 3. Client Code Input Field
                    OutlinedTextField(
                        value = uiState.clientCode,
                        onValueChange = {
                            onEvent(LogInEvent.OnClientCodeChange(it))
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors().copy(focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0),
                        label = { Text("Client Code") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Ascii, // Or Number if it's always numeric
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                                if (uiState.clientCode.isNotBlank()) {
                                    hasAttemptedLogin = true
                                    onEvent(LogInEvent.OnLoginClick(uiState.clientCode))
                                }
                            }
                        ),
                        isError = uiState.errorMessage != null && hasAttemptedLogin,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // 4. Error Message Display
                    AnimatedVisibility(
                        visible = uiState.errorMessage != null && hasAttemptedLogin,
                        enter = fadeIn(animationSpec = tween(300)) + slideInVertically(animationSpec = tween(300)) { it / 2 },
                    ) {
                        Text(
                            text = uiState.errorMessage ?: "",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 5. Login Button
                    Button(
                        onClick = {
                            keyboardController?.hide()
                            hasAttemptedLogin = true
                            onEvent(LogInEvent.OnLoginClick(uiState.clientCode))
                        },
                        colors = ButtonDefaults.buttonColors().copy(contentColor = customWhite0 , containerColor = p_color1_dark),
                        enabled = !uiState.isLoading && uiState.clientCode.isNotBlank(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp) // Softer corners
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("LOGIN", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        }
                    }
                }
            }


            // Optional: Support Contact
            Text(
                text = "Having trouble? Contact support.", // Or your specific instruction
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 40.dp)
            )
        }
    }
}

// --- Preview ---
@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun ClientCodeLoginScreen_Preview_Initial() {
    OptilensTheme {
        LoginScreen(
            uiState = ClientLoginUiState(),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun ClientCodeLoginScreen_Preview_Loading() {
    OptilensTheme {
        LoginScreen(
            uiState = ClientLoginUiState(clientCode = "VALIDCODE", isLoading = true),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun ClientCodeLoginScreen_Preview_Error() {
    OptilensTheme(darkTheme = false) { // Example with light theme
        LoginScreen(
            uiState = ClientLoginUiState(clientCode = "INVALIDCODE", errorMessage = "Invalid Client Code. Please try again."),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun ClientCodeLoginScreen_Preview_Dark_Error() {
    OptilensTheme(darkTheme = true) { // Example with dark theme
        LoginScreen(
            uiState = ClientLoginUiState(clientCode = "INVALIDCODE123", errorMessage = "Client code not found or inactive."),
            onEvent = {}
        )
    }
}
