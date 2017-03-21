<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Job </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/job.css">
    <link rel="shortcut icon" href="resources/fav.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.9/semantic.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
</head>
<body>

<script type="x-template" id="company-template">
    <h2 class="ui header">Организации</h2>

    <div class="ui grid">
        <div class="ui left aligned nine wide column">
            <div class="ui labeled icon input">
                <div class="ui label">Search:</div>
                <input v-model="searchFor" class="ui input" >
                <i class="search icon"></i>
            </div>
            <button class="ui button primary" >Go</button>
            <button class="ui button" >Reset</button>
            &nbsp;
        </div>
        <div class="ui right aligned seven wide column">
            <button class="ui button primary" id="addBtn">Добавить запись</button>
        </div>
    </div>

    <!-- Modal Forms-->
    <div class="ui small modal" id="company-modal">
        <div class="header">Добавить</div>
        <div class="content">
            <form class="ui form" id="theform">
                <div class="field">
                    <input type="hidden" name="id">
                    <label>Компания:</label>
                    <input type="text" name="name" placeholder="Название компании">
                </div>
                <div class="field">
                    <label>ИНН:</label>
                    <input type="text" name="inn" placeholder="ИНН компании">
                </div>

                <div class="ui error message"></div>
            </form>
        </div>
        <div class="actions">
            <button class="ui cancel button">Закрыть</button>
            <button class="ui approve button" form="theform">Сохранить</button>
        </div>
    </div>

    <vuetable
            :api-url="dataget"
            :fields="columns"
            :item-actions="rowactions"
            :per-page="perpage"
            pagination-info-template="Показано: {from} - {to} из {total} записей"
            pagination-path="pagination"
    ></vuetable>
</script>

<script type="x-template" id="person-template">
    <h2 class="ui header">Физические лица</h2>
    <vuetable
            :api-url="dataget"
            :fields="columns"
            :item-actions="rowactions"
            :per-page="perpage"
            pagination-info-template="Показано: {from} - {to} из {total}"
            pagination-path="pagination"
    ></vuetable>
</script>
<script type="x-template" id="job-template">
    <h2 class="ui header">Место работы</h2>
    <vuetable
            :api-url="dataget"
            :fields="columns"
            :item-actions="rowactions"
            :per-page="perpage"
            pagination-info-template="Показано: {from} - {to} из {total}"
            pagination-path="pagination"
    ></vuetable>
</script>

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
        <component :is="menu"
                   :columns="columns"
                   :dataget="dataget"
                   :rowactions="itemActions"
                   :perpage="perPage"></component>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.2/semantic.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/vue/1.0.28/vue.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js"></script>
<script src="http://cdn.jsdelivr.net/vue.table/1.5.3/vue-table.min.js"></script>
<script src="resources/jobvue.js"></script>

</body>
</html>
