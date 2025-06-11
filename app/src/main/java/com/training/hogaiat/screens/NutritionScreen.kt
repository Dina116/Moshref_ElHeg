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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen() {
    var government by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var meal by remember { mutableStateOf("فطار") }
    var mealNumber by remember { mutableStateOf("") }

    val mealOptions = listOf("فطار", "غداء", "عشاء")
    var mealExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text("التغذية", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = government,
            onValueChange = { government = it },
            label = { Text("المحافظة") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = day,
            onValueChange = { day = it },
            label = { Text("اليوم") },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown لاختيار الوجبة
        ExposedDropdownMenuBox(
            expanded = mealExpanded,
            onExpandedChange = { mealExpanded = !mealExpanded }
        ) {
            OutlinedTextField(
                value = meal,
                onValueChange = {},
                readOnly = true,
                label = { Text("الوجبة") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mealExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = mealExpanded,
                onDismissRequest = { mealExpanded = false }
            ) {
                mealOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            meal = option
                            mealExpanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = mealNumber,
            onValueChange = { mealNumber = it },
            label = { Text("عدد الوجبات") },
            modifier = Modifier.fillMaxWidth()
        )
        val context = LocalContext.current
        Button(
            onClick = {
                sendDataToSheet(
                    sheet = "التغذية",
                    params = mapOf(
                        "government" to government,
                        "day" to day,
                        "meal" to meal,
                        "meal_number" to mealNumber
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
