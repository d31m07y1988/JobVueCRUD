var vm = new Vue({

    el: '#app',
    data: {
        menu: '',
        dataget: [],
        columns: [],
        itemActions: [
            {name: 'edit-item', label: '', icon: 'large edit icon', class: 'ui mini icon button'},
            {name: 'delete-item', label: '', icon: 'large delete icon', class: 'ui mini icon button'}
        ],
        perPage: 10
    },
    components: {
        company: {
            props: {
                dataget: String,
                columns: {type: Array},
                rowactions: {type: Array},
                perpage: Number
            },
            template: '#company-template',
            methods: {
                editRow: function () {
                    this.$parent.editRow();
                }
            },
            ready: function () {
                // code here executes once the component is rendered
                // use this in the child component
                companyValidateSent();
            }
        },
        person: {
            props: {
                dataget: String,
                columns: {type: Array},
                rowactions: {type: Array},
                perpage: Number
            },
            template: '#person-template',
            methods: {
                editRow: function () {
                    this.$parent.editRow();
                }
            },
            ready: function () {
                // code here executes once the component is rendered
                // use this in the child component
                personValidateSent();
            }
        },
        job: {
            props: {
                dataget: String,
                columns: {type: Array},
                rowactions: {type: Array},
                perpage: Number
            },
            template: '#job-template',
            methods: {
                dateformat: function (val) {
                    return new Date(val).toLocaleDateString("ru")
                },
                managerformat: function (val) {
                    return val ? 'да' : 'нет'
                },
                salaryformat: function(val) {
                    return parseFloat(Math.round(val * 100) / 100).toFixed(2);
                },
                editRow: function () {
                    this.$parent.editRow();
                }
            },
            ready: function () {
                jobValidateSent();
            }
        }
    },
    methods: {
        makeActive: function (menu) {
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
                        {name: 'date_start', title: 'Дата приема', callback: 'dateformat'},
                        {name: 'date_end', title: 'Дата увольнения', callback: 'dateformat'},
                        {name: 'manager', title: 'Руководитель', callback: 'managerformat'},
                        {name: 'salary', title: 'Зарплата', callback: 'salaryformat'},
                        {name: '__actions'}
                    ];
                    break;
            };
        },
        viewProfile: function (id) {
            console.log('view profile with id:', id)
        },
        editRow: function (data) {
            switch (this.$data.menu) {
                case 'company':
                    companyModal(data).modal('show')
                    break;
                case 'person':
                    personModal(data).modal('show')
                    break;
                case 'job':
                    jobModal(data).modal('show')
                    break;
            }
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
                function () {
                    vm.$http.delete(vm.dataget + "/" + id)
                    swal({
                            title: "Строка была удалена",
                            type: "success"
                        },
                        function () {
                            vm.$broadcast('vuetable:reload')
                        });
                })
        }
    },
    events: {
        'vuetable:action': function (action, data) {
            console.log('vuetable:action', action, data)
            if (action == 'view-item') {
                this.viewProfile(data.id)
            } else if (action == 'edit-item') {
                this.editRow(data)
            } else if (action == 'delete-item') {
                this.deleteRow(data.id)
            }
        },
        'vuetable:load-error': function (response) {
            console.log('Load Error: ', response)
        }
    },
    created: function () {
        this.makeActive('company');
    }
});

function companyModal(data) {
    return $('#company-modal')
        .modal({
                onShow: function () {
                    $('.ui.error.message').empty();
                    if (data != undefined) {
                        $('#company-modal form').form('set values', {
                            id: data.id,
                            name: data.name,
                            inn: data.inn
                        })
                    } else $('#company-modal form').form('clear')
                },
                onVisible: function () {
                },
                onApprove: function () {
                    return false;
                }
            }
        )
};

