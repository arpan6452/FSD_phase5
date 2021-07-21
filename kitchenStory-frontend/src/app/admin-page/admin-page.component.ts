import { UserServiceService } from './../common/user-service.service';
// import { UserDataService } from './../common/user-data.service';
import { ItemServiceService } from './../common/item-service.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  items: any = [];
  createItemForm: any;
  updateItemForm: any;
  updatePasswordForm: any;
  userDetails: any;
  userDetail: user;

  constructor( private userService: UserServiceService, private fb: FormBuilder, private itemService: ItemServiceService, private router: Router) {
      this.createItemForm = this.fb.group({
        itemName: ['', Validators.required],
        itemCatagory: ['', Validators.required],
        itemCourse: ['', Validators.required],
        itemType: ['', Validators.required],
        price: ['', Validators.required]
      })
      this.updateItemForm = this.fb.group({
        itemid: ['', Validators.required],
        itemName: ['', Validators.required],
        itemCatagory: ['', Validators.required],
        itemCourse: ['', Validators.required],
        itemType: ['', Validators.required],
        price: ['', Validators.required]
      })

      this.updatePasswordForm = this.fb.group({
        oldpassword: ['', Validators.required],
        newpassword: ['', Validators.required]
      })

      //this.userDetails = this.userService.getAuthentication();
      // this.userDetail = this.userDetails[0];
   }

  ngOnInit(): void {
    this.getAllElements();
  }

  updatePassword(){
    this.userService.updatePassword(this.updatePasswordForm.get("oldpassword").value, this.updatePasswordForm.get("newpassword").value, this.userDetails[0]).subscribe( res => {
      console.log(res);
      this.router.navigateByUrl("/admin");
    }, error => {
      console.log(error);
    });
  }

  openForm(item){
    this.updateItemForm.controls['itemid'].setValue(item.itemid);
    this.updateItemForm.controls['itemName'].setValue(item.itemName);
    this.updateItemForm.controls['itemCatagory'].setValue(item.itemCatagory);
    this.updateItemForm.controls['itemCourse'].setValue(item.itemCourse);
    this.updateItemForm.controls['itemType'].setValue(item.itemType);
    this.updateItemForm.controls['price'].setValue(item.price);
  }

  createItemSubmit(){
    this.itemService.createItem(this.createItemForm).subscribe( res => {

      this.getAllElements();
      this.createItemForm.reset();
    }, error => {
      console.log(error);
    });
  }

  updateItemSubmit(close){
    close.click();
    this.itemService.updateItem(this.updateItemForm.get("itemid").value, this.updateItemForm).subscribe( res => {
      this.getAllElements();
      // this.createItemForm.reset();
    }, error => {
      console.log(error);
    });
  }

  deleteItem(item){
    this.itemService.deleteItems(item.itemid).subscribe( data =>{
      this.getAllElements();
    }, error =>{
      //console.log(error);
    })
  }

  getAllElements(){
    this.itemService.getAllItems().subscribe(res =>{
      this.items = res;
    });
  }

}

interface user{
  userid: number,
  email: string,
  password: string,
  address1: string,
  address2: string,
  city: string,
  state: string,
  pincode: number
}
