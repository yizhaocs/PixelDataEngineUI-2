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

    /*
     *
     * functions
     * */
    // button fuctions
    $scope.addSetRule = function () {
        if ($scope.debug) {
            console.log('addSetRule');
        }
        $scope.frontendRightHandPanelData.setRuleArray.push({
            column1: $scope.frontendRightHandPanelData.setRuleArray.length,
            column2: ''
        });

    };

    $scope.removeSetRule = function () {
        if ($scope.debug) {
            console.log('removeSetRule');
        }
        $scope.frontendRightHandPanelData.setRuleArray.pop();
    };

    $scope.addInElement = function () {
        if ($scope.debug) {
            console.log('addInElement');
        }
        $scope.frontendRightHandPanelData.inElementArray.push({
            column1: $scope.frontendRightHandPanelData.inElementArray.length,
            column2: ''
        });
    };

    $scope.removeInElement = function () {
        if ($scope.debug) {
            console.log('removeInElement');
        }
        $scope.frontendRightHandPanelData.inElementArray.pop();
    };


    $scope.isClean = function () {
        return angular.equals(responseBackupRuleData, $scope.frontendRightHandPanelData);
    }

    $scope.deleteRule = function (frontendRightHandPanelData) {
        if (confirm("Are you sure to delete rule number: " + frontendRightHandPanelData.keyId) == true) {
            pixelmappingService.deleteRule($rootScope.base + 'pixel-data-engine-rule', frontendRightHandPanelData.keyId);
        }
    };

    $scope.saveRule = function (frontendRightHandPanelData) {
        if ($routeParams.keyId == '0') {
            pixelmappingService.insertRule($rootScope.base + 'pixel-data-engine-rule', frontendRightHandPanelData.parseRule, frontendRightHandPanelData.conditionRule, frontendRightHandPanelData.actionRule, frontendRightHandPanelData.gid, frontendRightHandPanelData.keyId, frontendRightHandPanelData.priority, frontendRightHandPanelData.type, frontendRightHandPanelData.split1, frontendRightHandPanelData.split2, frontendRightHandPanelData.len, frontendRightHandPanelData.range, frontendRightHandPanelData.substr, frontendRightHandPanelData.dec, frontendRightHandPanelData.inElementArray, frontendRightHandPanelData.setRuleArray);
        }
        else {
            pixelmappingService.updateRule($rootScope.base + 'pixel-data-engine-rule', frontendRightHandPanelData.parseRule, frontendRightHandPanelData.conditionRule, frontendRightHandPanelData.actionRule, frontendRightHandPanelData.gid, frontendRightHandPanelData.keyId, frontendRightHandPanelData.priority, frontendRightHandPanelData.type, frontendRightHandPanelData.split1, frontendRightHandPanelData.split2, frontendRightHandPanelData.len, frontendRightHandPanelData.range, frontendRightHandPanelData.substr, frontendRightHandPanelData.dec, frontendRightHandPanelData.inElementArray, frontendRightHandPanelData.setRuleArray);
        }
    };

    $scope.selectKeyId = function (frontendData) {
        //  If the user clicks on a <div>, we can get the ng-click to call this function, to set a new selected Customer.
        $scope.key_id = frontendData.key_id;
        $scope.getRule();
    }

    $scope.getRule = function () {
        //  Reset our list of orders  (when binded, this'll ensure the previous list of orders disappears from the screen while we're loading our JSON data)
        //$scope.listOfOrders = null;

        //  The user has selected a Customer from our Drop Down List.  Let's load this Customer's records.
        pixelmappingService.getRule($scope.key_id).success(function (backendData) {

            $scope.buttonText = 'Update Group';
            $scope.isUpdate = (backendData.key_id > 0) ? true : false;


            var processedResponseBackupRuleData = {
                "parseRule": null,
                "conditionRule": null,
                "actionRule": null,
                "gid": null,
                "keyId": null,
                "priority": null,
                "type": null,
                "split1": {
                    "column1": null
                },
                "split2": {
                    "column1": null,
                    "column2": null
                },
                "len": {
                    "column1": null
                },
                "range": {
                    "column1": null,
                    "column2": null
                },
                "substr": {
                    "column1": null,
                    "column2": null,
                    "column3": null
                },
                "dec": {
                    "column1": null
                },
                "inElementArray": [],
                "setRuleArray": []
            };

            // true if user click on the "edit button"
            if (gid != 0) {
                processedResponseBackupRuleData.gid = backendData.gid;
                processedResponseBackupRuleData.keyId = backendData.key_id;
                processedResponseBackupRuleData.type = backendData.type;
                processedResponseBackupRuleData.priority = backendData.priority;

                var parseRuleSplit = backendData.parse_rule.split("|");
                processedResponseBackupRuleData.parseRule = parseRuleSplit[0];
                if (parseRuleSplit[0] == 'split1') {
                    // true if -> -> split1|"|"
                    if (parseRuleSplit.length == 3) {
                        processedResponseBackupRuleData.split1.column1 = "\"|\"";
                    } else {
                        processedResponseBackupRuleData.split1.column1 = parseRuleSplit[1];
                    }
                } else if (parseRuleSplit[0] == 'split2') {
                    /*
                     * handle case of -> split2|"|"|"|"
                     * handle case of -> split2|"|"|,
                     * handle case of -> split2|,|"|"
                     * */
                    if (parseRuleSplit.length == 5) { // true if -> split2|"|"|"|"
                        processedResponseBackupRuleData.split2.column1 = "\"|\"";
                        processedResponseBackupRuleData.split2.column2 = "\"|\"";
                    } else if (parseRuleSplit.length == 4) {
                        // true if -> split2|"|"|,
                        // false if -> split2|,|"|"
                        if (parseRuleSplit[1] == "\"") {
                            processedResponseBackupRuleData.split2.column1 = "\"|\"";
                            processedResponseBackupRuleData.split2.column2 = parseRuleSplit[3];
                        } else {
                            processedResponseBackupRuleData.split2.column1 = parseRuleSplit[1];
                            processedResponseBackupRuleData.split2.column2 = "\"|\"";
                        }
                    } else {
                        processedResponseBackupRuleData.split2.column1 = parseRuleSplit[1];
                        processedResponseBackupRuleData.split2.column2 = parseRuleSplit[2];
                    }
                }


                var conditionRuleSplit = backendData.condition_rule.split("|");
                processedResponseBackupRuleData.conditionRule = conditionRuleSplit[0];
                if (conditionRuleSplit[0] == 'len') {
                    processedResponseBackupRuleData.len.column1 = conditionRuleSplit[1];
                } else if (conditionRuleSplit[0] == 'range') {
                    processedResponseBackupRuleData.range.column1 = conditionRuleSplit[1];
                    processedResponseBackupRuleData.range.column2 = conditionRuleSplit[2];
                } else if (conditionRuleSplit[0] == 'in') {
                    for (var i = 1; i < conditionRuleSplit.length; i++) {
                        var inObject = {
                            column1: i - 1,
                            column2: conditionRuleSplit[i]
                        }
                        processedResponseBackupRuleData.inElementArray.push(inObject);
                    }
                }

                var actionRuleSplit = backendData.action_rule.split("|");
                processedResponseBackupRuleData.actionRule = actionRuleSplit[0];
                if (actionRuleSplit[0] == 'substr') {
                    processedResponseBackupRuleData.substr.column1 = actionRuleSplit[1];
                    processedResponseBackupRuleData.substr.column2 = actionRuleSplit[2];
                    processedResponseBackupRuleData.substr.column3 = actionRuleSplit[3];
                } else if (actionRuleSplit[0] == 'set') {
                    for (var i = 1; i < actionRuleSplit.length; i++) {
                        var setObject = {
                            column1: i - 1,
                            column2: actionRuleSplit[i]
                        }
                        processedResponseBackupRuleData.setRuleArray.push(setObject);
                    }
                } else if (actionRuleSplit[0] == 'dec') {
                    processedResponseBackupRuleData.dec.column1 = actionRuleSplit[1];
                }
            }

            $scope.frontendRightHandPanelData = angular.copy(processedResponseBackupRuleData);


            // true if user click on the "add button"
            if (gid == 0) {
                //if (responseBackupRuleData.inElementArray == null) {
                //    responseBackupRuleData.inElementArray = [{
                //        column1: '0',
                //        column2: ''
                //    }];
                $scope.frontendRightHandPanelData.inElementArray = [{
                    column1: '0',
                    column2: ''
                }];
                // }

                //if (responseBackupRuleData.setRuleArray == null) {
                //    responseBackupRuleData.setRuleArray = [{
                //        column1: '0',
                //        column2: ''
                //    }];
                $scope.frontendRightHandPanelData.setRuleArray = [{
                    column1: '0',
                    column2: ''
                }];
                //  }
            }
        });
        //$http.get('http://inorthwind.azurewebsites.net/Service1.svc/getBasketsForCustomer/' + $scope.selectedCustomer)
        //    .success(function (data) {
        //        $scope.listOfOrders = data.GetBasketsForCustomerResult;
        //    })
        //    .error(function (data, status, headers, config) {
        //        $scope.errorMessage = "Couldn't load the list of Orders, error # " + status;
        //    });
    }
});


