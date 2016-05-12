/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('ManageusersController', function ManageusersController(UserService, $rootScope, $location, md5) {
    var vm = this;

    vm.user = null;
    vm.allUsers = [];
    vm.deleteUser = deleteUser;
    vm.updateUser = updateUser;

    initController();

    function initController() {
        loadCurrentUser();
        loadAllUsers();
    }

    function loadCurrentUser() {
        UserService.GetByUsername($rootScope.globals.currentUser.username)
            .then(function (user) {
                vm.user = user;
            });
    }

    function loadAllUsers() {
        UserService.GetAll()
            .then(function (users) {
                vm.allUsers = users;
            });
    }

    function deleteUser(id) {
        UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
    }

    function updateUser(user) {
        user.password = md5.createHash(user.password);
        UserService.Update(user)
            .then(function () {
                $location.path($rootScope.base + 'manageusers');
                loadAllUsers();
            });
    }
});