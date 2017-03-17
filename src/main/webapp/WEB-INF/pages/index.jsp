<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Job </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/job.css">
</head>
<body>

<div class="container brd" id="app">
    <b-nav tabs>
<%--
        <b-nav-item link="#" v-bind:class="menuNum=='m1'?'active':''" @click="makeActive('Главная','m1')">Главная</b-nav-item>
--%>
        <b-nav-item link="#" v-bind:class="menuNum=='m2'?'active':''" @click="makeActive('Организации','m2')">Организации</b-nav-item>
        <b-nav-item link="#" v-bind:class="menuNum=='m3'?'active':''" @click="makeActive('Физические лица','m3')">Физические лица</b-nav-item>
        <b-nav-item link="#" v-bind:class="menuNum=='m4'?'active':''" @click="makeActive('Место работы','m4')">Место работы</b-nav-item>
    </b-nav>

    <div class="panel panel-default brd">
        <div class="panel-heading">{{ topic }}</div>
        <div class="panel-body">
            Panel Content
<br>

            {{dataTable}}

        </div>
    </div>


    <b-button>
        <span class="glyphicon glyphicon-trash"></span>
    </b-button>
</div>
    <script src="resources/require.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.2.4/vue.js"></script>
    <script src="https://unpkg.com/bootstrap-vue/dist/bootstrap-vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue-resource/1.2.1/vue-resource.min.js"></script>
    <script src="resources/jobvue.js"></script>

</body>
</html>
