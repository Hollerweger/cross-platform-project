// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'ngCordova','starter.controllers','starter.services'])

.config(function($compileProvider){
 $compileProvider.imgSrcSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|tel):/);
})

.run(    
    function($ionicPlatform) {
        $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    
   // if (window.StatusBar) {
        
//        $cordovaStatusbar.styleHex $WPHCConfig.cordova.statubar.color
//            if $WPHCConfig.cordova.statubar.show
//                $cordovaStatusbar.show()
//            else
//                $cordovaStatusbar.hide()
      // org.apache.cordova.statusbar required
      //StatusBar.styleDefault();
//      StatusBar.backgroundColorByHexString("#333");
//    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
  
.state('app', {
    url: "/app",
    abstract: true,
    templateUrl: function() {
        if (ionic.Platform.isAndroid()) {
            return  "templates/menu.html";
        }
        return "templates/menu-ios.html";
    }
  })


  .state('app.camera', {
    url: "/camera",
    views: {
      'menuContent': {
        templateUrl: "templates/camera.html",
        controller: 'CameraController'
      }
    }
  })

  .state('app.map', {
    url: "/map",
    views: {
      'menuContent': {
        templateUrl: "templates/map.html",
        controller: 'GeolocationController'
      }
    }
  })
    .state('app.uiElements', {
      url: "/uiElements",
      views: {
        'menuContent': {
          templateUrl: "templates/uiElements.html",
          controller: 'uiElementsController'
        }
      }
    });
    
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/uiElements');
});
