@file:OptIn(KoinExperimentalAPI::class)

package org.khairy.brova.features.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import brovakmp.composeapp.generated.resources.Res
import brovakmp.composeapp.generated.resources.ic_logo
import brovakmp.composeapp.generated.resources.ic_passkey_register
import brovakmp.composeapp.generated.resources.ic_phone_register
import brovakmp.composeapp.generated.resources.ic_visibility_off
import brovakmp.composeapp.generated.resources.ic_visibility_on
import org.jetbrains.compose.resources.painterResource
import org.khairy.brova.design.AppColors
import org.khairy.brova.features.login.ui.LoginScreen
import org.khairy.brova.features.login.viewmodel.RegisterEvent
import org.khairy.brova.features.login.viewmodel.RegisterState
import org.khairy.brova.features.login.viewmodel.RegisterViewModel
import org.khairy.brova.navigation.HomeScreen
import org.khairy.brova.navigation.LoginScreen
import org.khairy.brova.utils.SpacerHeight16
import org.khairy.brova.utils.SpacerHeight32
import org.khairy.brova.utils.SpacerHeight8
import org.khairy.brova.utils.SpacerWidth8
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    navController: NavController,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.collectAsState().value
    val username = viewModel.username.collectAsState().value
    val phone = viewModel.phone.collectAsState().value
    val email = viewModel.email.collectAsState().value
    val confirmPassword = viewModel.confirmPassword.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val isLoading = state is RegisterState.Loading
    var passwordVisible by remember { mutableStateOf(false) }

    // When an error state is detected, log the actual error and show a generic error message via SnackBar.
    LaunchedEffect(state) {
        if (state is RegisterState.Error) {
            println("RegisterScreen: " + state.message)
            scaffoldState.snackbarHostState.showSnackbar("حدث خطأ ما، الرجاء المحاولة مرة أخرى")
        }
    }

    // Navigate to the HomeScreen when registration is successful.
    when (state) {
        is RegisterState.Success -> {
            navController.navigate(HomeScreen)
        }

        else -> {}
    }

    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Top,
        ) {
            SpacerHeight32()

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(Res.drawable.ic_logo),
                    contentDescription = "Prova Logo",
                    modifier = Modifier.size(124.dp, 96.dp)
                )
            }

            SpacerHeight32()

            Text(
                text = "مرحبا بك في تطبيق بروفة..",
                color = AppColors.blue_0072CE,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp,
            )
            SpacerWidth8()
            Text(
                text = "سجل معنا في التطبيق واستمتع بخدماتنا",
                fontWeight = FontWeight.Normal,
                color = AppColors.black_595959,
                fontSize = 16.sp,
            )
            SpacerHeight32()
            Text(
                text = "الاسم",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 16.sp,
            )
            TextField(
                value = username,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                onValueChange = { viewModel.onEvent(RegisterEvent.OnUsernameChange(it)) },
                placeholder = { Text("الاسم") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Face Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "رقم الجوال",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 16.sp,
            )
            TextField(
                value = phone,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                onValueChange = { viewModel.onEvent(RegisterEvent.OnPhoneChange(it)) },
                placeholder = { Text("01129053117") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_phone_register),
                        contentDescription = "Phone Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                onValueChange = { viewModel.onEvent(RegisterEvent.OnEmailChange(it)) },
                placeholder = { Text("mohamed@xyz.com") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "كلمة المرور",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 16.sp,
            )
            TextField(
                value = password,
                onValueChange = { viewModel.onEvent(RegisterEvent.OnPasswordChange(it)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = { Text("********") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_passkey_register),
                        contentDescription = "Password Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                trailingIcon = {
                    val icon = if (passwordVisible)
                        painterResource(Res.drawable.ic_visibility_on)
                    else
                        painterResource(Res.drawable.ic_visibility_off)

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = icon,
                            contentDescription = description,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            SpacerHeight16()

            Text(
                text = "تأكيد كلمة المرور",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 16.sp,
            )
            TextField(
                value = confirmPassword,
                onValueChange = { viewModel.onEvent(RegisterEvent.OnConfirmPasswordChange(it)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = { Text("********") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_passkey_register),
                        contentDescription = "Password Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                trailingIcon = {
                    val icon = if (passwordVisible)
                        painterResource(Res.drawable.ic_visibility_on)
                    else
                        painterResource(Res.drawable.ic_visibility_off)

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = icon,
                            contentDescription = description,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            SpacerHeight16()

            Text(
                text = buildAnnotatedString {
                    append("نسيت كلمة المرور ؟")
                    addStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        start = 0,
                        end = "نسيت كلمة المرور ؟".length
                    )
                },
                style = TextStyle(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                modifier = Modifier.align(Alignment.End).clickable {
                    // Handle forgot password logic
                }
            )

            Spacer(modifier = Modifier.height(64.dp))
            Button(
                onClick = {
                    viewModel.onEvent(RegisterEvent.OnRegisterClick)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White
                    )
                } else {
                    Text("تسجيل")
                }
            }

            SpacerHeight8()

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "لديك حساب في التطبيق ؟",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
                SpacerWidth8()
                Text(
                    text = buildAnnotatedString {
                        val text = "تسجيل الدخول"
                        append(text)
                        addStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                            start = 0,
                            end = text.length
                        )
                    },
                    modifier = Modifier.clickable {
                        navController.navigate(LoginScreen)
                    }
                )
            }
        }
    }
}