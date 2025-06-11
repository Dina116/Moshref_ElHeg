package com.training.hogaiat.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupervisorTaskScreen() {
    var place by remember { mutableStateOf("") }
    var mission by remember { mutableStateOf("") }
    var supervisorName by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text("مهام المشرف", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = place,
            onValueChange = { place = it },
            label = { Text("المكان") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = mission,
            onValueChange = { mission = it },
            label = { Text("المهمة") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = supervisorName,
            onValueChange = { supervisorName = it },
            label = { Text("اسم المشرف") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state,
            onValueChange = { state = it },
            label = { Text("الحالة") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("ملاحظات") },
            modifier = Modifier.fillMaxWidth()
        )
        val context = LocalContext.current
        Button(
            onClick = {
                sendDataToSheet(
                    sheet = "مهام_المشرف",
                    params = mapOf(
                        "place" to place,
                        "mission" to mission,
                        "supervisor_name" to supervisorName,
                        "state" to state,
                        "notes" to notes
                    )
                    ,
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
