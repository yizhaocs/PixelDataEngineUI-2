/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listPixelGroups', function ($scope, pixelmappingService) {
    pixelmappingService.getGroups().then(function (backendData) {
        for (var i = 0; i < backendData.data.length; i++) {
            if (backendData.data[i].group_type == 1) {
                backendData.data[i].group_type = 'independent';
            } else if (backendData.data[i].group_type == 2) {
                backendData.data[i].group_type = 'sequential';
            }
        }

        $scope.frontendData = backendData.data;
    });
});


app.controller('editPixelGroup', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var keyId = ($routeParams.keyId) ? parseInt($routeParams.keyId) : 0;
    $rootScope.title = (keyId > 0) ? 'Edit Group' : 'Add Group';
    $scope.buttonText = (keyId > 0) ? 'Update Group' : 'Add New Group';
    $scope.isUpdate = (keyId > 0) ? true : false;
    $scope.keyIdDisable = (keyId > 0) ? true : false;

    if (backendData.data.group_type == 1) {
        backendData.data.group_type = 'independent';
    } else if (backendData.data.group_type == 2) {
        backendData.data.group_type = 'sequential';
    }

    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    };

    $scope.deleteGroup = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.trigger_key_id) == true)
            pixelmappingService.deleteGroup($rootScope.base + 'pixel-data-engine-group', frontendData.trigger_key_id);
    };

    $scope.saveGroup = function (frontendData) {
        if(frontendData.group_type == 'independent'){
            frontendData.group_type = 1;
        }else if(frontendData.group_type == 'sequential'){
            frontendData.group_type = 2;
        }
        if (keyId <= 0) {
            pixelmappingService.insertGroup($rootScope.base + 'pixel-data-engine-group', frontendData);
        } else {
            pixelmappingService.updateGroup($rootScope.base + 'pixel-data-engine-group', keyId, frontendData);
        }
    };
});