function personModal(data) {
    return $('#person-modal')
        .modal({
                onShow: function () {
                    $('.ui.error.message').empty();
                    if (data != undefined) {
                        $('#person-modal form').form('set values', {
                            id: data.id,
                            fullname: data.fullname,
                            phone: data.phone,
                            email: data.email
                        })
                    } else $('#person-modal form').form('clear')
                },

                onVisible: function () {
                },
                onApprove: function () {

                    return false;
                }
            }
        )
};

function jobModal(data) {
    return $('#job-modal')
        .modal({
                onShow: function () {
                    $('.ui.error.message').empty();
                    $('#date_start').calendar({
                        type: 'date',
                        firstDayOfWeek: 1,
                        text: {
                            days: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
                            months: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрт', 'Октябрь', 'Ноябрь', 'Декабрь'],
                            monthsShort: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек']
                        },
                        monthFirst: false,
                        formatter: {
                            date: function (date, settings) {
                                function pad(number) {
                                    if (number < 10) {
                                        return '0' + number;
                                    }
                                    return number;
                                };
                                if (!date) return '';
                                var day = pad(date.getDate());
                                var month = pad(date.getMonth() + 1);
                                var year = date.getFullYear();
                                return day + '.' + month + '.' + year;
                            }
                        }
                    });
                    $('#date_end').calendar({
                        type: 'date',
                        firstDayOfWeek: 1,
                        text: {
                            days: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
                            months: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрт', 'Октябрь', 'Ноябрь', 'Декабрь'],
                            monthsShort: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек']
                        },
                        monthFirst: false,
                        formatter: {
                            date: function (date, settings) {
                                function pad(number) {
                                    if (number < 10) {
                                        return '0' + number;
                                    }
                                    return number;
                                };
                                if (!date) return '';
                                var day = pad(date.getDate());
                                var month = pad(date.getMonth() + 1);
                                var year = date.getFullYear();
                                return day + '.' + month + '.' + year;
                            }
                        }
                    });

                    $('#companydropdown')
                        .dropdown({
                            fullTextSearch: true,
                            apiSettings: {
                                url: '/ajax/companies/dropdown'
                            },
                            fields: {
                                name  : 'name', // displayed dropdown
                                value : 'value', // actual dropdown value
                                text : 'text'   // name displayed after selection (optional)
                            }
                        })
                    ;

                    $('#persondropdown')
                        .dropdown({
                            fullTextSearch: true,
                            apiSettings: {
                                url: '/ajax/persons/dropdown'
                            },
                            fields: {
                                name  : 'name', // displayed dropdown
                                value : 'value', // actual dropdown value
                                text : 'text'   // name displayed after selection (optional)
                            }
                        })
                    ;

                    if (data != undefined) {
                        console.log(data);
                        $('#date_start').calendar('set date', new Date(data.date_start));
                        $('#date_end').calendar('set date', new Date(data.date_end));
                        $('#managercheckbox').checkbox(data.manager?'check':'uncheck');
                        $('#job-modal form').form('set values', {
                            id: data.id,
                            person: data.person.id,
                            company: data.company.id,
                            salary: data.salary
                        })
                    } else $('#job-modal form').form('clear')
                },
                onVisible: function () {
                },
                onApprove: function () {
                    return false;
                }
            }
        )
};

