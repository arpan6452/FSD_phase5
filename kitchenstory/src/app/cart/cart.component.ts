import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ItemCartListService } from '../common/item-cart-list.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItemList: any;
  billedList: any[]= [];
  itemIdList: string="";
  totalPrice: number = 0;
  gst: number = 0;
  sgst: number = 0;
  totalPriceWithTax: number = 0;
  cartForm: any;
  fullAddress: string = "";
  showModal: boolean = true;


  constructor(private fb: FormBuilder, private itemCartListService: ItemCartListService) {
    this.cartForm = this.fb.group({
      username: ['',Validators.required],
      email: ['',Validators.required],
      address1: ['',Validators.required],
      address2: ['',Validators.required],
      city: ['',Validators.required],
      state: ['',Validators.required],
      pincode: ['',Validators.required],
    });

    this.cartItemList = this.itemCartListService.getItemCartList();
    for(let cartItem of this.cartItemList){
      if(!(this.itemIdList.includes(cartItem.itemid))){
        this.billedList.push(cartItem);
        this.itemIdList = this.itemIdList + ";" + cartItem.itemid;
      }else{
        cartItem.price = cartItem.price + cartItem.price;
      }
    }
    for(let billedItem of this.billedList){
      this.totalPrice = this.totalPrice + billedItem.price;
    }

    this.gst = .18 * this.totalPrice;
    this.sgst = .17 * this.totalPrice;

    this.totalPriceWithTax = this.totalPrice + this.gst + this.sgst;

  }

  ngOnInit(): void {

  }

  confirmOrder(){
    if(this.cartForm.valid){
      this.fullAddress = this.cartForm.get("address1").value + " , " + this.cartForm.get("address2").value + " , " +
                        this.cartForm.get("city").value + " , " + this.cartForm.get("state").value + " , Pincode: " +
                        this.cartForm.get("pincode").value;
    }else{
      this.validateForm(this.cartForm);
    }
  }

  private validateForm(form:any) {
    Object.keys(form.controls).forEach(field=>{
     const control =  form.get(field);
     if(control instanceof FormControl){
      control.markAsTouched({ onlySelf : true});
     } else{
      this.validateForm(control);
     }
    });
  }

  public hasError(field:any){
    return ( this.cartForm.get(field)?.invalid && this.cartForm.get(field)?.touched &&
    this.cartForm.get(field)?.errors);
  }

  get f(){ return this.cartForm.controls;}

}
