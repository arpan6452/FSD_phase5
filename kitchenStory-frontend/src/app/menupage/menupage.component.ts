import { ItemCartListService } from './../common/item-cart-list.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ItemServiceService } from '../common/item-service.service';

@Component({
  selector: 'app-menupage',
  templateUrl: './menupage.component.html',
  styleUrls: ['./menupage.component.css']
})
export class MenupageComponent implements OnInit {

  items: any = [];
  totalPrice: number=0;
  searchForm: any;
  cartItems: any= [];
  isSearchBttnDisabled: boolean = true;
  isDisabled: boolean = true;

  constructor(private itemService: ItemServiceService, private formBuilder: FormBuilder, private router: Router, private itemCartListService: ItemCartListService) {
    this.searchForm = this.formBuilder.group({
      itemCatagory: ['Beverage', Validators.required],
      itemCourse: ['Staters', Validators.required],
      itemType: ['',],
    })
   }

  ngOnInit(): void {
    this.viewAllMenu();
  }

  filterMenu(): void{
    if(this.searchForm.valid){
      if(this.searchForm.controls["itemType"].value == true){
        this.searchForm.controls["itemType"].setValue("Veg");
      }
      else{
        this.searchForm.controls["itemType"].setValue("");
      }
      this.itemService.searchItems(this.searchForm).subscribe(res =>{
        this.items = res;
      })
      this.totalPrice = 0;
    }else{
      this.isSearchBttnDisabled = true;
    }

  }

  updateTotalPrice(){
    for(let item of this.items){
      this.totalPrice = this.totalPrice + item.price;
    }
  }

  viewAllMenu(){
    this.itemService.getAllItems().subscribe(res =>{
      this.items = res;
    })
  }

  addQuantity(quantity, price, item){
    let priceInt = parseInt(price.innerText);
    let updatedQuantity: number = parseInt(quantity.innerText) + 1;
    quantity.innerText = updatedQuantity.toString();
    this.totalPrice = this.totalPrice + priceInt;

    this.cartItems.push(item);

    if(this.totalPrice > 0){
      this.isDisabled = false;
    }else{
      this.isDisabled = true;
    }
  }

  deductQuantity(quantity, price, item){
    let priceInt = parseInt(price.innerText);
    let currentValue = parseInt(quantity.innerText) - 1;
    if(currentValue >= 0){
      let updatedQuantity: number = parseInt(quantity.innerText) - 1;
      quantity.innerText = updatedQuantity.toString();
      this.totalPrice = this.totalPrice - priceInt;
      const index = this.cartItems.indexOf(item);
      if (index > -1) {
        this.cartItems.splice(index, 1);
      }
    }
    if(this.totalPrice > 0){
      this.isDisabled = false;
    }else{
      this.isDisabled = true;
    }
  }

  gotToCart(){
    this.itemCartListService.addItemCartList(this.cartItems);
    this.router.navigateByUrl("/cartPage");
  }

  hasError(field: any){
    return (this.searchForm.get(field)?.invalid && this.searchForm.get(field)?.touched && this.searchForm.get(field)?.errors);
  }

  get f(){ return this.searchForm.controls;}
}



