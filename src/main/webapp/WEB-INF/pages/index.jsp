<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Job </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/job.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.9/semantic.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
</head>
<body>

<div class="ui container segment" id="app">

    <div class="ui pointing menu">
        <a class="item" v-bind:class="menu=='company'?'active':''" @click="makeActive('company')" href="#">
            Организации
        </a>
        <a class="item" v-bind:class="menu=='person'?'active':''" @click="makeActive('person')" href="#">
            Физические лица
        </a>
        <a class="item" v-bind:class="menu=='job'?'active':''" @click="makeActive('job')" href="#">
            Место работы
        </a>
    </div>

    <div class="ui segment">
        <template id="company-template">
            <h2 class="ui header">Организации</h2>
            <vuetable
                    :api-url="dataget"
                    :fields="columns"
                    :item-actions="rowactions"
                    :per-page="perpage"
                    pagination-info-template="Показано: {from} - {to} из {total} записей"
                    pagination-path="pagination"
            ></vuetable>
        </template>
        <template id="person-template">
            <h2 class="ui header">Физические лица</h2>
            <vuetable
                    :api-url="dataget"
                    :fields="columns"
                    :item-actions="rowactions"
                    :per-page="perpage"
                    pagination-info-template="Показано: {from} - {to} из {total}"
                    pagination-path="pagination"
            ></vuetable>
        </template>
        <template id="job-template">
            <h2 class="ui header">Место работы</h2>
            <vuetable
                    :api-url="dataget"
                    :fields="columns"
                    :item-actions="rowactions"
                    :per-page="perpage"
                    pagination-info-template="Показано: {from} - {to} из {total}"
                    pagination-path="pagination"
            ></vuetable>
        </template>

        <component :is="menu"
                   :columns="columns"
                   :dataget="dataget"
                   :rowactions="itemActions"
                   :perpage="perPage"></component>

    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/vue/1.0.28/vue.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js"></script>
<script type="text/javascript" src="http://cdn.jsdelivr.net/vue.table/1.5.3/vue-table.min.js"></script>
<script src="resources/jobvue.js"></script>

</body>
</html>
