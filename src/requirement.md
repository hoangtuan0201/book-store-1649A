## 🧭 1️⃣ MỤC TIÊU DỰ ÁN

Xây dựng **một hệ thống Bookstore dạng console** có khả năng:

| Chức năng | Mô tả |
| --- | --- |
| 🧾 **Đặt hàng (Place Order)** | Người dùng nhập thông tin khách hàng và danh sách sách → hệ thống tạo 1 `Order` và đưa vào **Queue** (chờ xử lý). |
| 🔄 **Xử lý đơn hàng (Process Orders)** | Nhân viên lấy đơn hàng ra khỏi **Queue (FIFO)** → sắp xếp danh sách sách trong đơn hàng bằng **Sorting Algorithm (MergeSort)**. |
| 🔍 **Tra cứu đơn hàng (Search Order)** | Người dùng nhập mã đơn → hệ thống tìm kiếm bằng **Searching Algorithm (BinarySearch hoặc LinearSearch)**. |
| 📦 **Cập nhật trạng thái (Mark as Shipped)** | Khi đơn hàng đã xử lý xong → chuyển trạng thái sang “Shipped”. |
| 📊 **Thống kê (Optional)** | Hiển thị tổng số đơn hàng, doanh thu, v.v. |

---

## 🧱 2️⃣ CÁC THÀNH PHẦN CHÍNH

| Thành phần | Mô tả | Vai trò trong hệ thống |
| --- | --- | --- |
| **Book.java** | Model đại diện cho sách | Chứa `title`, `author`, `price` |
| **Order.java** | Model đại diện cho đơn hàng | Gồm `orderId`, `customerName`, `List<Book>`, `isShipped` |
| **Queue.java** | Cấu trúc dữ liệu hàng đợi FIFO | Quản lý danh sách đơn hàng chờ xử lý |
| **MergeSort.java** | Thuật toán sắp xếp | Dùng để sắp xếp danh sách sách trong từng đơn hàng |
| **BinarySearch.java** | Thuật toán tìm kiếm | Dùng để tra cứu đơn hàng theo ID |
| **Main.java** | Giao diện console | Nhận input người dùng và điều hướng toàn hệ thống |

---

## 🔄 3️⃣ LUỒNG XỬ LÝ TỔNG QUAN

### **Luồng 1 — Tạo đơn hàng mới**

```
[User Input] → [Nhập tên khách, địa chỉ, danh sách sách]
              ↓
      [Tạo đối tượng Order]
              ↓
      [Enqueue vào hàng đợi Queue<Order>]
              ↓
      [In thông báo: “Đơn hàng đã được thêm vào hàng đợi”]

```

👉 **Data Structure chính:** `Queue`

👉 **Mục đích:** Lưu trữ đơn hàng theo thứ tự khách đặt (FIFO).

---

### **Luồng 2 — Xử lý đơn hàng**

```
[Nhân viên chọn “Process Next Order”]
              ↓
     [Dequeue đơn hàng từ hàng đợi]
              ↓
 [Sắp xếp danh sách Book trong Order bằng MergeSort]
              ↓
 [In danh sách sách đã sắp xếp + tổng giá tiền]
              ↓
 [Cập nhật trạng thái isShipped = true]
              ↓
 [Lưu vào danh sách lịch sử đã xử lý (Stack hoặc List)]

```

👉 **Data Structures:**

- `Queue<Order>` → lấy đơn hàng ra theo thứ tự FIFO
- `MergeSort` → sắp xếp danh sách sách trong đơn

---

### **Luồng 3 — Tra cứu đơn hàng**

```
[User Input] → [Nhập mã đơn hàng cần tra cứu]
              ↓
     [Duyệt qua danh sách đơn hàng (đã xử lý hoặc trong Queue)]
              ↓
     [Tìm đơn bằng BinarySearch hoặc LinearSearch]
              ↓
     [In thông tin đơn hàng nếu tìm thấy]

```

👉 **Data Structure:** `ArrayList<Order>` hoặc `Queue<Order>`

👉 **Thuật toán:** `BinarySearch` (nếu danh sách đã sắp xếp theo ID)

---

### **Luồng 4 — Hiển thị danh sách đơn hàng**

```
[Chọn "View All Orders"]
              ↓
[In tất cả đơn hàng trong hàng đợi + đã xử lý]

```

---

## 🧮 4️⃣ SƠ ĐỒ LUỒNG XỬ LÝ (FLOW DIAGRAM)

```
+---------------------+
| 1. User places order|
+----------+----------+
           |
           v
+---------------------+
| Create Order object |
+----------+----------+
           |
           v
+---------------------+
| Enqueue to Queue<Order> |
+----------+----------+
           |
           v
+---------------------+
| 2. Admin processes next order |
+----------+----------+
           |
           v
+---------------------+
| Dequeue order from queue |
+----------+----------+
           |
           v
+---------------------+
| Sort books using MergeSort |
+----------+----------+
           |
           v
+---------------------+
| Display sorted order details |
+----------+----------+
           |
           v
+---------------------+
| Mark order as Shipped |
+----------+----------+
           |
           v
+---------------------+
| Store in processed list |
+----------+----------+
           |
           v
+---------------------+
| 3. Search Order by ID |
+----------+----------+
           |
           v
+---------------------+
| BinarySearch in processed list |
+----------+----------+
           |
           v
+---------------------+
| Display result |
+---------------------+

```

---

## 🧠 5️⃣ MỐI LIÊN HỆ GIỮA CÁC THÀNH PHẦN

| Class | Gọi / Dùng | Mục đích |
| --- | --- | --- |
| `Main` | `Queue`, `Order`, `Book`, `MergeSort`, `BinarySearch` | Điều hướng toàn hệ thống |
| `Queue` | `Order` | Quản lý đơn hàng chờ |
| `MergeSort` | `Book` | Sắp xếp danh sách sách trong đơn |
| `BinarySearch` | `Order` | Tìm kiếm đơn hàng theo ID |
| `Order` | `Book` | Mỗi đơn hàng chứa nhiều sách |

---

## 🧾 6️⃣ VÍ DỤ CHUỖI HOẠT ĐỘNG THỰC TẾ

### Giả sử người dùng nhập:

```
Tên khách: Alice
Địa chỉ: 123 Main Street
Sách:
  [1] Algorithms, $50
  [2] Data Structures, $45

```

→ Hệ thống sẽ:

1. Tạo `Order(1001, "Alice", "123 Main Street", books)`
2. `enqueue(order)` vào Queue
3. Khi admin chọn “Process Orders”:
    - `dequeue()` lấy đơn Alice
    - `MergeSort.sort(bookList)`
    - In danh sách đã sắp xếp
    - Cập nhật trạng thái → shipped = true
    - Lưu vào danh sách lịch sử
4. Người dùng có thể nhập `1001` để tra cứu đơn → `BinarySearch` sẽ tìm và hiển thị thông tin.

---

## 🧩 7️⃣ TỔNG KẾT LUỒNG

| Bước | Mô tả | Thuật toán / Cấu trúc sử dụng |
| --- | --- | --- |
| 1 | Thêm đơn hàng | Queue (enqueue) |
| 2 | Xử lý đơn hàng | Queue (dequeue), MergeSort |
| 3 | Cập nhật trạng thái | Boolean flag `isShipped` |
| 4 | Tra cứu đơn hàng | BinarySearch |
| 5 | Hiển thị thống kê | Duyệt danh sách |