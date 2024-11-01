<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Gestion des Matieres </title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

	
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Bonjour</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Etudiant</a>
        </li>

        <li class="nav-item">
          <a class="nav-link " href="list" >Matiere</a>
        </li>
		<li class="nav-item">
          <a class="nav-link" href="#">Note</a>
        </li>
      </ul>

    </div>
	
  </div>
</nav>

<form class="d-flex justify-content-center align-items-center flex-column" style="padding: 20px;">

    <div class="mb-2">
        <a class="btn btn-primary" href="new" role="button">Ajouter Matiere</a>
    </div>
    
    <!-- Champ de recherche -->
    <div class="d-flex align-items-center">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" style="width: 200px;">
        <button class="btn btn-outline-success" type="submit">Search</button>
    </div>
</form>


        </h2>
	</center>
<div class="container">
           <table class="table table-success table-striped text-center " border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Matiere</th>
                <th>coefficient</th>
                <th colspan="2">Actions</th>
            </tr>
            <c:forEach var="matiere" items="${listMatiere}">
                <tr>
                    <td><c:out value="${matiere.idMatiere}" /></td>
                    <td><c:out value="${matiere.nomMatiere}" /></td>
                    <td><c:out value="${matiere.coefficient}" /></td>
<td>
    <a href="${pageContext.request.contextPath}/edit?idMatiere=${matiere.idMatiere}">Edit</a>
</td>
<td>
    <a href="${pageContext.request.contextPath}/delete?idMatiere=${matiere.idMatiere}">Delete</a>
</td>

                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
