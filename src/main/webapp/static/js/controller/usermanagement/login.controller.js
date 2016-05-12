/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('LoginController', function LoginController($rootScope, $location, AuthenticationService, FlashService) {
    var vm = this;
    vm.login = login;

    (function initController() {
        // reset login status
        $rootScope.isroot = false;
        AuthenticationService.ClearCredentials();
    })();

    function login() {
        vm.dataLoading = true;
        AuthenticationService.Login(vm.username, vm.password, function (response) {
            if (response.status == 'Success') {
                if (vm.username == 'root') {
                    $rootScope.isroot = true;
                }
                AuthenticationService.SetCredentials(vm.username, vm.password);
                $location.path($rootScope.base);
            } else {
                FlashService.Error(response.message);
                vm.dataLoading = false;
            }
        });
    };
});