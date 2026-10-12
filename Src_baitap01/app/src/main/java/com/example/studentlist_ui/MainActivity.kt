package com.example.studentlist_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.listOf

//class Student
data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentListScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen() {
    //list 5 sv
    val students = listOf(
        Student("Nguyễn Văn A", "a@sv.edu.vn", "0901 234 567"),
        Student("Trần Thị B", null, "0912 888 999"),
        Student("Lê Minh C", "c@sv.edu.vn", null),
        Student("Phạm Quốc D", null, null),
        Student("Võ Thanh E", "e@sv.edu.vn", "0933 111 222")
    )

    //đếm số sv thiếu email
    var missingEmailCount = 0
    for (student in students) {
        if (student.email == null) {
            missingEmailCount++
        }
    }

    // Giao diện chính
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách sinh viên", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF008080))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            //thẻ sv
            for (student in students) {
                StudentCard(student)
                Spacer(modifier = Modifier.height(12.dp)) // Tương đương SizedBox bên Flutter
            }

            Spacer(modifier = Modifier.height(16.dp))

            //tổng kết
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEBF0F5), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tổng: ${students.size} sinh viên", fontWeight = FontWeight.Bold)
                Text(text = "Thiếu email: $missingEmailCount", color = Color.Red, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun StudentCard(student: Student) {
    //null toán tử ?:
    val emailText = student.email ?: "Chưa cập nhật"
    val phoneText = student.phone ?: "Chưa cập nhật"

    /*đỏ chưa có
    * xám đã có*/
    val emailColor = if (student.email == null) Color.Red else Color.Gray
    val phoneColor = if (student.phone == null) Color.Red else Color.Gray

    //thông tin sv
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(text = student.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email: $emailText", color = emailColor, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "SĐT: $phoneText", color = phoneColor, fontSize = 14.sp)
    }
}