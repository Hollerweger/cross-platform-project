angular.module('starter.controllers', [])

.config(function($compileProvider){  $compileProvider.imgSrcSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|tel):/);
})

.controller('uiElementsController', function($scope,Camera) {    
   $scope.getPhoto = function() {
    console.log('Getting camera');
    Camera.getPicture({
      quality: 75,
      targetWidth: 320,
      targetHeight: 320,
      saveToPhotoAlbum: false
    }).then(function(imageURI) {
      console.log(imageURI);
      $scope.lastPhoto = imageURI;
    }, function(err) {
      console.err(err);
    });
   };
})

.controller('CameraController', function($scope,Camera) {
   $scope.getPhoto = function() {
    console.log('Getting camera');
    Camera.getPicture({
      quality: 75,
      targetWidth: 320,
      targetHeight: 320,
      saveToPhotoAlbum: false
    }).then(function(imageURI) {
      console.log(imageURI);
      $scope.lastPhoto = imageURI;
    }, function(err) {
      console.err(err);
    });
   };
})

.controller('GeolocationController', function($scope,Geolocation) {

    var posOptions = {maximumAge: 10000, timeout: 10000, enableHighAccuracy: false};
    
    $scope.getPosition = function(){
        console.log('Getting position');
        Geolocation.getPosition(posOptions)
        .then(function (position) {
          var lat  = position.coords.latitude
          var long = position.coords.longitude

          $scope.lastPosition = position;
        }, function(err) {
          console.dir(err);
        });
    }
        
});
