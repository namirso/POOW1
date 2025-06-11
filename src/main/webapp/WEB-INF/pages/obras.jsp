<%@page contentType="text/html; charset=UTF-8"  language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html lang="pt-BR">
<head>
  <title>Obras</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
  <style>
    body {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: flex-start;
      min-height: 100vh;
      padding-top: 56px; /* height of navbar for spacing */
    }
    main {
      width: 50%;
      margin-top: 20px;
    }
  </style>
</head>
<body class="bg-body-secondary">
<nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
  <div class="container-fluid">
    <h2 class="navbar-brand">REVIEWS</h2>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="menu">Início</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="obras">Obras</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="tipos">Tipos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="usuarios">Usuários</a>
        </li>
        <li class="nav-item">
          <button type="button" class="nav-link btn btn-outline-danger" href="login">Sair</button>
        </li>
      </ul>
    </div>
  </div>
</nav>

<main class="card">
  <div class="p-3">
    <h1 class="text-center">Obras</h1>
    <form action="obras" method="post" class="mb-4 p-3" accept-charset="UTF-8">
      <c:choose>
        <c:when test="${obra.id != null}">
          <h2>Editar Obra</h2>
          <input type="hidden" name="idobra" value="${obra.id}">
        </c:when>
        <c:otherwise>
          <h2>Adicionar Obra</h2>
          <input type="hidden" name="idobra" value="0">
        </c:otherwise>
      </c:choose>

      <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" placeholder="Nome" name="nome" required value="${obra.nome}">
      </div>
      <div class="mb-3">
        <label for="tipo" class="form-label">Tipo</label>
        <select class="form-select" name="tipo" required>
          <option value="" disabled selected>Selecione o tipo</option>
          <c:forEach var="tipo" items="${tipos}">
            <option value="${tipo.id}"  <c:if test="${obra.tipo.id == tipo.id}">selected</c:if>>${tipo.nome}</option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label for="direcao" class="form-label">Direção</label>
        <input type="text" class="form-control" placeholder="Direção" name="direcao" required value="${obra.direcao}">
      </div>

      <c:choose>
        <c:when test="${obra.id != null}">
          <input type="submit" class="btn btn-success" value="Alterar" name="gravar">
        </c:when>
        <c:otherwise>
          <input type="submit" class="btn btn-success" value="Cadastrar" name="gravar">
        </c:otherwise>
      </c:choose>
    </form>

    <c:if test="${not empty msg}">
      <h2 class="text-center text-success">${msg}</h2>
    </c:if>

    <div>
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>Nome</th>
          <th>Tipo</th>
          <th>Direção</th>
          <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obra" items="${obras}">
          <tr>
            <td>${obra.nome}</td>
            <td>${obra.tipo}</td>
            <td>${obra.direcao}</td>
            <td>
              <a href="obras?opcao=editar&&info=${obra.id}" class="btn btn-secondary btn-sm me-2">Editar</a>
              <a href="obras?opcao=excluir&&info=${obra.id}" class="btn btn-danger btn-sm">Excluir</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

</main>

</body>
</html>