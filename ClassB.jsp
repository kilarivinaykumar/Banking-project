
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ClassB</title>
</head>
<body>
<h1>
<%@ page import="banking.*"%>
<jsp:useBean id="aobj" class="banking.age"/>
<jsp:setproperty name="aobj" property="age" value="23"/>
<jsp:getproperty name="aobj" property="age" />
</h1>

</body>
</html>