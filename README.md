# JobVueCRUD

#### Frameworks/Libs:
- Spring framework, Spring MVC
- Hibernate, C3PO Pool, Validator
- PostgreSQL
- JSON/Jackson
- Vue.js + vue-table
- Semantic UI + JQuery
- Sweetalert
 
##### Запуск
- Для Запуска приложения отредактировать config файл БД по адресу: resources/db/db.properties и указать свою БД
- Произвести сборку в Maven
- Запустить в Tomcat.

<a href="https://ibb.co/jbrETv"><img src="https://thumb.ibb.co/jbrETv/company.jpg" alt="company" border="0"></a> 
<a href="https://ibb.co/eLgsFa"><img src="https://thumb.ibb.co/eLgsFa/person.jpg" alt="person" border="0"></a>
<a href="https://ibb.co/hq9eva"><img src="https://thumb.ibb.co/hq9eva/job.jpg" alt="job" border="0"></a> 

<a href="https://ibb.co/eVWOov"><img src="https://thumb.ibb.co/eVWOov/delete.jpg" alt="delete" border="0"></a>
<a href="https://ibb.co/kS9XgF"><img src="https://thumb.ibb.co/kS9XgF/changed.jpg" alt="changed" border="0"></a>
<a href="https://ibb.co/hGyEva"><img src="https://thumb.ibb.co/hGyEva/posterror.jpg" alt="posterror" border="0"></a>

<a href="https://ibb.co/goO8MF"><img src="https://thumb.ibb.co/goO8MF/validation.jpg" alt="validation" border="0"></a>
<a href="https://ibb.co/nhpeva"><img src="https://thumb.ibb.co/nhpeva/job_add.jpg" alt="job_add" border="0"></a> 
<a href="https://ibb.co/dW4CFa"><img src="https://thumb.ibb.co/dW4CFa/person_add.jpg" alt="person_add" border="0"></a> 


##### Bugs:
  - Модальной окно "Место работы": Автоматическая установка значения для подгружаемых полей становится возможно только после подгрузки данных. Подгрузка осуществляется при первичном обращении
 - После отправки/валидации формы на одной из страниц и переключение на другую страницу форма валидации/отправки перестаёт работать.
 - При повторном нажатии на ссылку вкладки, таблица пропадает.
