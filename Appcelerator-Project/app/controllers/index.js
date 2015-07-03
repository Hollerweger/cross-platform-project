$.index.open();
function openCamera(e) {

	Titanium.Media.showCamera({
	 
		success:function(event)
		{
			var image = event.media;
	 
			Ti.API.debug('Our type was: '+event.mediaType);
			if(event.mediaType == Ti.Media.MEDIA_TYPE_PHOTO)
			{
					$.cameraImage.setImage(event.media);
				// var imageView = Ti.UI.createImageView({width:win.width,height:win.height,image:event.media});
				// win.add(imageView);  
			}
			else
			{
				alert("got the wrong type back ="+event.mediaType);
			}
		},
		cancel:function()
		{
	               alert('You canceled the action.');
		},
		error:function(error)
		{
			// create alert
			var a = Titanium.UI.createAlertDialog({title:'Camera'});
	 
			// set message
			if (error.code == Titanium.Media.NO_CAMERA)
			{
				a.setMessage('Please run this test on device');
			}
			else
			{
				a.setMessage('Unexpected error: ' + error.code);
			}
	 
			// show alert
			a.show();
		},
		saveToPhotoGallery:true,
		allowEditing:true,
		mediaTypes:[Ti.Media.MEDIA_TYPE_VIDEO,Ti.Media.MEDIA_TYPE_PHOTO],
	});
};

function requestLocation(e){
	if (Ti.Geolocation.locationServicesEnabled) {
	  	$.latitude.setText("47.121231231");
	  	$.longitude.setText("12.123123123");
	} else {
	    alert('Please enable location services');
	}
};
