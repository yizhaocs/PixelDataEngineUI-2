/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */


app.controller('RegisterController', function RegisterController(UserService, $location, $rootScope, FlashService, md5) {
    var vm = this;
    vm.register = register;

    function register() {
        vm.dataLoading = true;
        vm.user.password = md5.createHash(vm.user.password);
        UserService.Create(vm.user)
            .then(function (response) {
                if (response.status == 'Success') {
                    FlashService.Success('Registration successful', true);
                    $location.path($rootScope.base + 'manageusers');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
    }
});