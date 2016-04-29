/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlKruxDpkey', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('krux-dpkey').then(function (data) {
        $scope.frontendData = data.data;
    });
});

app.controller('editCtrlKruxDpkey', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : 0;

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
            pixelmappingService.deleteMapping($rootScope.base + 'krux-dpkey', frontendData.krux_segment_id, 'krux-dpkey');
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/adobe');
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'krux-dpkey', frontendData, 'krux-dpkey');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'krux-dpkey', mappingID, frontendData, 'krux-dpkey');
        }
    };
});