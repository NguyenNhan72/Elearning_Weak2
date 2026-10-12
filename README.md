Dự án này bao gồm 4 bài tập thực hành (xử lý Nullable, vòng lặp For, câu lệnh điều kiện If/Else)

Bài 1: Vòng lặp for và kiểu nullable

Mô tả: Xây dựng màn hình danh sách sinh viên (StudentListScreen). Dữ liệu bao gồm các trường có thể bị thiếu (email, số điện thoại). Ở cuối danh sách, hiển thị tổng số sinh viên và đếm số lượng sinh viên bị thiếu email.
Mục tiêu:

Làm quen với data class trong Kotlin chứa thuộc tính nullable (String?).

Sử dụng vòng lặp for để đếm và render các component StudentCard.

Sử dụng toán tử Elvis ?: để xử lý an toàn dữ liệu null.

🛠 Các hàm / Logic chính:

data class Student(val name: String, val email: String?, val phone: String?): Khai báo cấu trúc dữ liệu.

Toán tử Elvis ?:: student.email ?: "Chưa cập nhật". Trả về chuỗi mặc định nếu email bị null.

Logic đếm: Vòng lặp for (student in students) kết hợp if (student.email == null) để tăng biến đếm missingEmailCount.

Bài 2: Câu lệnh if / else

Mô tả: Xây dựng màn hình XepLoaiHocLucScreen để xếp loại học lực từ điểm số và hiển thị màu sắc tương ứng. Giao diện có thêm phần kiểm tra danh sách điểm mẫu.
Mục tiêu:

Tách biệt logic tính toán (xếp loại, chọn màu) ra thành các hàm riêng rẽ.

Sử dụng cấu trúc if / else if / else để kiểm tra điều kiện rẽ nhánh.

🛠 Các hàm / Logic chính:

fun layChuXepLoai(diem: Double): String: Trả về chuỗi (Giỏi, Khá, Yếu...) dựa vào các mốc điểm.

fun layMauChu(diem: Double): Color & fun layMauNen(diem: Double): Color: Áp dụng logic tương tự để trả về đối tượng Color cho giao diện Jetpack Compose.

Bài 3: Tổng hợp for + if + nullable

Mô tả: Xây dựng ứng dụng BangDiemApp hiển thị bảng điểm lớp học. Ứng dụng tính toán điểm trung bình lớp, số sinh viên giỏi, số sinh viên chưa có điểm (điểm null) và điểm cao nhất.
Mục tiêu:

Kết hợp tính toán phức tạp sử dụng biến tạm, vòng lặp và câu lệnh điều kiện.

Format dữ liệu số học (làm tròn số).

🛠 Các hàm / Logic chính:

Logic thống kê (trong block @Composable): Duyệt danh sách danhSachSV bằng for. Sử dụng kiểm tra kiểu if (sv.score != null) để cộng dồn tính tongDiem, tìm max diemCaoNhat, đếm số học sinh giỏi và số người chưa có điểm.

Hàm làm tròn: (diemTrungBinh * 100).roundToInt() / 100.0 để làm tròn điểm trung bình lấy 2 chữ số thập phân.
