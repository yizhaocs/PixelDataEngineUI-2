/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("pixelmappingService", ['$http', '$location', '$rootScope',
    function ($http, $location, $rootScope) {
        var obj = {};

        /**
         *   Rest API Services
         **/
        obj.insertRule = function (username, password, redirectPath) {
            // , inElementArray: inElementArray, setRuleArray: setRuleArray
            return $http.post($rootScope.base + 'login', {username: username, password: password}).then(function (status) {
                $location.path($rootScope.base + 'adobe');
                return status.data;
            });
        };

        obj.getMappings = function (type) {
            return $http.get($rootScope.base + 'mappings?type=' + type);
        }

        obj.getMapping = function (mappingID, type) {
            return $http.get($rootScope.base + 'mapping?id=' + mappingID + '&type=' + type);
        }

        obj.insertMapping = function (redirectPath, mapping, type) {
            return $http.post($rootScope.base + 'insertMapping', {mapping: mapping, type: type}).then(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        obj.updateMapping = function (redirectPath, id, mapping, type) {
            return $http.post($rootScope.base + 'updateMapping', {id: id, mapping: mapping, type: type}).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.deleteMapping = function (redirectPath, id, type) {
            return $http.delete($rootScope.base + 'deleteMapping?id=' + id + '&type=' + type).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        /*
         * group service begin
         * */
        obj.getGroups = function () {
            return $http.get($rootScope.base + 'getGroups');
        }

        obj.getGroup = function (keyId) {
            return $http.get($rootScope.base + 'group?id=' + keyId);
        }


        /*
         * rule service begin
         * */
        obj.getRules = function () {
            return $http.get($rootScope.base + 'getRules');
        }

        obj.getRule = function (keyID) {
            return $http.get($rootScope.base + 'getRule?keyid=' + keyID);
        }

        obj.updateRule = function (redirectPath, parseRule, conditionRule, actionRule, keyId, type, split1, split2, len, range, substr, dec, inElementArray, setRuleArray) {
            return $http.post($rootScope.base + 'updateRule', {
                keyId: keyId,
                type: type,
                parseRule: parseRule,
                split1: split1,
                split2: split2,
                conditionRule: conditionRule,
                len: len,
                range: range,
                actionRule: actionRule,
                substr: substr,
                dec: dec,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray
            }).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.deleteRule = function (redirectPath, keyid) {
            return $http.delete($rootScope.base + 'deleteRule?keyid=' + keyid).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.insertRule = function (redirectPath, parseRule, conditionRule, actionRule, keyId, type, split1, split2, len, range, substr, dec, inElementArray, setRuleArray) {
            return $http.post($rootScope.base + 'insertRule', {
                keyId: keyId,
                type: type,
                parseRule: parseRule,
                split1: split1,
                split2: split2,
                conditionRule: conditionRule,
                len: len,
                range: range,
                actionRule: actionRule,
                substr: substr,
                dec: dec,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray
            }).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return obj;
    }
]);
