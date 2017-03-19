<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Job </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/job.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.9/semantic.min.css">
</head>
<body>

<div class="ui container segment" id="app">

    <div class="ui pointing menu">
        <a class="item" v-bind:class="menuNum=='m2'?'active':''" @click="makeActive('Организации','m2')" href="#">
            Организации
        </a>
        <a class="item" v-bind:class="menuNum=='m3'?'active':''" @click="makeActive('Физические лица','m3')" href="#">
            Физические лица
        </a>
        <a class="item" v-bind:class="menuNum=='m4'?'active':''" @click="makeActive('Место работы','m4')" href="#">
            Место работы
        </a>
    </div>

    <div class="ui segment">
        <h2 class="ui header">{{ topic }}</h2>

        <div v-if="menuNum">
                <vuetable
                        :api-url="dataget"
                        :fields="columns"
                        :item-actions="itemActions"
                        :per-page="perPage"
                        pagination-info-template="Показано: {from} - {to} из {total} записей"
                        pagination-path="pagination"
                ></vuetable>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/vue/1.0.28/vue.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js"></script>
<script type="text/javascript" src="http://cdn.jsdelivr.net/vue.table/1.5.3/vue-table.min.js"></script>
<script src="resources/jobvue.js"></script>

</body>
</html>
