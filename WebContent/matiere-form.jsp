<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Ajouter des Matieres</title>
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
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Etudiant</a></li>
                <li class="nav-item"><a class="nav-link" href="list">Matiere</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Note</a></li>
            </ul>
        </div>
    </div>
</nav>

<center>
    <h1 class="display-4">Ajouter Matiere</h1>
</center>
<div class="container mt-5 d-flex justify-content-center">
    <form action="${matiere != null ? 'update' : 'insert'}" method="post">
        <table class="table table-success table-striped table-bordered text-center" style="width: 40%;">
            <c:if test="${matiere != null}">
                <input type="hidden" name="idMatiere" value="${matiere.idMatiere}" />
            </c:if>

            <tr>
                <th><label for="nomMatiere">Matiere:</label></th>
                <td>
                    <input type="text" id="nomMatiere" name="nomMatiere" size="45"
                           value="${matiere != null ? matiere.nomMatiere : ''}" required />
                </td>
            </tr>
            <tr>
                <th><label for="coefficient">Coefficient:</label></th>
                <td>
                    <input type="text" id="coefficient" name="coefficient" size="45"
                           value="${matiere != null ? matiere.coefficient : ''}" required />
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" class="btn btn-primary" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
