<%@page contentType="text/html; charset=UTF-8"  language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>LOGAR</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="icon" type="image/png" href="https://cdn-icons-png.flaticon.com/512/616/616489.png">

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap');

        body {
            background-color: #121212;
            color: #ffffff;
            font-family: 'Inter', sans-serif;
        }

        .navbar {
            background-color: #1f1f1f;
            border-bottom: 1px solid #2a2a2a;
        }

        .navbar-brand {
            font-weight: bold;
            color: #d64b00;
            font-size: 1.8rem;
            letter-spacing: 1px;
        }

        .navbar-nav .nav-link {
            color: #d1d1d1;
            transition: color 0.3s ease;
        }

        .navbar-nav .nav-link:hover,
        .navbar-nav .nav-link.active {
            color: #1DB954;
        }

        .dropdown-menu {
            background-color: #2a2a2a;
            color: #ffffff;
        }

        .dropdown-item {
            color: #ffffff;
        }

        .dropdown-item:hover {
            background-color: #1DB954;
            color: #000000;
        }

        .btn-success {
            background-color: #1DB954;
            border-color: #1DB954;
            box-shadow: 0 0 10px #1db95466;
            transition: all 0.3s ease;
        }

        .btn-success:hover {
            background-color: #17a64a;
            border-color: #17a64a;
            box-shadow: 0 0 15px #1db954aa;
        }

        .card {
            background-color: #1e1e1e;
            border: 1px solid #2c2c2c;
            color: #e0e0e0;
            transition: transform 0.3s ease;
        }

        .card-title {
            color: #1DB954;
        }

        .text-muted {
            color: #aaaaaa !important;
        }

        .toast {
            background-color: #2a2a2a;
            border: 1px solid #444;
            color: #fff;
        }

        .toast .btn-close-white {
            filter: invert(1);
        }
        .top-right {
            position: absolute;
            top: 20px;
            right: 20px;
        }
    </style>
</head>
<body>

<div class="top-right">
    <a href="cadastro" class="btn btn-outline-secondary">Cadastre-se</a>
</div>

<div class="card text-center position-absolute top-50 start-50 translate-middle p-4">
    <form action="${pageContext.request.contextPath}/login" method="post" class="form-signin">
    <h2>LOGIN</h2>
        <br>

        <div class="row mb-3 align-items-center">
            <label for="email" class="col-sm-3 col-form-label text-start"><b>Email:</b></label>
            <div class="col-sm-9">
                <input type="email" placeholder="E-mail" name="email" required class="form-control" id="email">
            </div>
        </div>

        <div class="row mb-4 align-items-center">
            <label for="senha" class="col-sm-3 col-form-label text-start"><b>Senha:</b></label>
            <div class="col-sm-9">
                <input type="password" placeholder="Senha" name="senha" required class="form-control" id="senha">
            </div>
        </div>

        <input class="btn btn-secondary" type="submit" value="LOGAR" name="login">
    </form>

</div>


<c:if test="${not empty msg}">
    <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 9999">
        <div id="toastMsg" class="toast align-items-center text-bg-success-outline border-0 show" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                        ${msg}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</c:if>


<script>
    setTimeout(() => {
        const toast = bootstrap.Toast.getOrCreateInstance(document.getElementById('toastMsg'));
        toast.hide();
    }, 5000);
</script>
</body>
</html>
