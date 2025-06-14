# ⚡ ElectricBillEase

ElectricBillEase is a simple and intuitive Android application that helps users estimate their monthly electricity bills based on the TNB (Tenaga Nasional Berhad) block tariff system in Malaysia. The app also supports saving records, viewing history, and applying rebate discounts.

![Logoname](https://github.com/user-attachments/assets/d8cbc333-2e8e-42ba-ab49-ffe2be5adb23)


---

## 📱 Features

- Input monthly usage in kWh
- Apply rebate percentage using a SeekBar
- Automatically calculates charges using TNB block tariff
- Save calculated bills locally using Room Database
- View and manage bill history
- Instruction page showing block tariff details
- Developer profile/About section
- Light & Dark theme support
- Responsive and beginner-friendly UI

---

![poster electric](https://github.com/user-attachments/assets/5d6d71e5-bd89-42de-b623-b50f2f0b6c17)

---

## 📊 Tariff Reference

Electricity is charged based on blocks:

```
1 - 200 kWh       → 21.8 sen/kWh  
201 - 300 kWh     → 33.4 sen/kWh  
301 - 600 kWh     → 51.6 sen/kWh  
601+ kWh          → 54.6 sen/kWh
```

---

## 🛠️ Tech Stack

- Java
- Android Studio (API 24+)
- Room Persistence Library
- Material Design 3
- XML Layouts (Dark Theme compatible)

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (Electric Eel or newer)
- Java 11+
- Android device or emulator

### Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Amarshafiqz/electricbillease.git
   ```

2. Open in Android Studio:
   ```
   File > Open > Select Project Directory
   ```

3. Build the project:
   ```
   Build > Rebuild Project
   ```

4. Run on emulator or connected device:
   ```
   Run > Run 'app'
   ```

---

## 📂 Project Structure

```
├── app
│   ├── java
│   │   └── com.example.electricbillease
│   │       ├── MainActivity.java
│   │       ├── AboutActivity.java
│   │       ├── HistoryActivity.java
│   │       ├── DetailActivity.java
│   │       ├── InstructionActivity.java
│   │       └── database/
│   │           ├── AppDatabase.java
│   │           ├── BillDao.java
│   │           └── BillRecord.java
│   └── res
│       ├── layout/
│       ├── values/
│       └── drawable/
```

---

## 👤 Developed By

**Muammar Shafiq Bin Raslan Adli**  
Bachelor of Information Technology  
Student ID: 2023141477

---


## 📬 Feedback

Feel free to submit issues or suggestions via GitHub Issues or pull requests.