app.controller('editSameGroup', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var gid = ($routeParams.gid) ? parseInt($routeParams.gid) : 0;
    $scope.title = 'Group ID:' + gid;
    $scope.buttonText = 'Add New Rule';
    $scope.isUpdate = false; // false to get rid of "Delete" button
    $scope.groupIdInputDisable = true;
    $scope.keyIdDisable = false;
    var frontendRightHandPanelDataForClean = '';

    $scope.initRuleData = function () {
        $scope.processedResponseBackupRuleData = {
            "parseRule": null,
            "conditionRule": null,
            "actionRule": null,
            "gid": gid,
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
                "column1": null,
                "column2": null
            },
            "seg": {
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

        $scope.frontendRightHandPanelData = angular.copy($scope.processedResponseBackupRuleData);

        // if no inElementArray || setRuleArray data from backend, then init them
        if ($scope.frontendRightHandPanelData.inElementArray.length == 0) {
            $scope.frontendRightHandPanelData.inElementArray = [{
                column1: '0',
                column2: ''
            }];
        }

        if ($scope.frontendRightHandPanelData.setRuleArray.length == 0) {
            $scope.frontendRightHandPanelData.setRuleArray = [{
                column1: '0',
                column2: ''
            }];
        }
    };

    $scope.initRuleData();

    // we need to order the priority col in the front, so we have to parse the 'priority' from string to int type
    $scope.sortPriority = function (data) {
        for (var i = 0; i < data.length; i++) {
            data[i].priority = parseInt(data[i].priority);
        }
    };

    $scope.sortPriority(backendData.data);
    $scope.frontendLeftHandPanelData = angular.copy(backendData.data);


    /*
     *
     * functions
     * */
    // button fuctions
    $scope.addSetRule = function () {
        $scope.frontendRightHandPanelData.setRuleArray.push({
            column1: $scope.frontendRightHandPanelData.setRuleArray.length,
            column2: ''
        });

    };

    $scope.removeSetRule = function () {
        $scope.frontendRightHandPanelData.setRuleArray.pop();
    };

    $scope.addInElement = function () {
        $scope.frontendRightHandPanelData.inElementArray.push({
            column1: $scope.frontendRightHandPanelData.inElementArray.length,
            column2: ''
        });
    };

    $scope.removeInElement = function () {
        $scope.frontendRightHandPanelData.inElementArray.pop();
    };


    $scope.isClean = function () {
        return angular.equals(frontendRightHandPanelDataForClean, $scope.frontendRightHandPanelData);
    }

    // when use click on "Add new rule" button
    $scope.addRule = function () {
        $scope.buttonText = 'Add New Rule';
        $scope.isUpdate = false; // false to get rid of "Delete" button
        $scope.groupIdInputDisable = true;
        $scope.keyIdDisable = false;
        $scope.initRuleData();
    };

    $scope.deleteRule = function (frontendRightHandPanelData) {
        if (confirm("Are you sure to delete rule number: " + frontendRightHandPanelData.keyId) == true) {
            pixelmappingService.deleteRule($rootScope.base + 'group/edit-rules/' + gid, frontendRightHandPanelData.gid, frontendRightHandPanelData.keyId, $scope.leftPanelSelectedPriority).then(function (backendData) {
                $scope.refreshLeftPanel();
                $scope.frontendRightHandPanelData = '';
            });
        }
    };

    $scope.saveRule = function (frontendRightHandPanelData) {
        // true if "Add new rule"
        if ($scope.isUpdate == false) {
            // validation for Duplicate Priorities in "insertion" scenario
            for (var i = 0; i < $scope.frontendLeftHandPanelData.length; i++) {
                if ($scope.frontendLeftHandPanelData[i].priority == $scope.frontendRightHandPanelData.priority) {
                    alert("Disallow Duplicate Priorities");
                    return;
                }
            }
            pixelmappingService.insertRule($rootScope.base + 'group/edit-rules/' + gid, frontendRightHandPanelData.parseRule, frontendRightHandPanelData.conditionRule, frontendRightHandPanelData.actionRule, frontendRightHandPanelData.gid, frontendRightHandPanelData.keyId, frontendRightHandPanelData.priority, frontendRightHandPanelData.type, frontendRightHandPanelData.split1, frontendRightHandPanelData.split2, frontendRightHandPanelData.len, frontendRightHandPanelData.seg, frontendRightHandPanelData.range, frontendRightHandPanelData.substr, frontendRightHandPanelData.dec, frontendRightHandPanelData.inElementArray, frontendRightHandPanelData.setRuleArray).then(function (backendData) {
                $scope.refreshLeftPanel();
            });
        } else {
            // validation for Duplicate Priorities in "updation" scenario
            for (var i = 0; i < $scope.frontendLeftHandPanelData.length; i++) {
                if ($scope.frontendLeftHandPanelData[i].key_id === $scope.frontendRightHandPanelData.keyId && $scope.frontendLeftHandPanelData[i].priority === $scope.frontendRightHandPanelData.priority) {
                    break;
                }
                if ($scope.frontendLeftHandPanelData[i].priority == $scope.frontendRightHandPanelData.priority) {
                    alert("Disallow Duplicate Priorities");
                    return;
                }

            }
            pixelmappingService.updateRule($rootScope.base + 'group/edit-rules/' + gid, frontendRightHandPanelData.parseRule, frontendRightHandPanelData.conditionRule, frontendRightHandPanelData.actionRule, frontendRightHandPanelData.gid, frontendRightHandPanelData.keyId, $scope.leftPanelSelectedPriority, frontendRightHandPanelData.priority, frontendRightHandPanelData.type, frontendRightHandPanelData.split1, frontendRightHandPanelData.split2, frontendRightHandPanelData.len, frontendRightHandPanelData.seg, frontendRightHandPanelData.range, frontendRightHandPanelData.substr, frontendRightHandPanelData.dec, frontendRightHandPanelData.inElementArray, frontendRightHandPanelData.setRuleArray).then(function (backendData) {
                $scope.refreshLeftPanel();
            });
        }
    };

    $scope.refreshLeftPanel = function () {
        pixelmappingService.getSameGroup(gid).success(function (backendData) {
            $scope.sortPriority(backendData);
            $scope.frontendLeftHandPanelData = angular.copy(backendData);
        });
    };

    $scope.selectKeyId = function (frontendData) {
        //  If the user clicks on a <div>, we can get the ng-click to call this function, to set a new selected Customer.
        $scope.leftPanelSelectedkeyid = frontendData.key_id;
        $scope.leftPanelSelectedPriority = frontendData.priority;
        $scope.leftPanelSelectedGid = frontendData.gid;
        $scope.getRule();
    };

    $scope.testRule = function (frontendRightHandPanelData) {
        pixelmappingService.testRule($rootScope.base + 'group/edit-rules/' + gid,
            frontendRightHandPanelData.parseRule, frontendRightHandPanelData.conditionRule, frontendRightHandPanelData.actionRule, frontendRightHandPanelData.gid,
            frontendRightHandPanelData.keyId, $scope.leftPanelSelectedPriority, frontendRightHandPanelData.priority, frontendRightHandPanelData.type, frontendRightHandPanelData.split1,
            frontendRightHandPanelData.split2, frontendRightHandPanelData.len,frontendRightHandPanelData.seg,frontendRightHandPanelData.range, frontendRightHandPanelData.substr, frontendRightHandPanelData.dec,
            frontendRightHandPanelData.inElementArray, frontendRightHandPanelData.setRuleArray,
            frontendRightHandPanelData.testValue).then(function (backendData) {
            var result = "";
            for(var i = 0; i < backendData.data.length; i++){
                result += backendData.data[i] + "\n";
            }

            $scope.frontendRightHandPanelData.testResult = result;
        });
    };

    $scope.getRule = function () {
        $scope.buttonText = 'Update Rule';
        $scope.isUpdate = true; // true to display "Delete" button
        $scope.groupIdInputDisable = true;
        $scope.keyIdDisable = true;
        pixelmappingService.getRule($scope.leftPanelSelectedGid, $scope.leftPanelSelectedkeyid, $scope.leftPanelSelectedPriority).success(function (backendData) {
            // frontendRightHandPanelDataForClean = backendData;

            $scope.initRuleData();

            // true if user click on the "edit button"
            if (gid != 0) {
                $scope.processedResponseBackupRuleData.gid = backendData.gid;
                $scope.processedResponseBackupRuleData.keyId = backendData.key_id;
                $scope.processedResponseBackupRuleData.type = backendData.type;
                $scope.processedResponseBackupRuleData.priority = parseInt(backendData.priority);

                var parseRuleSplit = backendData.parse_rule.split("|");
                $scope.processedResponseBackupRuleData.parseRule = parseRuleSplit[0];
                if (parseRuleSplit[0] == 'split1') {
                    // true if -> -> split1|"|"
                    if (parseRuleSplit.length == 3) {
                        $scope.processedResponseBackupRuleData.split1.column1 = "\"|\"";
                    } else {
                        $scope.processedResponseBackupRuleData.split1.column1 = parseRuleSplit[1];
                    }
                } else if (parseRuleSplit[0] == 'split2') {
                    /*
                     * handle case of -> split2|"|"|"|"
                     * handle case of -> split2|"|"|,
                     * handle case of -> split2|,|"|"
                     * */
                    if (parseRuleSplit.length == 5) { // true if -> split2|"|"|"|"
                        $scope.processedResponseBackupRuleData.split2.column1 = "\"|\"";
                        $scope.processedResponseBackupRuleData.split2.column2 = "\"|\"";
                    } else if (parseRuleSplit.length == 4) {
                        // true if -> split2|"|"|,
                        // false if -> split2|,|"|"
                        if (parseRuleSplit[1] == "\"") {
                            $scope.processedResponseBackupRuleData.split2.column1 = "\"|\"";
                            $scope.processedResponseBackupRuleData.split2.column2 = parseRuleSplit[3];
                        } else {
                            $scope.processedResponseBackupRuleData.split2.column1 = parseRuleSplit[1];
                            $scope.processedResponseBackupRuleData.split2.column2 = "\"|\"";
                        }
                    } else {
                        $scope.processedResponseBackupRuleData.split2.column1 = parseRuleSplit[1];
                        $scope.processedResponseBackupRuleData.split2.column2 = parseRuleSplit[2];
                    }
                }


                var conditionRuleSplit = backendData.condition_rule.split("|");
                $scope.processedResponseBackupRuleData.conditionRule = conditionRuleSplit[0];
                if (conditionRuleSplit[0] == 'len') {
                    $scope.processedResponseBackupRuleData.len.column1 = conditionRuleSplit[1];
                    $scope.processedResponseBackupRuleData.len.column2 = conditionRuleSplit[2];
                } else if (conditionRuleSplit[0] == 'range') {
                    $scope.processedResponseBackupRuleData.range.column1 = conditionRuleSplit[1];
                    $scope.processedResponseBackupRuleData.range.column2 = conditionRuleSplit[2];
                } else if (conditionRuleSplit[0] == 'in') {
                    for (var i = 1; i < conditionRuleSplit.length; i++) {
                        var inObject = {
                            column1: i - 1,
                            column2: conditionRuleSplit[i]
                        }
                        $scope.processedResponseBackupRuleData.inElementArray.push(inObject);
                    }
                } else if (conditionRuleSplit[0] == 'seg') {
                    $scope.processedResponseBackupRuleData.seg.column1 = conditionRuleSplit[1];
                }

                var actionRuleSplit = backendData.action_rule.split("|");
                $scope.processedResponseBackupRuleData.actionRule = actionRuleSplit[0];
                if (actionRuleSplit[0] == 'substr') {
                    $scope.processedResponseBackupRuleData.substr.column1 = actionRuleSplit[1];
                    $scope.processedResponseBackupRuleData.substr.column2 = actionRuleSplit[2];
                    $scope.processedResponseBackupRuleData.substr.column3 = actionRuleSplit[3];
                } else if (actionRuleSplit[0] == 'set') {
                    for (var i = 1; i < actionRuleSplit.length; i++) {
                        var setObject = {
                            column1: i - 1,
                            column2: actionRuleSplit[i]
                        }
                        $scope.processedResponseBackupRuleData.setRuleArray.push(setObject);
                    }
                } else if (actionRuleSplit[0] == 'dec') {
                    $scope.processedResponseBackupRuleData.dec.column1 = actionRuleSplit[1];
                }
            }

            $scope.frontendRightHandPanelData = angular.copy($scope.processedResponseBackupRuleData);

            // if no inElementArray || setRuleArray data from backend, then init them
            if ($scope.frontendRightHandPanelData.inElementArray.length == 0) {
                $scope.frontendRightHandPanelData.inElementArray = [{
                    column1: '0',
                    column2: ''
                }];
            }

            if ($scope.frontendRightHandPanelData.setRuleArray.length == 0) {
                $scope.frontendRightHandPanelData.setRuleArray = [{
                    column1: '0',
                    column2: ''
                }];
            }

            frontendRightHandPanelDataForClean = angular.copy($scope.frontendRightHandPanelData);
        });
    }
});


