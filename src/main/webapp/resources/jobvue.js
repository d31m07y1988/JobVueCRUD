var vm = new Vue({

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
              },
              managerformat: function (val) {
                  return val? 'да':'нет'
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
                        {name: 'manager', title: 'Руководитель', callback: 'managerformat'},
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
                    confirmButtonText: "Удалить!",
                    cancelButtonText: "Отменить",
                    closeOnConfirm: false
                },
                function (){
                    vm.$http.delete(vm.dataget +"/" + id)
                    swal({
                        title: "Строка была удалена",
                        type: "success"},
                    function () {
                        vm.$broadcast('vuetable:reload')
                    });
                })
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

$.fn.form.settings.rules.isINN = function(i) {
    if (i=="" || i.match(/\D/) ) return false;

    var inn = i.match(/(\d)/g);

    if ( inn.length == 10 )
    {
        return inn[9] == String(((
                    2*inn[0] + 4*inn[1] + 10*inn[2] +
                    3*inn[3] + 5*inn[4] +  9*inn[5] +
                    4*inn[6] + 6*inn[7] +  8*inn[8]
                ) % 11) % 10);
    }
    else if ( inn.length == 12 )
    {
        return inn[10] == String(((
                    7*inn[0] + 2*inn[1] + 4*inn[2] +
                    10*inn[3] + 3*inn[4] + 5*inn[5] +
                    9*inn[6] + 4*inn[7] + 6*inn[8] +
                    8*inn[9]
                ) % 11) % 10) && inn[11] == String(((
                    3*inn[0] +  7*inn[1] + 2*inn[2] +
                    4*inn[3] + 10*inn[4] + 3*inn[5] +
                    5*inn[6] +  9*inn[7] + 4*inn[8] +
                    6*inn[9] +  8*inn[10]
                ) % 11) % 10);
    }

    return false;
};

$('#company-modal')
    .modal({
        onApprove : function() {
            $('#theform').submit(function (e) {
                e.preventDefault()
                let formData = {
                    id: $('[name=id]').val(),
                    name: $('[name=name]').val(),
                    inn: $('[name=inn]').val()
                }
                vm.$http.post('/ajax/companies', formData).then(function (response) {
                    // success
                    swal({
                            title: "Добавлена запись",
                            type: "success"
                        },
                        function () {
                            vm.$broadcast('vuetable:reload')
                            $('#company-modal').modal('hide');
                        });
                }, function (response) {
                    // error
                    sweetAlert("Ошибка", "ошибка добавления записи", "error");
                    console.log('ошибка добавления записи ' + formData)
                });
            });
            return false;
        }
    })
    .modal('attach events', '#addBtn', 'show')
;


$('#company-modal form')
    .form({
        fields: {
            name: {
                identifier: 'name',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Введите название компании'
                    }
                ]
            },
            inn: {
                identifier: 'inn',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Введите ИНН'
                    },
                    {
                        type: 'isINN',
                        prompt: 'ИНН не корректно введен'
                    }
                ]
            }
        }
    },{
        inline : false,
        on: 'blur'
    });