/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider) {
        var base = '/';

        // use the HTML5 History API
        $locationProvider.html5Mode(true);

        $routeProvider
            .when('/', {
                title: 'Adobe Mappings',
                templateUrl: 'static/html/adobe-pages/mappings.html',
                controller: 'listCtrlAdobe'
            })
            .when(base + 'login', {
                templateUrl: 'static/html/usermanagement/login/login.view.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .when(base + 'register', {
                templateUrl: 'static/html/usermanagement/register/register.view.html',
                controller: 'RegisterController',
                controllerAs: 'vm'
            })
            .when(base + 'manageusers', {
                templateUrl: 'static/html/usermanagement/manage-users/manageusers.view.html',
                controller: 'ManageusersController',
                controllerAs: 'vm'
            })
            .when(base + 'adobe', {
                title: 'Adobe Mappings',
                templateUrl: 'static/html/adobe-pages/mappings.html',
                controller: 'listCtrlAdobe'
            })
            .when(base + 'adobe/edit-mapping/:mappingID', {
                title: 'Edit Adobe Mappings',
                templateUrl: 'static/html/adobe-pages/edit-mapping.html',
                controller: 'editCtrlAdobe',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'adobe');
                    }
                }
            })
            .when(base + 'derive-conversion', {
                title: 'Derive Combo Pixel Mappings',
                templateUrl: 'static/html/derive-conversion-pages/mappings.html',
                controller: 'listCtrlDeriveConversion'
            })
            .when(base + 'derive-conversion/edit-mapping/:mappingID', {
                title: 'Edit Derive Combo Pixel Mappings',
                templateUrl: 'static/html/derive-conversion-pages/edit-mapping.html',
                controller: 'editCtrlDeriveConversion',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'derive-conversion');
                    }
                }
            })
            .when(base + 'krux-dpkey', {
                title: 'Krux Dpkey Mappings',
                templateUrl: 'static/html/krux-dpkey-pages/mappings.html',
                controller: 'listCtrlKruxDpkey'
            })
            .when(base + 'krux-dpkey/edit-mapping/:mappingID', {
                title: 'Edit Krux Dpkey Mappings',
                templateUrl: 'static/html/krux-dpkey-pages/edit-mapping.html',
                controller: 'editCtrlKruxDpkey',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'krux-dpkey');
                    }
                }
            })

            .when(base + 'liveramp', {
                title: 'Liveramp Mappings',
                templateUrl: 'static/html/liveramp-pages/mappings.html',
                controller: 'listCtrlLiveramp'
            })
            .when(base + 'liveramp/edit-dp-mapping/:mappingID', {
                title: 'Edit Liveramp DP Mappings',
                templateUrl: 'static/html/liveramp-pages/edit-dp-mapping.html',
                controller: 'editCtrlLiverampDp',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'liveramp-dp');
                    }
                }
            })
            .when(base + 'liveramp/edit-key-mapping/:mappingID', {
                title: 'Edit Liveramp Key Mappings',
                templateUrl: 'static/html/liveramp-pages/edit-key-mapping.html',
                controller: 'editCtrlLiverampKey',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'liveramp-key');
                    }
                }
            })

            .when(base + 'dbm', {
                title: 'Dbm Mappings',
                templateUrl: 'static/html/dbm-pages/mappings.html',
                controller: 'listCtrlDbm'
            })
            .when(base + 'dbm/edit-mapping/:mappingID', {
                title: 'Edit Dbm Mappings',
                templateUrl: 'static/html/dbm-pages/edit-mapping.html',
                controller: 'editCtrlDbm',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'dbm');
                    }
                }
            })

            .when(base + 'facebook', {
                title: 'Facebook Mappings',
                templateUrl: 'static/html/facebook-pages/mappings.html',
                controller: 'listCtrlFacebook'
            })
            .when(base + 'facebook/edit-pixel-mapping/:mappingID', {
                title: 'Edit Facebook Pixel Mappings',
                templateUrl: 'static/html/facebook-pages/edit-pixel-mapping.html',
                controller: 'editCtrlFacebookPixel',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'facebook-pixel');
                    }
                }
            })
            .when(base + 'facebook/edit-dp-mapping/:mappingID', {
                title: 'Edit Facebook DP Mappings',
                templateUrl: 'static/html/facebook-pages/edit-dp-mapping.html',
                controller: 'editCtrlFacebookDp',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'facebook-dp');
                    }
                }
            })
            .when(base + 'facebook/edit-key-mapping/:mappingID', {
                title: 'Edit Facebook Key Mappings',
                templateUrl: 'static/html/facebook-pages/edit-key-mapping.html',
                controller: 'editCtrlFacebookKey',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return pixelmappingService.getMapping(mappingID, 'facebook-key');
                    }
                }
            })
            .when(base + 'pixel-data-engine-group', {
                title: 'Pixel Data Engine Groups',
                templateUrl: 'static/html/pixel-data-engine-config/list-group.html',
                controller: 'listPixelGroups'
            })
            .when(base + 'group/edit-group/:keyId', {
                title: 'Edit Pixel Data Engine Group',
                templateUrl: 'static/html/pixel-data-engine-config/edit-group.html',
                controller: 'editPixelGroup',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var keyId = $route.current.params.keyId;
                        return pixelmappingService.getGroup(keyId);
                    }
                }
            })
            .when(base + 'group/edit-rules/:gid', {
                title: 'Group Id :gid',
                templateUrl: 'static/html/pixel-data-engine-config/edit-rules.html',
                controller: 'editSameGroup',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var gid = $route.current.params.gid;
                        return pixelmappingService.getSameGroup(gid);
                    }
                }
            })
            .when(base + 'pixel-data-engine-rule', {
                title: 'Pixel Data Engine Rules',
                templateUrl: 'static/html/pixel-data-engine-config/list-rule.html',
                controller: 'listPixelRules'
            })
            .when(base + 'rule/edit-rule/:keyId', {
                title: 'Edit Pixel Data Engine Rule',
                templateUrl: 'static/html/pixel-data-engine-config/edit-rule.html',
                controller: 'editPixelRule',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var gid = $route.current.params.gid;
                        var keyId = $route.current.params.keyId;
                        var priority = $route.current.params.priority;
                        return pixelmappingService.getRule(gid, keyId, priority);
                    }
                }
            })
            .otherwise({
                templateUrl: 'static/html/usermanagement/login/login.view.html',
                redirectTo: base + 'login'
            });
    }]);

app.run(['$location', '$rootScope', '$cookieStore', '$http', function ($location, $rootScope, $cookieStore, $http) {
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

    $rootScope.isroot = false;
    $rootScope.base = '';

    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;

        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), [$rootScope.base + 'login', $rootScope.base + 'register']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path($rootScope.base + 'login');
        }
    });
}]);