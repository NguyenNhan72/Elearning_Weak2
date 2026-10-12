package com.example.calculate_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiaoDienTinhToan()
        }
    }
}

@Composable
fun GiaoDienTinhToan() {
    //quản lý trạng thái
    var soThuNhat by remember {mutableStateOf("")}
    var soThuHai by remember {mutableStateOf("")}
    var ketQua by remember {mutableStateOf("Kết quả: ")}

    //cột dọc chứa toàn bộ các thành phần
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //tiêu đề
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Thực hành 03", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(30.dp))

        //ô nhập số thứ 1
        OutlinedTextField(
            value = soThuNhat,
            onValueChange = {soThuNhat = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        //phép tính
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            //cộng
            Button(
                onClick = {
                    val so1 = soThuNhat.toDoubleOrNull() ?: 0.0
                    val so2 = soThuHai.toDoubleOrNull() ?: 0.0
                    ketQua = "Kết quả: ${so1 + so2}"
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text("+")
            }
            //trừ
            Button(
                onClick = {
                    val so1 = soThuNhat.toDoubleOrNull() ?: 0.0
                    val so2 = soThuHai.toDoubleOrNull() ?: 0.0
                    ketQua = "Kết quả: ${so1 - so2}"
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFBC02D))
            ) {
                Text("-", color = Color.Black)
            }
            //nhân
            Button(
                onClick = {
                    val so1 = soThuNhat.toDoubleOrNull() ?: 0.0
                    val so2 = soThuHai.toDoubleOrNull() ?: 0.0
                    ketQua = "Kết quả: ${so1 * so2}"
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E35B1))
            ) {
                Text("*")
            }
            //chia
            Button(
                onClick = {
                    val so1 = soThuNhat.toDoubleOrNull() ?: 0.0
                    val so2 = soThuHai.toDoubleOrNull() ?: 0.0
                    if(so2 == 0.0) {
                        ketQua = "Kết quả: Lỗi chia cho 0"
                    } else {
                        ketQua = "kết quả: ${so1 / so2}"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF212121))
            ) {
                Text("/")
            }
        }
        //ô nhập số thứ hai
        OutlinedTextField(
            value = soThuHai,
            onValueChange = {soThuHai = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))

        //hiển thị kết quả
        Text(text = ketQua,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start))
    }
}


