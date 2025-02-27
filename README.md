# 📚 Online Student Assignment Submission System 🚀

A **Jakarta EE-based** web application for online submission of assignments, projects, and practice exercises. This system enables **students** to submit their work before deadlines and allows **instructors** to create, manage, and review submissions efficiently.

## 🌟 Features
- ✅ **User Authentication** (Students & Instructors)
- 📂 **File Uploads** (`.pdf`, `.zip`, `.xlsx`, `.pptx`, `.txt`, `.docx`)
- 🕒 **Deadline Enforcement** for submissions
- 🔍 **Instructor Dashboard** to manage assignments & submissions
- 📜 **Student Submission History Tracking**
- 📄 **Generate Student Lists (PDF)** using iTextPDF *(optional)*
- 🌍 **Multilingual Support (Kinyarwanda & English)** *(optional)*
- 🔒 **Secure Password Hashing** using `bcrypt`
- 🚢 **Dockerized Deployment** for easy setup

## 🛠️ Tech Stack
- **Backend:** Jakarta EE (Servlets, JSP, Hibernate)
- **Frontend:** JSP, JavaScript
- **Database:** PostgreSQL
- **Security:** bcrypt for password hashing

## 📁 Project Structure
```
📂 online-assignment-submission
├── 📂 src
│   ├── 📂 main
│   │   ├── 📂 java
│   │   │   ├── controllers/    # Servlets handling logic
│   │   │   ├── models/         # Hibernate entities
│   │   │   ├── services/       # Business logic
│   │   ├── 📂 webapp
│   │   │   ├── views/          # JSP Pages
│   │   │   ├── assets/         # CSS, JS, Images
├── 📂 database
├── 📜 README.md
```

## 🚀 Quick Start
### 1️⃣ Clone the Repository
```bash
 git clone https://github.com/your-username/SubmiTrack.git
 cd SubmiTrack
```

### 3️⃣ Access the Application
- Visit `http://localhost:8080/SubmiTrack_war` in your browser.
- Login as a **Student** or **Instructor**.
- Start submitting or managing assignments!

## 🏗️ Contributing
1. Fork the repository 🍴
2. Create a feature branch `git checkout -b feature-name`
3. Commit changes `git commit -m 'Added a cool feature'`
4. Push to GitHub `git push origin feature-name`
5. Submit a Pull Request 🔥

