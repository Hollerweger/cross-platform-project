//
//  FirstViewController.swift
//  IOS Reference
//
//  Created by Martin Hollerweger on 22.06.15.
//  Copyright (c) 2015 mhollerweger. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController,UIImagePickerControllerDelegate,UINavigationControllerDelegate {
  
  @IBOutlet weak var myImageView: UIImageView!
  let picker = UIImagePickerController()   //our controller.
  //Memory will be conserved a bit if you place this in the actions.
  // I did this to make code a bit more streamlined
  
  //MARK: - Methods
  // An alert method using the new iOS 8 UIAlertController instead of the deprecated UIAlertview
  // make the alert with the preferredstyle .Alert, make necessary actions, and then add the actions.
  // add to the handler a closure if you want the action to do anything.
  
  func noCamera(){
    //let alertVC = UIAlertController(title: "No Camera", message: "Sorry, this device has no camera", preferredStyle: .Alert)
    //let okAction = UIAlertAction(title: "OK", style:.Default, handler: nil)
    //alertVC.addAction(okAction)
   // presentViewController(alertVC, animated: true, completion: nil)
  }
  
  //MARK: - Actions
  //get a photo from the library. We present as a popover on iPad, and fullscreen on smaller devices.
  @IBAction func photoFromLibrary(sender: UIBarButtonItem) {
    picker.allowsEditing = false //2
    picker.sourceType = .PhotoLibrary //3
   // picker.modalPresentationStyle = .UPopover
    presentViewController(picker, animated: true, completion: nil)//4
   // picker.popoverPresentationController?.barButtonItem = sender
  }
  
  //take a picture, check if we have a camera first.
  @IBAction func shootPhoto(sender: UIButton) {
    if UIImagePickerController.availableCaptureModesForCameraDevice(.Rear) != nil {
      picker.allowsEditing = false
      picker.sourceType = UIImagePickerControllerSourceType.Camera
      picker.cameraCaptureMode = .Photo
      presentViewController(picker, animated: true, completion: nil)
    } else {
      noCamera()
    }
  }
    
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view, typically from a nib.
    picker.delegate = self   //the required delegate to get a photo back to the app.
  }
  //MARK: - Delegates
  //What to do when the picker returns with a photo
  func imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String: AnyObject]) {
    let chosenImage = info[UIImagePickerControllerOriginalImage] as! UIImage //2
    myImageView.contentMode = .ScaleAspectFit //3
    myImageView.image = chosenImage //4
    dismissViewControllerAnimated(true, completion: nil) //5
  }
  //What to do if the image picker cancels.
  func imagePickerControllerDidCancel(picker: UIImagePickerController) {
    dismissViewControllerAnimated(true, completion: nil)
  }
}

