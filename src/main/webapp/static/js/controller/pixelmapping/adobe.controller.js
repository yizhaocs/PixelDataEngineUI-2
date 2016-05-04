/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlAdobe', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('adobe').then(function (backendData) {
        $scope.frontendData = backendData.data;
    });
});

app.controller('editCtrlAdobe', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.frontendData = angular.copy(backendData.data);
    $scope.keyIdDisable = (mappingID > 0) ? true : false;


    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.adobe_segment_id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'adobe', frontendData.adobe_segment_id, 'adobe');
    };

    $scope.saveMapping = function (frontendData) {
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'adobe', frontendData, 'adobe');
        } else {
            pixelmappingService.updateMapping($rootScope.base + 'adobe', mappingID, frontendData, 'adobe');
        }
    };
});