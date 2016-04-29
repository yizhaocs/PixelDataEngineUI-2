/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlDeriveConversion', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('derive-conversion').then(function (data) {
        $scope.frontendData = data.data;
    });
});

app.controller('editCtrlDeriveConversion', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    var original = backendData.data;
    if (original != '') {
        original._id = mappingID;
    }
    $scope.frontendData = angular.copy(original);
    if (original != '') {
        $scope.frontendData._id = mappingID;
    }

    $scope.isClean = function () {
        return angular.equals(original, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/adobe');
        if (confirm("Are you sure to delete mapping number: " + $scope.frontendData._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'derive-conversion', frontendData.dp_key_id, 'derive-conversion');
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/adobe');
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'derive-conversion', frontendData, 'derive-conversion');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'derive-conversion', mappingID, frontendData, 'derive-conversion');
        }
    };
});