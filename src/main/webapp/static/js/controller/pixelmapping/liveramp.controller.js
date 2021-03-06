/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlLiveramp', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('liveramp-dp').success(function (backendData) {
        $scope.mappingsDp = backendData.body;
    });
    pixelmappingService.getMappings('liveramp-key').success(function (backendData) {
        $scope.mappingsKey = backendData.body;
    });
});

app.controller('editCtrlLiverampDp', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID > 0) ? 'Edit Liveramp DP Mapping' : 'Add Liveramp DP Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Liveramp DP Mapping' : 'Add New Liveramp DP Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data.body);

    $scope.isClean = function () {
        return angular.equals(backendData.data.body, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/liveramp');
        if (confirm("Are you sure to delete mapping number: " + frontendData.dp_name) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'liveramp', frontendData.dp_name, 'liveramp-dp');
    };

    $scope.saveMapping = function (frontendData) {
        if (frontendData.threshold_mb > 100) {
            alert("Threshold can't be greater than 100MB");
        } else {
            // $location.path('/liveramp');
            if (mappingID > 0) {
                pixelmappingService.updateMapping($rootScope.base + 'liveramp', mappingID, frontendData, 'liveramp-dp');
            } else {
                pixelmappingService.insertMapping($rootScope.base + 'liveramp', frontendData, 'liveramp-dp');
            }
        }
    };
});

app.controller('editCtrlLiverampKey', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID != '0') ? 'Edit Liveramp Key Mapping' : 'Add Liveramp Key Mapping';
    $scope.buttonText = (mappingID != '0') ? 'Update Liveramp Key Mapping' : 'Add New Liveramp Key Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false;
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data.body);


    $scope.isClean = function () {
        return angular.equals(backendData.data.body, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/liveramp');
        if (confirm("Are you sure to delete mapping number: " + frontendData.liveramp_segment_id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'liveramp', frontendData.liveramp_segment_id, 'liveramp-key');
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/liveramp');
        if (mappingID > 0) {
            pixelmappingService.updateMapping($rootScope.base + 'liveramp', mappingID, frontendData, 'liveramp-key');
        } else {
            pixelmappingService.insertMapping($rootScope.base + 'liveramp', frontendData, 'liveramp-key');
        }
    };
});