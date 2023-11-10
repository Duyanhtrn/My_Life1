package com.example.mylife.ui.information

import android.text.Editable.Factory
import android.util.Log
import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mylife.R
import com.example.mylife.TopBar
import com.example.mylife.navigation.navigationDestination
import com.example.mylife.reuse.EditNumberField
import com.example.mylife.ui.AppViewModelProvider
import kotlinx.coroutines.launch

// sau khi người dùng đăng ký tài khoản, đây là trang để họ điền thông tin cụ thể
object EntryInforDestination : navigationDestination {
    override val route = "navigateToEntryInfor"
    override val titleRes = R.string.app_name
}
@Composable
fun UpProfile(
    navigateToHome: () -> Unit,
    viewModel: EntryInfoViewModel = viewModel(factory = AppViewModelProvider.Factory)
){

    var nameUser by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var gender by remember {
        mutableStateOf(
            ""
        )
    }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "MyLife",
                    fontSize = 30.sp
                )

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Please enter your information",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.name,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = nameUser,
                onValueChange = {
                    nameUser = it
                    viewModel.updateUiState(viewModel.entryInfoUiState.userInfo.copy(userName = nameUser))
                                },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.gender,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = gender,
                onValueChange = {
                    gender = it
                    viewModel.updateUiState(viewModel.entryInfoUiState.userInfo.copy(userGender = gender))
                },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.age,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = age,
                onValueChange = {
                    age = it
                    viewModel.updateUiState(viewModel.entryInfoUiState.userInfo.copy(userAge = age))
                                },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.height,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = height,
                onValueChange = { height = it
                    viewModel.updateUiState(viewModel.entryInfoUiState.userInfo.copy(userHeight = height))
                },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.weight,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = weight,
                onValueChange = { weight = it
                    viewModel.updateUiState(viewModel.entryInfoUiState.userInfo.copy(userWeight = weight))
                },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            GoalRadioGroupExample()
            ActivityRateRadioGroupExample()
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Center)
            ) {
                Button(
                    onClick = {
                              viewModel.viewModelScope.launch {
                                  viewModel.saveUser()
                                  Log.d("SaveUser", "userSaved")
                                  navigateToHome()
                              }

                    },
                    ) {
                    Text(text = "Update now")
                }
            }
        }
    }
}

@Composable
fun RadioOption(text: String, optionValue: Int, selectedOption: Int, onOptionSelected: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = optionValue == selectedOption,
            onClick = { onOptionSelected(optionValue) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun GoalRadioGroupExample() {
    var selectedOption by remember { mutableStateOf(1) }

    Column {
        Text(text = "Your Goal: ",
            fontSize = 21.sp,
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
        Spacer(modifier = Modifier.height(5.dp))
        RadioOption("Keep weight", 1, selectedOption) { selectedOption = it }
        RadioOption("Gain weight", 2, selectedOption) { selectedOption = it }
        RadioOption("Lose weight", 3, selectedOption) { selectedOption = it }
    }
}
@Composable
fun ActivityRateRadioGroupExample() {
    var selectedOption by remember { mutableStateOf(1) }

    Column {
        Text(text = "Your Activity Rate: ",
            fontSize = 21.sp,
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
        Spacer(modifier = Modifier.height(5.dp))
        RadioOption("Lightly Active", 1, selectedOption) { selectedOption = it }
        RadioOption("Moderate Activate", 2, selectedOption) { selectedOption = it }
        RadioOption("Very Activate", 3, selectedOption) { selectedOption = it }
    }
}

@Composable
fun TestUpProfile(

    ){
    var nameUser by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "MyLife",
                    fontSize = 30.sp
                )

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Please enter your information",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.name,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = nameUser,
                onValueChange = { nameUser = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.age,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = age,
                onValueChange = { age = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.height,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = height,
                onValueChange = { height = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            EditNumberField(
                label = R.string.weight,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = weight,
                onValueChange = { weight = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            GoalRadioGroupExample()
            ActivityRateRadioGroupExample()
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Center)
            ) {
                Button(
                    onClick = {},
                ) {
                    Text(text = "Update now")
                }
            }
        }
    }
}


@Preview
@Composable
fun PreTestUpProfile() {
    TestUpProfile()
}