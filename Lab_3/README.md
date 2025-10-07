# 🧪 Selenium Test Script – Login Form

## 1) Giới thiệu
Dự án kiểm thử **form đăng nhập web** theo chủ đề.
Các trang HTML tự chứa (chạy local): `login.html`, `forgot.html`, `signup.html`.
Ngôn ngữ: **Java** · Framework: **Selenium WebDriver** (JUnit).

### Tính năng kiểm thử
1. Đăng nhập thành công với tài khoản hợp lệ  
2. Sai thông tin đăng nhập → hiển thị thông báo lỗi  
3. Bỏ trống Username/Password → hiển thị cảnh báo  
4. Link **Forgot password?** điều hướng sang `forgot.html`  
5. Link **SIGN UP** điều hướng sang `signup.html`  
6. Ba nút **Login bằng social** (Facebook, X, Google) có phản hồi thành công khi click

---

## 2) Chuẩn bị môi trường

### Yêu cầu
- **Java JDK 8+** (khuyến nghị 17+)  
- **Maven 3.8+** *(khuyến nghị, để quản lý thư viện)* hoặc IDE (IntelliJ/Eclipse/NetBeans)  
- **Google Chrome**  
- **ChromeDriver** cùng phiên bản với Chrome

> Kiểm tra nhanh:
```bash
java -version
mvn -v
```

### Cài Selenium (2 cách)

**Cách A – Maven (khuyến nghị):** thêm vào `pom.xml`
```xml
<dependencies>
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.23.0</version>
  </dependency>

  <!-- JUnit 5 (nếu chạy test dạng @Test) -->
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.3</version>
    <scope>test</scope>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.2.5</version>
      <configuration>
        <includes>
          <include>**/*Test*.java</include>
        </includes>
      </configuration>
    </plugin>
  </plugins>
</build>
```

**Cách B – Thủ công:** tải `.jar` Selenium từ trang chủ, add vào *Referenced Libraries* của IDE.

### Cấu hình ChromeDriver

1. Tải tại: https://chromedriver.chromium.org/downloads (đúng version Chrome)  
2. Giải nén, ví dụ:
   - Windows: `C:\chromedriver\chromedriver.exe`
   - macOS: `/usr/local/bin/chromedriver`
3. Trong code, đặt:
```java
System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe"); // Windows
// hoặc
System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");        // macOS/Linux
```

**Tuỳ chọn (khỏi tải driver): dùng WebDriverManager**
```xml
<dependency>
  <groupId>io.github.bonigarcia</groupId>
  <artifactId>webdrivermanager</artifactId>
  <version>5.9.2</version>
</dependency>
```
```java
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
```

---

## 3) Cấu trúc dự án
```
/project-folder
│
├── login.html          # Form Login
├── forgot.html         # Trang quên mật khẩu
├── signup.html         # Trang đăng ký
├── TestLoginForm.java  # Test Script Selenium
└── README.md
```

---

## 4) Cách chạy test

### C1) Chạy bằng IDE
- Mở dự án → mở `TestLoginForm.java` → **Run** (Java Application hoặc JUnit Test).
  (→ **Test File** nếu dùng NetBeans IDE)
- Sửa đường dẫn tới file HTML local ở `driver.get(...)`:
  - Windows: `driver.get("file:///C:/path/to/login.html");`
  - macOS:   `driver.get("file:///Users/you/path/login.html");`

### C2) Chạy bằng Maven
```bash
mvn clean test
```

---

## 5) Locator (đạt tiêu chí “viết gọn, chính xác”)
```java
By username   = By.id("username");
By password   = By.id("password");
By btnLogin   = By.id("btnLogin");
By linkForgot = By.linkText("Forgot password?");
By linkSignup = By.linkText("SIGN UP");
By btnFb      = By.id("btnFacebook");
By btnX       = By.id("btnTwitter");
By btnGg      = By.id("btnGoogle");
By msgSuccess = By.id("msg-success");
By msgError   = By.id("msg-error");
By msgSocial  = By.id("msg-social");
```
> Ưu tiên `id`, `linkText`; tránh XPath/CSS dài rối.

---

## 6) Danh sách Test Case & Kỳ vọng

| TC | Mô tả | Bước thực hiện | Kết quả mong đợi |
|---|-------|-----------------|------------------|
| TC1 | Đăng nhập thành công | Nhập `sv1@ptit.edu.vn` / `P@ssw0rd` → click **LOGIN** | Thông báo **Login success!** xuất hiện |
| TC2 | Sai thông tin | Nhập đúng username, sai password → **LOGIN** | Thông báo **Invalid credentials.** |
| TC3 | Bỏ trống trường | Để trống 1 hoặc cả 2 ô → **LOGIN** | Thông báo **Please fill all required fields.** |
| TC4 | Forgot password? | Click **Forgot password?** | Điều hướng tới `forgot.html` |
| TC5 | SIGN UP | Click **SIGN UP** | Điều hướng tới `signup.html` |
| TC6 | Social login | Click **Facebook/X/Google** | Hiện **Login successfully with ...** (không yêu cầu nhập form) |

## 👨‍💻 Thông tin
- Môn học: **Nhập môn Công nghệ Phần mềm**
- Bài tập: Lab 3 - Testing Login Form
- Thực hiện: Nguyễn Minh Khánh
- Trường: Học viện Công nghệ Bưu chính Viễn thông