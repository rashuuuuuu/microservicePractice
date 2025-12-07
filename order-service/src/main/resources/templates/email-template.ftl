<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Verification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .email-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            background-color: #007bff;
            color: #ffffff;
            padding: 10px;
            text-align: center;
            border-radius: 8px 8px 0 0;
        }
        .header h1 {
            margin: 0;
            font-size: 24px;
        }
        .content {
            padding: 20px;
        }
        .content h2 {
            color: #333333;
            font-size: 20px;
        }
        .content p {
            color: #666666;
            line-height: 1.5;
        }
        .verify-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #ffffff;
            text-decoration: none;
            border-radius: 4px;
        }
        .footer {
            text-align: center;
            padding: 20px;
            color: #999999;
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="email-container">
    <div class="header">
        <h1>Email Verification</h1>
    </div>
    <div class="content">
        <h2>Hello, ${name}!</h2>
        <p>Thank you for registering. Please use the following OTP to verify your email address:</p>
        <h3 style="text-align: center;">${input}</h3>
        <p>If you did not register for an account, please ignore this email.</p>
    </div>
    <div class="footer">
        <p>&copy; 2024 CosmoTech Intl. All rights reserved.</p>
    </div>
</div>
</body>
</html>
