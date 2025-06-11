package com.training.hogaiat.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcuseScreen() {
    var name by remember { mutableStateOf("") }
    var disease by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("ذكر") }
    var hotel by remember { mutableStateOf("") }
    var government by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("أول") }
    var state by remember { mutableStateOf("ترددي") }
    var supervisor by remember { mutableStateOf("") }

    val genderOptions = listOf("ذكر", "أنثى")
    val levelOptions = listOf("أول", "ثاني", "ثالث")
    val stateOptions = listOf("ترددي", "إعذار", "دفع مباشر")

    var genderExpanded by remember { mutableStateOf(false) }
    var levelExpanded by remember { mutableStateOf(false) }
    var stateExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text("أصحاب الأعذار", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("الاسم") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = disease, onValueChange = { disease = it }, label = { Text("المرض") }, modifier = Modifier.fillMaxWidth())

        // النوع
        ExposedDropdownMenuBox(
            expanded = genderExpanded,
            onExpandedChange = { genderExpanded = !genderExpanded }
        ) {
            OutlinedTextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                label = { Text("النوع") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(expanded = genderExpanded, onDismissRequest = { genderExpanded = false }) {
                genderOptions.forEach {
                    DropdownMenuItem(text = { Text(it) }, onClick = {
                        gender = it
                        genderExpanded = false
                    })
                }
            }
        }

        OutlinedTextField(value = hotel, onValueChange = { hotel = it }, label = { Text("اسم الفندق") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = government, onValueChange = { government = it }, label = { Text("المحافظة") }, modifier = Modifier.fillMaxWidth())

        // الفرقة
        ExposedDropdownMenuBox(
            expanded = levelExpanded,
            onExpandedChange = { levelExpanded = !levelExpanded }
        ) {
            OutlinedTextField(
                value = level,
                onValueChange = {},
                readOnly = true,
                label = { Text("المستوي") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = levelExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(expanded = levelExpanded, onDismissRequest = { levelExpanded = false }) {
                levelOptions.forEach {
                    DropdownMenuItem(text = { Text(it) }, onClick = {
                        level = it
                        levelExpanded = false
                    })
                }
            }
        }

        // الحالة
        ExposedDropdownMenuBox(
            expanded = stateExpanded,
            onExpandedChange = { stateExpanded = !stateExpanded }
        ) {
            OutlinedTextField(
                value = state,
                onValueChange = {},
                readOnly = true,
                label = { Text("منى") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = stateExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(expanded = stateExpanded, onDismissRequest = { stateExpanded = false }) {
                stateOptions.forEach {
                    DropdownMenuItem(text = { Text(it) }, onClick = {
                        state = it
                        stateExpanded = false
                    })
                }
            }
        }

        OutlinedTextField(value = supervisor, onValueChange = { supervisor = it }, label = { Text("اسم المشرف") }, modifier = Modifier.fillMaxWidth())
        val context = LocalContext.current

        Button(
            onClick = {
                sendDataToSheet(
                    sheet = "اصحاب_الاعذار",
                    params = mapOf(
                        "name" to name,
                        "disease_name" to disease,
                        "gender" to gender,
                        "hotel_name" to hotel,
                        "government" to government,
                        "level" to level,
                        "state" to state,
                        "supervisor_name" to supervisor
                    ),
                    onSuccess = {
                        Toast.makeText(context, "تم الإرسال بنجاح", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = {
                        Toast.makeText(context, "فشل في الإرسال", Toast.LENGTH_SHORT).show()
                    }

                )
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("إرسال")
        }
    }
}
