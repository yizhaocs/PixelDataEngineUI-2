/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlDeriveConversion', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('derive-conversion').success(function (backendData) {
        $scope.frontendData = backendData.body;
    });
});

app.controller('editCtrlDeriveConversion', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data.body);

    $scope.isClean = function () {
        return angular.equals(backendData.data.body, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/adobe');
        if (confirm("Are you sure to delete mapping number: " + frontendData.dp_key_id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'derive-conversion', frontendData.dp_key_id, 'derive-conversion');
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/adobe');
        if (mappingID > 0) {
            pixelmappingService.updateMapping($rootScope.base + 'derive-conversion', mappingID, frontendData, 'derive-conversion');
        } else {
            pixelmappingService.insertMapping($rootScope.base + 'derive-conversion', frontendData, 'derive-conversion');
        }
    };
});