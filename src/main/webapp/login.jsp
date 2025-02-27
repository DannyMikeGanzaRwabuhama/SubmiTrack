<%--
  Created by IntelliJ IDEA.
  User: mike
  Date: 2/13/25
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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

        input {
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
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const form = document.getElementById("loginForm");
            const emailField = document.getElementById("email");
            const passwordField = document.getElementById("password");
            const submitBtn = document.getElementById("submitBtn");

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

            function validateForm() {
                let isValid = true;
                isValid &= validateField(emailField, "emailError", /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, "Invalid email format");
                isValid &= validateField(passwordField, "passwordError", /^.{8,}$/, "Password must be at least 8 characters");
                submitBtn.disabled = !isValid;
            }

            form.addEventListener("input", validateForm);
        });
    </script>
</head>
<body>
<h2>Login</h2>
<form id="loginForm" action="login" method="post">
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
    <div class="error"><%= error %>
    </div>
    <% } %>
    <input type="email" id="email" name="email" placeholder="Email" required>
    <div class="error" id="emailError"></div>

    <input type="password" id="password" name="password" placeholder="Password" required>
    <div class="error" id="passwordError"></div>

    <button type="submit" id="submitBtn" disabled>Login</button>
</form>
<p>Don't have an account? <a href="register.jsp">Register here</a></p>
</body>
</html>
