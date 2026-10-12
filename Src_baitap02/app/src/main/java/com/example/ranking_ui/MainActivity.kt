package com.example.ranking_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XepLoaiHocLucScreen()
        }
    }
}

// 2.hàm xếp loại học lực
fun layChuXepLoai(diem: Double): String {
    if (diem < 0.0 || diem > 10.0) {
        return "Không hợp lệ"
    } else if (diem >= 8.0) {
        return "Giỏi"
    } else if (diem >= 6.5) {
        return "Khá"
    } else if (diem >= 5.0) {
        return "Trung bình"
    } else {
        return "Yếu"
    }
}

//hàm xếp loại học lực theo màu chữ
fun layMauChu(diem: Double): Color {
    if (diem < 0.0 || diem > 10.0) return Color.Gray
    else if (diem >= 8.0) return Color(0xFF2E7D32)
    else if (diem >= 6.5) return Color(0xFF1565C0)
    else if (diem >= 5.0) return Color(0xFFD4AF37)
    else return Color(0xFFC62828)
}

fun layMauNen(diem: Double): Color {
    if (diem < 0.0 || diem > 10.0) return Color(0xFFEEEEEE)
    else if (diem >= 8.0) return Color(0xFFE8F5E9)
    else if (diem >= 6.5) return Color(0xFFE3F2FD)
    else if (diem >= 5.0) return Color(0xFFF9F1D1)
    else return Color(0xFFFFEBEE)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XepLoaiHocLucScreen() {
    val diemHienTai = 8.5

    //khai báo kiểm tra thêm
    val danhSachDiemTest = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Xếp loại học lực", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF008080))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //điểm 8.5
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Điểm trung bình", color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = diemHienTai.toString(), fontSize = 48.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(layMauNen(diemHienTai), shape = RoundedCornerShape(8.dp))
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = layChuXepLoai(diemHienTai),
                            color = layMauChu(diemHienTai),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            //kiểm tra thêm
            Text(text = "KIỂM TRA THÊM", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    for (diem in danhSachDiemTest) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val textĐiểm = if (diem % 1 == 0.0) "Điểm ${diem.toInt()}" else "Điểm $diem"
                            Text(text = textĐiểm)

                            Box(
                                modifier = Modifier
                                    .background(layMauNen(diem), shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = layChuXepLoai(diem),
                                    color = layMauChu(diem),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        if (diem != danhSachDiemTest.last()) {
                            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
                        }
                    }
                }
            }
        }
    }
}