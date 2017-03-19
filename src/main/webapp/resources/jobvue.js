var demo = new Vue({

    // A DOM element to mount our view model.
    el: '#app',

    // This is the model.
    // Define properties and give them initial values.
    data: {
        topic: '',
        menuNum: '',
        dataget: [],
        columns: [],
        itemActions: [
            { name: 'edit-item', label: '', icon: 'large edit icon', class: 'ui mini icon button'},
            { name: 'delete-item', label: '', icon: 'large delete icon', class: 'ui mini icon button' }
        ],
        dataTable: [],
        perPage: 10
    },

    // Functions we will be using.
    methods: {
        makeActive: function(item, num){
            this.topic = item;
            this.menuNum = num;
            switch (num) {
                case 'm2':
                    this.dataget = 'ajax/companies';
                    this.columns = [
                        {name: 'id'},
                        {name: 'name', title: 'Название'},
                        {name: 'inn', title: 'ИНН'},
                        {name: '__actions'}
                    ];
                    break;
                case 'm3':
                    this.dataget = 'ajax/persons';
                    this.columns = [
                        {name: 'id'},
                        {name: 'fullname', title: 'ФИО'},
                        {name: 'phone', title: 'Телефон'},
                        {name: 'email'}
                    ];
                    break;
                case 'm4':
                    this.dataget = 'ajax/jobs';
                    break;
            }
        },
        fetchCompanies: function(page) {
            var options = {
                params: {
                    page: page,
                    per_page: this.perPage
                }
            }

            this.$http.get('ajax/companies', options).then(function(response) {

                this.dataTable = response.data
            }, console.log)
        },
        fetchPersons: function() {
            this.$http.get('ajax/persons').then(function(response) {
                this.dataTable = response.data
            }, console.log)
        },
        fetchJob: function() {
            this.$http.get('ajax/job').then(function(response) {
                this.dataTable = response.data
            }, console.log)
        },
        viewProfile: function(id) {
            console.log('view profile with id:', id)
        }
    },
    events: {
        'vuetable:action': function(action, data) {
            console.log('vuetable:action', action, data)
            if (action == 'view-item') {
                this.viewProfile(data.id)
            }
        },
        'vuetable:load-error': function(response) {
            console.log('Load Error: ', response)
        }
    },

    created: function() {
        this.makeActive('Организации','m2');
    }
});
