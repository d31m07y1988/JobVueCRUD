var demo = new Vue({

    // A DOM element to mount our view model.
    el: '#app',

    // This is the model.
    // Define properties and give them initial values.
    data: {
        menu: '',
        dataget: [],
        columns: [],
        itemActions: [
            { name: 'edit-item', label: '', icon: 'large edit icon', class: 'ui mini icon button'},
            { name: 'delete-item', label: '', icon: 'large delete icon', class: 'ui mini icon button' }
        ],
        perPage: 10
    },
    components: {
      company: {
         props:{
              dataget: String,
              columns: {type: Array},
              rowactions: {type: Array},
             perpage: Number
          },
          template: '#company-template'
      }, 
      person: {
          props:{
              dataget: String,
              columns: {type: Array},
              rowactions: {type: Array},
              perpage: Number
          },
          template: '#person-template'
      }, 
      job: {
          props:{
              dataget: String,
              columns: {type: Array},
              rowactions: {type: Array},
              perpage: Number
          },
          template: '#job-template',
          methods: {
              dateformat:function(val) {
                  return new Date(val).toLocaleDateString("ru")
              }
          }
      }
    },
    methods: {
        makeActive: function(menu){
            this.menu = menu;
            switch (menu) {
                case 'company':
                    this.dataget = 'ajax/companies';
                    this.columns = [
                        {name: 'id'},
                        {name: 'name', title: 'Название'},
                        {name: 'inn', title: 'ИНН'},
                        {name: '__actions'}
                    ];
                    break;
                case 'person':
                    this.dataget = 'ajax/persons';
                    this.columns = [
                        {name: 'id'},
                        {name: 'fullname', title: 'ФИО'},
                        {name: 'phone', title: 'Телефон'},
                        {name: 'email'},
                        {name: '__actions'}
                    ];
                    break;
                case 'job':
                    this.dataget = 'ajax/jobs';
                    this.columns = [
                        {name: 'id'},
                        {name: 'person.fullname', title: 'Сотрудник'},
                        {name: 'company.name', title: 'Компания'},
                        {name: 'date_start', title: 'Дата приема',callback: 'dateformat'},
                        {name: 'date_end', title: 'Дата увольнения',callback: 'dateformat'},
                        {name: 'manager', title: 'Руководитель'},
                        {name: 'salary', title: 'Зарплата'},
                        {name: '__actions'}
                    ];
                    break;
            }
        },
        viewProfile: function(id) {
            console.log('view profile with id:', id)
        },
        editRow: function (id) {
            console.log('edit row with id:', id)
        },
        deleteRow: function (id) {
            swal({
                    title: "Удалить запись?",
                    text: "Прошу подтвердить удаление записи с id: " + id + " из базы данных",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Удалить запись!",
                    cancelButtonText: "Отменить!",
                    closeOnConfirm: false
                },
                function(){
                    this.$http.delete(dataget +"/" + message.id)
                        .success(function(response) {
                            console.log(response);
                        })
                        .error(function(errors) {
                            console.log(errors);
                        });
                    swal("Deleted!", "Строка была удалена", "success");
                });
            console.log('edit row with id:', id)
        }
    },
    events: {
        'vuetable:action': function(action, data) {
            console.log('vuetable:action', action, data)
            if (action == 'view-item') {
                this.viewProfile(data.id)
            } else if (action == 'edit-item') {
                this.editRow(data.id)
            } else if (action == 'delete-item') {
                this.deleteRow(data.id)
            }
        },
        'vuetable:load-error': function(response) {
            console.log('Load Error: ', response)
        }
    },

    created: function() {
        this.makeActive('company');
    }
});
