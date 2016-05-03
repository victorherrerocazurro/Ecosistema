<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Encuentra tu pareja ideal!</h1>
        <form action="ControlBusquedas">
        	<table>
        		<tr><td>Nombre:</td><td><input type="text" name="nombre"/></td></tr>
        		<tr><td>Edad:</td><td><input type="text" name="edad"/></td></tr>
        		<tr><td>Altura:</td><td><input type="text" name="altura"/></td></tr>
        		<tr><td>Sexo:</td><td><input type="text" name="sexo"/></td></tr>
        		<tr><td colspan="2"><input type="submit" name="buscar"/></td></tr>
        	</table>
        
        </form>
    </body>
</html>
