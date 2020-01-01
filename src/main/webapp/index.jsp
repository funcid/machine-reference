<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h3>Создать</h3>
  <form name="createCarForm" method="post" action="createCar">
    Название авто: <input type="text" name="name"> <br>
    Производитель: <input type="text" name="company"> <br>
    <input type="submit" value="Создать" >
  </form>
  <hr>
  <h3>Найти</h3>
  <form name="findCarsByCompanyForm" method="post" action="findCarsByCompany">
    Имя марки:    <input type="text" name="name">
    <input type="submit" value="Поиск" >
  </form>
  <form name="findCarForm" method="post" action="findCar">
    Номер авто: <input type="text" name="id">
    <input type="submit" value="Поиск" >
  </form>
  </body>
</html>
