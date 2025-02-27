<%--
  Created by IntelliJ IDEA.
  User: mike
  Date: 2/10/25
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        form {
            background: white;
            padding: 20px;
            width: 300px;
            margin: auto;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .error {
            color: red;
            font-size: 12px;
            text-align: left;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            width: 100%;
            cursor: pointer;
            border-radius: 5px;
        }
        button:disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }
        .hidden {
            display: none;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const form = document.getElementById("registerForm");

            // Function to toggle fields based on role
            function toggleFields() {
                const role = document.getElementById("role").value;
                document.getElementById("studentFields").style.display = (role === "student") ? "block" : "none";
                document.getElementById("instructorFields").style.display = (role === "instructor") ? "block" : "none";
                validateForm(); // Validate on role change
            }

            // Function to validate fields
            function validateField(field, errorId, regex, errorMessage) {
                const errorDiv = document.getElementById(errorId);
                if (!regex.test(field.value.trim())) {
                    errorDiv.textContent = errorMessage;
                    return false;
                } else {
                    errorDiv.textContent = "";
                    return true;
                }
            }

            // Function to validate the whole form
            function validateForm() {
                let isValid = true;

                isValid &= validateField(document.getElementById("firstName"), "firstNameError", /^.{3,50}$/, "First name must be 3-50 characters");
                isValid &= validateField(document.getElementById("lastName"), "lastNameError", /^.{3,50}$/, "Last name must be 3-50 characters");
                isValid &= validateField(document.getElementById("email"), "emailError", /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, "Invalid email format");
                isValid &= validateField(document.getElementById("password"), "passwordError", /^.{8,}$/, "Password must be at least 8 characters");

                const role = document.getElementById("role").value;
                if (role === "student") {
                    isValid &= validateField(document.getElementById("className"), "classError", /^.{2,50}$/, "Class name is required");
                    isValid &= document.getElementById("dob").value !== "";
                    document.getElementById("dobError").textContent = document.getElementById("dob").value ? "" : "Date of birth is required";
                } else if (role === "instructor") {
                    isValid &= validateField(document.getElementById("departmentName"), "deptError", /^.{3,50}$/, "Department name must be 3-50 characters");
                }

                document.getElementById("submitBtn").disabled = !isValid;
            }

            // Attach validation to input fields
            form.addEventListener("input", function (event) {
                validateForm();
            });

            // Attach event listener to role selection
            document.getElementById("role").addEventListener("change", toggleFields);
        });
        // Add logs
        document.getElementById("registerForm").addEventListener("submit", function (e) {
            console.log("Form is being submitted!");
        });
    </script>
</head>
<body>
<h2>Register</h2>
<form id="registerForm" action="register" method="post">
    <input type="text" id="firstName" name="firstName" placeholder="First Name" required>
    <div class="error" id="firstNameError"></div>

    <input type="text" id="lastName" name="lastName" placeholder="Last Name" required>
    <div class="error" id="lastNameError"></div>

    <input type="email" id="email" name="email" placeholder="Email" required>
    <div class="error" id="emailError"></div>

    <input type="password" id="password" name="password" placeholder="Password" required>
    <div class="error" id="passwordError"></div>

    <select name="role" id="role" required>
        <option value="">Select Role</option>
        <option value="student">Student</option>
        <option value="instructor">Instructor</option>
    </select>

    <!-- Student Fields (Hidden by default) -->
    <div id="studentFields" class="hidden">
        <input type="text" id="className" name="className" placeholder="Class Name">
        <div class="error" id="classError"></div>

        <input type="date" id="dob" name="dob">
        <div class="error" id="dobError"></div>
    </div>

    <!-- Instructor Fields (Hidden by default) -->
    <div id="instructorFields" class="hidden">
        <input type="text" id="departmentName" name="departmentName" placeholder="Department Name">
        <div class="error" id="deptError"></div>
    </div>

    <button type="submit" id="submitBtn" disabled>Register</button>
</form>
<p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>
