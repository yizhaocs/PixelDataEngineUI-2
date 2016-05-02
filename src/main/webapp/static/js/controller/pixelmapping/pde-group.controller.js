/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listPixelGroups', function ($scope, pixelmappingService) {
    pixelmappingService.getGroups().then(function (data) {
        $scope.frontendData = data.data;
    });
});


app.controller('editPixelGroup', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var keyId = ($routeParams.keyId) ? parseInt($routeParams.keyId) : 0;
    $rootScope.title = (keyId > 0) ? 'Edit Group' : 'Add Group';
    $scope.buttonText = (keyId > 0) ? 'Update Group' : 'Add New Group';
    $scope.isUpdate = (keyId > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    };

    $scope.deleteGroup = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.key_id) == true)
            pixelmappingService.deleteGroup($rootScope.base + 'pixel-data-engine-group', frontendData.key_id);
    };

    $scope.saveGroup = function (frontendData) {
        if (keyId <= 0) {
            pixelmappingService.insertGroup($rootScope.base + 'pixel-data-engine-group', frontendData);
        }
        else {
            pixelmappingService.updateGroup($rootScope.base + 'pixel-data-engine-group', keyId, frontendData);
        }
    };
});

app.controller('editSameGroup', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var gid = ($routeParams.gid) ? parseInt($routeParams.gid) : 0;
    $scope.title = 'Group id:' + gid;
    $scope.frontendData = angular.copy(backendData.data);

    //  We'll load our list of Customers from our JSON Web Service into this variable
    $scope.listOfCustomers = null;

    //  When the user selects a "Customer" from our MasterView list, we'll set the following variable.
    $scope.selectedCustomer = null;

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    };

    $scope.deleteGroup = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.key_id) == true)
            pixelmappingService.deleteGroup($rootScope.base + 'pixel-data-engine-group', frontendData.key_id);
    };

    $scope.saveGroup = function (frontendData) {
        if (keyId <= 0) {
            pixelmappingService.insertGroup($rootScope.base + 'pixel-data-engine-group', frontendData);
        }
        else {
            pixelmappingService.updateGroup($rootScope.base + 'pixel-data-engine-group', keyId, frontendData);
        }
    };

    $scope.selectCustomer = function (val) {
        //  If the user clicks on a <div>, we can get the ng-click to call this function, to set a new selected Customer.
        $scope.selectedCustomer = val.CustomerID;
        $scope.loadOrders();
    }

    $scope.loadOrders = function () {
        //  Reset our list of orders  (when binded, this'll ensure the previous list of orders disappears from the screen while we're loading our JSON data)
        $scope.listOfOrders = null;

        //  The user has selected a Customer from our Drop Down List.  Let's load this Customer's records.
        pixelmappingService.getGroup($scope.selectedCustomer);
        //$http.get('http://inorthwind.azurewebsites.net/Service1.svc/getBasketsForCustomer/' + $scope.selectedCustomer)
        //    .success(function (data) {
        //        $scope.listOfOrders = data.GetBasketsForCustomerResult;
        //    })
        //    .error(function (data, status, headers, config) {
        //        $scope.errorMessage = "Couldn't load the list of Orders, error # " + status;
        //    });
    }
});


