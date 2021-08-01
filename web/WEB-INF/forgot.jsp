<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password Page</title>
    </head>
    <body>
        <h1>Forgot Password</h1
        <p>Please enter your email address to retrieve your password.</p>
        <form method="POST" action="">
            <label>Email Address:</label><input type="text" name="forgot_email" value=""><br/>
            <input type="submit" value="Submit">
            <p>${userMessage}</p>
        </form>
    </body>
</html>
