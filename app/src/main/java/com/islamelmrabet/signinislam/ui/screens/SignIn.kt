package com.islamelmrabet.signinislam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamelmrabet.signinislam.ui.theme.SignInIslamTheme
import com.islamelmrabet.signinislam.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun content() {
    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var mail by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.purple_200))
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Mail Icon",
                modifier = Modifier.size(120.dp),
                tint = colorResource(id = R.color.gray)
            )
            Row(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.edit),
                    fontSize = 15.sp,
                    modifier = Modifier
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(start = 4.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.secondaryHeader),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp),
            )
        }
        // Agregar una línea fina bajo el secondary header
        Divider(
            color = colorResource(id = R.color.black),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        textField(
            textFieldValue = name,
            onValueChange = { name = it },
            labelValue = "Name",
            icon = Icons.Default.Person,
            placeHolderValue = "",
            kerboardTypeValue = KeyboardType.Text
        )
        textField(
            textFieldValue = surname,
            onValueChange = { surname = it },
            labelValue = stringResource(id = R.string.labelSurnameField),
            icon = Icons.Default.Person,
            placeHolderValue = "",
            kerboardTypeValue = KeyboardType.Text
        )

        textField(
            textFieldValue = mail,
            onValueChange = { mail = it },
            labelValue = stringResource(id = R.string.labelMailField),
            icon = Icons.Default.Email,
            placeHolderValue = stringResource(id = R.string.exampleMailField),
            kerboardTypeValue = KeyboardType.Text
        )

        textField(
            textFieldValue = phone,
            onValueChange = { phone = it },
            labelValue = stringResource(id = R.string.labelPhoneField),
            icon = Icons.Default.Phone,
            placeHolderValue = "",
            kerboardTypeValue = KeyboardType.Phone
        )
        Column {
            datePickerField()
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.thirdHeader),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp),
            )
        }
        // Agregar una línea fina bajo el secondary header
        Divider(
            color = colorResource(id = R.color.black),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row {
                filterChip(stringResource(id = R.string.tiger))
                filterChip(stringResource(id = R.string.lion))
                filterChip(stringResource(id = R.string.cat))
            }
            Row {
                filterChip(stringResource(id = R.string.lepard))
                filterChip(stringResource(id = R.string.puma))
                filterChip(stringResource(id = R.string.gepard))
            }
        }
        ButtonsSection(
            onClearClick = {
                name = ""
                surname = ""
                mail = ""
                phone = ""
            },
            onSendClick = {
                if (validateInput(name, surname, mail, phone)) showDialog = true

            }
        )
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(stringResource(id = R.string.fieldSummary)) },
                text = {
                    Column {
                        Text("Nombre: $name")
                        Text("Apellidos: $surname")
                        Text("Correo Electronico: $mail")
                        Text("Telefono: $phone")
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(stringResource(id = R.string.accept))
                    }
                }
            )
        }
        Row {
            AboutMe(
                image = painterResource(id = R.drawable.fotoperfil),
                name = stringResource(id = R.string.PersonalName)
            )
        }
    }
}

//Composable que recibe de parametro una imagen y un nombre para crear la cabecera
@Composable
fun AboutMe(image: Painter, name: String) {
    Row(
        modifier = Modifier
            .padding(15.dp)
    ) {
        Image(
            painter = image, contentDescription = "Foto de $name",
            modifier = Modifier
                .width(80.dp)
                .clip(CircleShape)
                .border(
                    width = 5.dp,
                    color = colorResource(id = R.color.purple_200),
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.width(25.dp))
        Text(
            text = name,
            fontSize = 15.sp,
            modifier = Modifier
                .paddingFromBaseline(45.dp)
        )
    }

}

//Composable que recibe como parametro un labelValue, un icono y el paceHolder, para crear un TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textField(textFieldValue: String,labelValue: String, icon: ImageVector, placeHolderValue: String, kerboardTypeValue: KeyboardType,onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            label = { Text(labelValue) },
            placeholder = { Text(placeHolderValue) },
            leadingIcon = { Icon(imageVector = icon, contentDescription = "Mail") },
            keyboardOptions = KeyboardOptions(
                keyboardType = kerboardTypeValue,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onDone = {}
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerField() {
 
    var datePickerState = rememberDatePickerState(initialSelectedDateMillis = Calendar.getInstance().timeInMillis)
    DatePicker(
        state = datePickerState,
        showModeToggle = true
    )
    Text(
        text = "Fecha seleccionada: ${
            run {
                val fecha: Calendar? = Calendar.getInstance()
                val selectedDateMillis = datePickerState.selectedDateMillis
                val formattedDate = selectedDateMillis?.let {
                    val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
                    dateFormat.format(it)
                } ?: ""
                formattedDate
            }
        }",
        modifier = Modifier
            .padding(15.dp)
            .background(color = colorResource(id = R.color.purple_50))
            .border(1.dp, color = colorResource(id = R.color.lightgray))
            .padding(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun filterChip(chipLabel : String){
    var selectedFilterChip by rememberSaveable { mutableStateOf(false) }
    FilterChip(
        selected = selectedFilterChip,
        onClick = { selectedFilterChip = !selectedFilterChip },
        label = { Text(text = chipLabel)},
        leadingIcon = {
            if(selectedFilterChip){
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Seleccionado",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        },
        modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun ButtonsSection(
    onClearClick: () -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClearClick,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text("Borrar Todo")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onSendClick,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text("Enviar")
        }
    }
}


fun validateInput(name:String, surname:String, mail:String, phone: String): Boolean {
    val nameIsValid = isValidName(name)
    val surnameIsValid = isValidName(surname)
    val mailIsValid = isValidEmail(mail)
    val phoneIsValid = isValidPhoneNumber(phone)

    return nameIsValid && surnameIsValid && mailIsValid && phoneIsValid
}
// Permitir solo letras en el nombre y apellidos
fun isValidName(name: String): Boolean {
    val namePattern = Regex("^[a-zA-Z]+$")
    return namePattern.matches(name)
}

// validacion de correo electronico
fun isValidEmail(email: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailPattern.matches(email)
}

// validación de número de teléfono
fun isValidPhoneNumber(phoneNumber: String): Boolean {
    val phonePattern = Regex("^[0-9]{9}$")
    return phonePattern.matches(phoneNumber)
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true,
)

@Composable
fun ContentPreview() {
    SignInIslamTheme {
        content()
    }
}