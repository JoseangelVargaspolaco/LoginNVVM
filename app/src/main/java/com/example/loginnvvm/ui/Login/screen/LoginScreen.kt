package com.example.loginnvvm.ui.Login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginnvvm.R
import com.example.loginnvvm.ui.Menu.LateralMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

/////////////////////////////////////////////MENU LATERAL///////////////////////////////////////////

@Composable
fun MenuLateral(){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navegationItems = listOf(
//        LateralMenu.Opción_init,
        LateralMenu.Opción_1,
        LateralMenu.Opción_2,
        LateralMenu.Opción_3
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope, scaffoldState)},
        drawerContent = {Drawer(menu_items = navegationItems)}
    ) {

    }
}

@Composable
fun TopBar(
    scope : CoroutineScope,
    scaffoldState : ScaffoldState
){
    TopAppBar(
        title = { Text(text = "Menu Inicial")},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }){
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Icono de menu"
                )
            }
        }
    )
}

@Composable
fun Drawer(menu_items: List<LateralMenu>){
//    val menu_items = listOf(
//        "Option_init",
//        "Opción_1",
//        "Opción_2",
//        "Opción_3"
//    )

    Column() {
        menu_items.forEach{item ->
            DrawerItem(item = item)
        }
    }
}

@Composable
fun DrawerItem(item: LateralMenu)
{
    Row(

    ){
        Image(painterResource(id = item.icon),
            contentDescription = item.title)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = item.title,
            style = MaterialTheme.typography.body1
        )
    }
}

//////////////////////////////////////////LOGIN/////////////////////////////////////////////////////

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {
    val email :String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
        }
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .height(48.dp)
            .padding(2.dp)
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xE17041C4),
            disabledBackgroundColor = Color(0xF35768C5),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xF53F51B5)
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Correo electronico") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.log),
        contentDescription = "Header",
        modifier = modifier
    )
    Text(text = "Inicio de sesión", modifier = modifier, color = Color(0xF53F51B5), fontSize = 25.sp)
}


