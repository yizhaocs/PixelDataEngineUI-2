/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlFacebook', function ($scope, $location, $anchorScroll, pixelmappingService) {
    pixelmappingService.getMappings('facebook-dp').then(function (data) {
        $scope.mappingsDp = data.data;
    });
    pixelmappingService.getMappings('facebook-key').then(function (data) {
        $scope.mappingsKey = data.data;
    });
    pixelmappingService.getMappings('facebook-pixel').then(function (data) {
        $scope.mappingsPixel = data.data;
    });

    $scope.scrollTo = function (position) {
        var old = $location.hash();
        $location.hash(position);
        $anchorScroll();
        $location.hash(old);
    };
});

app.controller('editCtrlFacebookPixel', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID != '0') ? 'Edit Liveramp DP Mapping' : 'Add Facebook Pixel Mapping';
    $scope.buttonText = (mappingID != '0') ? 'Update Liveramp DP Mapping' : 'Add New Facebook Pixel Mapping';
    var original = mapping.data;
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

    $scope.deleteMapping = function (mapping) {
        // $location.path('/facebook');
        if (confirm("Are you sure to delete mapping number: " + $scope.mapping._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'facebook', mapping.dp_id, 'facebook-pixel');
    };

    $scope.saveMapping = function (mapping) {
        // $location.path('/facebook');
        if (mappingID == '0') {
            pixelmappingService.insertMapping($rootScope.base + 'facebook', mapping, 'facebook-pixel');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'facebook', mappingID, mapping, 'facebook-pixel');
        }
    };
});

app.controller('editCtrlFacebookDp', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = 'Edit Facebook DP Mapping';
    $scope.buttonText = 'Update Facebook DP Mapping';
    var original = mapping.data;
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

    $scope.saveMapping = function (mapping) {
        // $location.path('/facebook');
        pixelmappingService.updateMapping($rootScope.base + 'facebook', mappingID, mapping, 'facebook-dp');
    };
});

app.controller('editCtrlFacebookKey', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID != '0') ? 'Edit Facebook Key Mapping' : 'Add Facebook Key Mapping';
    $scope.buttonText = (mappingID != '0') ? 'Update Facebook Key Mapping' : 'Add New Facebook Key Mapping';
    var original = mapping.data;
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

    $scope.deleteMapping = function (mapping) {
        // $location.path('/facebook');
        if (confirm("Are you sure to delete mapping number: " + $scope.frontendData._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'facebook', mapping.key_id, 'facebook-key');
    };

    $scope.saveMapping = function (mapping) {
        // $location.path('/facebook');
        if (mappingID == '0') {
            pixelmappingService.insertMapping($rootScope.base + 'facebook', mapping, 'facebook-key');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'facebook', mappingID, mapping, 'facebook-key');
        }
    };
});