function companyValidateSent() {
    $.fn.form.settings.rules.isINN = function (i) {
        if (i == "" || i.match(/\D/)) return false;

        var inn = i.match(/(\d)/g);

        if (inn.length == 10) {
            return inn[9] == String(((
                        2 * inn[0] + 4 * inn[1] + 10 * inn[2] +
                        3 * inn[3] + 5 * inn[4] + 9 * inn[5] +
                        4 * inn[6] + 6 * inn[7] + 8 * inn[8]
                    ) % 11) % 10);
        }
        else if (inn.length == 12) {
            return inn[10] == String(((
                        7 * inn[0] + 2 * inn[1] + 4 * inn[2] +
                        10 * inn[3] + 3 * inn[4] + 5 * inn[5] +
                        9 * inn[6] + 4 * inn[7] + 6 * inn[8] +
                        8 * inn[9]
                    ) % 11) % 10) && inn[11] == String(((
                        3 * inn[0] + 7 * inn[1] + 2 * inn[2] +
                        4 * inn[3] + 10 * inn[4] + 3 * inn[5] +
                        5 * inn[6] + 9 * inn[7] + 4 * inn[8] +
                        6 * inn[9] + 8 * inn[10]
                    ) % 11) % 10);
        }

        return false;
    };
    $('#company-modal form')
        .form({
            on: 'submit',
            fields: {
                name: {
                    identifier: 'name',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Введите название компании'
                        }
                    ]
                },
                inn: {
                    identifier: 'inn',
                    rules: [
                        {
                            type: 'isINN',
                            prompt: 'ИНН введен не корректно'
                        }
                    ]
                }
            },
            onSuccess(event, fields) {
                var formData = $('#company-modal form').form('get values')
                vm.$http.post('/ajax/companies', formData).then(function (response) {
                    // success
                    swal({
                            title: "Запись произведена",
                            type: "success"
                        },
                        function () {
                            vm.$broadcast('vuetable:reload')
                            $('#company-modal').modal('hide');
                        });
                }, function (response) {
                    // error
                    sweetAlert("Ошибка", "ошибка внесения записи", "error");
                    console.log('ошибка внесения записи ' + formData)
                });
                return false;
            }
        });
};

function personValidateSent() {
    $.fn.form.settings.rules.isPhone = function (phonenum) {
        if (phonenum == "") return false;
        var regexObj = /^((8|0|((\+|00)\d{1,2}))[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/;
        return regexObj.test(phonenum);
    }
    $('#person-modal form')
        .form({
            on: 'submit',
            fields: {
                name: {
                    identifier: 'fullname',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Введите ФИО'
                        }
                    ]
                },
                phone: {
                    identifier: 'phone',
                    rules: [
                        {
                            type: 'isPhone',
                            prompt: 'Телефон введен не корректно'
                        }
                    ]
                },
                email: {
                    identifier: 'email',
                    rules: [
                        {
                            type: 'email',
                            prompt: 'Email введен не корректно'
                        }
                    ]
                }
            },
            onSuccess(event, fields) {
                var formData = $('#person-modal form').form('get values')
                vm.$http.post('/ajax/persons', formData).then(function (response) {
                    // success
                    swal({
                            title: "Запись произведена",
                            type: "success"
                        },
                        function () {
                            vm.$broadcast('vuetable:reload')
                            $('#person-modal').modal('hide');
                        });
                }, function (response) {
                    // error
                    sweetAlert("Ошибка", "ошибка внесения записи", "error");
                    console.log('ошибка внесения записи ' + formData)
                });
                return false;
            }
        });
};

function jobValidateSent() {
    $('#job-modal form')
        .form({
            inline: true,
            on: 'submit',
            fields: {
                company: {
                    identifier: 'company',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Выберите компанию'
                        }
                    ]
                },
                person: {
                    identifier: 'person',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Выберите сотрудника'
                        }
                    ]
                },
                date_start: {
                    identifier: 'date_start',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Укажите дату'
                        }
                    ]
                },
                date_end: {
                    identifier: 'date_end',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Укажите дату'
                        }
                    ]
                },
                salary: {
                    identifier: 'salary',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Укажите зарплату'
                        },
                        {
                            type: 'number',
                            prompt: 'Зарлата указана не корректно'
                        }
                    ]
                }
            },
            onSuccess(event, fields) {
                var formData = $('#job-modal form').form('get values')
                console.log(formData)
                vm.$http.post('/ajax/jobs', formData).then(function (response) {
                    // success
                    swal({
                            title: "Запись произведена",
                            type: "success"
                        },
                        function () {
                            vm.$broadcast('vuetable:reload')
                            $('#job-modal').modal('hide');
                        });
                }, function (response) {
                    // error
                    sweetAlert("Ошибка", "ошибка внесения записи", "error");
                    console.log('ошибка внесения записи ' + formData)
                });
                return false;
            }
        });
};