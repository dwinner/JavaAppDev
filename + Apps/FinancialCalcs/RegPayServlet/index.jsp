<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <form name="Form1" action="http://localhost:8080/RegPayServlet/RegPayS">
            <b>Enter amount to finance:</b>
            <input type="text" name="amount" size="12" value="" /><br />
            <b>Enter term in years:</b>
            <input type="text" name="period" size="12" value="" /><br />
            <b>Enter interest rate:</b>
            <input type="text" name="rate" size="12" value="" /><br />
            <b>Monthly Payment:</b>
            <input readonly="true" type="text" name="payment" size="12" value="" />
            <br />
            <p>
                <input type="submit" value="Submit" />
            </p>
        </form>
    </body>
</html>