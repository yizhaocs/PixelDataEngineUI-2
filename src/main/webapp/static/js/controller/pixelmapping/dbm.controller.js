/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlDbm', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('dbm').then(function (backendData) {
        $scope.frontendData = backendData.data;
    });
});

app.controller('editCtrlDbm', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.conversion_pixel_id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'dbm', frontendData.conversion_pixel_id, 'dbm');
    };

    $scope.saveMapping = function (frontendData) {
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'dbm', frontendData, 'dbm');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'dbm', mappingID, frontendData, 'dbm');
        }
    };
});