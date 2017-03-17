var demo = new Vue({

    // A DOM element to mount our view model.
    el: '#app',

    // This is the model.
    // Define properties and give them initial values.
    data: {
        topic: '',
        menuNum: '',
        columns: [],
        dataTable: [],
        totalDataTable: 0,
        perPage: 10,
        currentPage: 1
    },

    // Functions we will be using.
    methods: {
        makeActive: function(item, num){
            this.topic = item;
            this.menuNum = num;
            switch (num) {
                case 'm2':
                    this.fetchCompanies(this.currentPage);
                    break;
                case 'm3':
                    this.fetchPersons();
                    break;
                case 'm4':
                    this.fetchJob();
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
/*                this.totalDataTable = this.persons.length
                this.currentPage = page*/
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
        }
    },

    created: function() {
        this.makeActive('Организации','m2');
    }
});
