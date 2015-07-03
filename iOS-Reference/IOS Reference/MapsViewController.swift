//
//  MapsViewController.swift
//  IOS Reference
//
//  Created by Martin Hollerweger on 30.06.15.
//  Copyright (c) 2015 mhollerweger. All rights reserved.
//

import UIKit
import CoreLocation

class MapsViewController: UIViewController,CLLocationManagerDelegate {
  
  @IBOutlet var latitude : UILabel!
  @IBOutlet var longitude : UILabel!
  
  var locationManager:CLLocationManager!
  
  override func viewDidLoad() {
    super.viewDidLoad()
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  @IBAction func requestLocation(sender: AnyObject) {
    if(locationManager == nil){
    	locationManager = CLLocationManager()
    	locationManager.delegate = self
    	locationManager.desiredAccuracy = kCLLocationAccuracyKilometer
    	//locationManager.requestWhenInUseAuthorization()
    	locationManager.startUpdatingLocation()
    }
    else{
      locationManager.startUpdatingLocation()
    }
  }
  
    func locationManager(manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {

    let locationArray = locations as NSArray
    let locationObj = locationArray.lastObject as! CLLocation
    let coord = locationObj.coordinate
      
    print(coord.latitude)
    print(coord.longitude)
    
    latitude.text = coord.latitude.description
    longitude.text = coord.longitude.description
    
    locationManager.stopUpdatingLocation()
  }
  
  
}


