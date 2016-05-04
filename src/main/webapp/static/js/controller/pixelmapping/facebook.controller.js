/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlFacebook', function ($scope, $location, $anchorScroll, pixelmappingService) {
    pixelmappingService.getMappings('facebook-dp').then(function (backendData) {
        $scope.frontendDataMappingsDp = backendData.data;
    });
    pixelmappingService.getMappings('facebook-key').then(function (backendData) {
        $scope.frontendDataMappingsKey = backendData.data;
    });
    pixelmappingService.getMappings('facebook-pixel').then(function (backendData) {
        $scope.frontendDataMappingsPixel = backendData.data;
    });

    $scope.scrollTo = function (position) {
        var old = $location.hash();
        $location.hash(position);
        $anchorScroll();
        $location.hash(old);
    };
});

app.controller('editCtrlFacebookPixel', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Data Provider Pixel' : 'Add Data Provider Pixel';
    $scope.buttonText = (mappingID > 0) ? 'Update Data Provider Pixel' : 'Add New Data Provider Pixel';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/facebook');
        if (confirm("Are you sure to delete mapping number: " + frontendData.dp_id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'facebook', frontendData.dp_id, 'facebook-pixel');
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/facebook');
        if (mappingID == '0') {
            pixelmappingService.insertMapping($rootScope.base + 'facebook', frontendData, 'facebook-pixel');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'facebook', mappingID, frontendData, 'facebook-pixel');
        }
    };
});

app.controller('editCtrlFacebookDp', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = 'Edit Facebook DP Mapping';
    $scope.buttonText = 'Update Facebook DP Mapping';
    $scope.isUpdate = (mappingID != '0') ? true : false;

    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.saveMapping = function (frontendData) {
        // $location.path('/facebook');
        pixelmappingService.updateMapping($rootScope.base + 'facebook', mappingID, frontendData, 'facebook-dp');
    };
});

app.controller('editCtrlFacebookKey', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Facebook Key Mapping' : 'Add Facebook Key Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Facebook Key Mapping' : 'Add New Facebook Key Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false;
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);


    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/facebook');
        if (confirm("Are you sure to delete mapping number: " + frontendData.key_id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'facebook', frontendData.key_id, 'facebook-key');
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/facebook');
        if (mappingID == '0') {
            pixelmappingService.insertMapping($rootScope.base + 'facebook', frontendData, 'facebook-key');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'facebook', mappingID, frontendData, 'facebook-key');
        }
    };
});