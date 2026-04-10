# 🎬 MOVIX - Movie Ticket Booking App

Chào mừng bạn đến với **MOVIX**, ứng dụng đặt vé xem phim hiện đại được xây dựng trên nền tảng Android (Kotlin) và kết nối trực tiếp với hệ sinh thái Firebase.


### 🎞️ Thư viện ảnh bổ sung
<p align="center">
  
  <img src="app/Image/z7710456852493_069ac4a92da4ee64bb295992d5d453c4.jpg" width="180">
  
  <img src="app/Image/z7710456864768_b5f7c45c310e97ebfbe4249e757e6166.jpg" width="180">
  <img src="app/Image/z7710456880565_7b5e5e93d3ae647e703f7eddfeee9f8e.jpg" width="180">
  <img src="app/Image/z7710456883950_0f4d31e078e34113f51200b490d0ddfe.jpg" width="180">
  <img src="app/Image/z7710464466295_51646fba2c87014a3041beff46344606.jpg" width="180">
  <img src="app/Image/z7710456903096_2e1e0c9ac2092c6124fe938430bccfcb.jpg" width="180">

  <img src="app/Image/z7710465584330_4a1be443ccf43903a35de9a0b1023d61.jpg" width="180">
  <img src="app/Image/z7710469357838_45b7c71c9cf601fcbc4583035fbe3e64.jpg" width="180">
  <img src="app/Image/z7710470081665_fc0e3c261462f1fa089b0a13c6a1793c.jpg" width="180">
  <img src="app/Image/z7710471783030_88a846241cf3a4fc8750ffc49af51374.jpg" width="180">
</p>

## ✨ Tính năng nổi bật

*   **🔐 Xác thực người dùng**: Đăng ký và đăng nhập bảo mật qua Firebase Authentication (Email/Password).
*   **📱 Giao diện Netflix Style**: Thiết kế Dark Theme hiện đại, sang trọng với hiệu ứng Grid Layout 2 cột.
*   **🎬 Danh sách phim Realtime**: Tự động đồng bộ danh sách phim, poster và đánh giá từ Cloud Firestore.
*   **🎫 Đặt vé nhanh chóng**: Lưu thông tin vé đặt trực tiếp vào database với đầy đủ chi tiết (Movie, Seat, Time).
*   **🔔 Thông báo đẩy (FCM)**: Tích hợp Firebase Cloud Messaging để gửi thông báo nhắc nhở lịch chiếu phim.
*   **🖼️ Hiển thị ảnh mượt mà**: Sử dụng thư viện Glide để tối ưu hóa việc tải Poster phim từ URL.

## 🛠 Công nghệ sử dụng

*   **Language**: [Kotlin](https://kotlinlang.org/)
*   **Database**: [Cloud Firestore](https://firebase.google.com/docs/firestore)
*   **Authentication**: [Firebase Auth](https://firebase.google.com/docs/auth)
*   **Cloud Messaging**: [FCM](https://firebase.google.com/docs/cloud-messaging)
*   **UI Components**: Material 3, CardView, ConstraintLayout, RecyclerView (GridLayout).
*   **Library**: Glide (Image Loading), ViewBinding.

## 🚀 Hướng dẫn cài đặt

Để chạy được dự án này, bạn cần thực hiện các bước sau:

1.  **Clone dự án**: Tải code về máy và mở bằng Android Studio.
2.  **Cấu hình Firebase**:
    *   Truy cập [Firebase Console](https://console.firebase.google.com/).
    *   Tải file `google-services.json` và chép vào thư mục `app/`.
3.  **Bật các dịch vụ trên Firebase**: Bật Auth (Email/Pass) và Firestore Database.
4.  **Chạy ứng dụng**: Nhấn nút **Run** (Play) trong Android Studio.

## 📂 Cấu trúc thư mục chính

*   `LoginActivity.kt`: Xử lý đăng ký & đăng nhập.
*   `MainActivity.kt`: Hiển thị danh sách phim và xử lý đặt vé.
*   `MovieAdapter.kt`: Quản lý hiển thị item phim trong danh sách.
*   `MyFirebaseMessagingService.kt`: Xử lý nhận thông báo đẩy từ Firebase.
*   `Models.kt`: Khai báo cấu trúc dữ liệu `Movie` và `Ticket`.

---

