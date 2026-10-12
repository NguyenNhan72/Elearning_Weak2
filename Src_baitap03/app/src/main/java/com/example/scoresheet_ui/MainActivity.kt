package com.example.scoresheet_ui

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
import kotlin.math.roundToInt

//class Student gồm String name và Double? score
data class Student(val name: String, val score: Double?)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BangDiemApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BangDiemApp() {
    val danhSachSV = listOf(
        Student("Nguyễn Văn A", 9.0),
        Student("Trần Thị B", 7.2),
        Student("Lê Minh C", null),
        Student("Phạm Quốc D", 4.5),
        Student("Võ Thanh E", 5.8)
    )

    //
    var tongDiem = 0.0
    var soSVCoDiem = 0
    var soSVGioi = 0
    var soSVChuaCoDiem = 0
    var diemCaoNhat = 0.0

    //tính toán bằng for if
    for (sv in danhSachSV) {
        if (sv.score != null) {
            // Có điểm
            tongDiem += sv.score
            soSVCoDiem++

            if (sv.score >= 8.0) {
                soSVGioi++
            }
            if (sv.score > diemCaoNhat) {
                diemCaoNhat = sv.score
            }
        } else {
            //null
            soSVChuaCoDiem++
        }
    }

    //tính điểm trung bình
    var diemTrungBinh = 0.0
    if (soSVCoDiem > 0) {
        diemTrungBinh = tongDiem / soSVCoDiem
        diemTrungBinh = (diemTrungBinh * 100).roundToInt() / 100.0
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bảng điểm lớp", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF008080))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            //hiển thị đtb
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF008080), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Điểm trung bình lớp", color = Color.White, fontSize = 14.sp)
                    Text("$diemTrungBinh", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.Gray, thickness = 1.dp) // Đường kẻ ngang
                    Spacer(modifier = Modifier.height(16.dp))

                    // Row chia 3 cột ở dưới
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Giỏi", color = Color.White, fontSize = 12.sp)
                            Text("$soSVGioi", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Chưa có điểm", color = Color.White, fontSize = 12.sp)
                            Text("$soSVChuaCoDiem", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Cao nhất", color = Color.White, fontSize = 12.sp)
                            Text("$diemCaoNhat", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("DANH SÁCH - ${danhSachSV.size} SINH VIÊN", color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))

            //for hiển thị tên, điểm, xếp loại
            for (sv in danhSachSV) {
                //xếp loại và màu sắc
                var xepLoai = ""
                var mauChu = Color.Gray
                var diemHienThi = ""

                if (sv.score == null) {
                    xepLoai = "Chưa có điểm"
                    diemHienThi = "—"
                    mauChu = Color.Gray
                } else {
                    diemHienThi = sv.score.toString()
                    if (sv.score >= 8.0) {
                        xepLoai = "Giỏi"
                        mauChu = Color(0xFF4CAF50)
                    } else if (sv.score >= 6.5) {
                        xepLoai = "Khá"
                        mauChu = Color(0xFF2196F3)
                    } else if (sv.score >= 5.0) {
                        xepLoai = "Trung bình"
                        mauChu = Color(0xFFD4AF37)
                    } else {
                        xepLoai = "Yếu"
                        mauChu = Color(0xFFF44336)
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //tên và xếp loại bên trái
                        Column {
                            Text(sv.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(xepLoai, color = mauChu, fontSize = 14.sp)
                        }
                        //điểm số bên phải
                        Text(diemHienThi, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}