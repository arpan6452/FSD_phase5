import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ItemServiceService {
  // public baseURL = 'http://localhost:9898';
  //public baseURL = 'http://kitchenstory-env.eba-sptqgsam.us-east-2.elasticbeanstalk.com';
  public baseURL = 'http://kstory-env.eba-beiz4uqk.us-east-2.elasticbeanstalk.com/items';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  httpOptions = {
    // headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:3000' , 'Access-Control-Allow-Credentials': 'true' }),
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://kstory-env.eba-beiz4uqk.us-east-2.elasticbeanstalk.com' , 'Access-Control-Allow-Credentials': 'true' }),
  };

  constructor(private http: HttpClient) { }

  createItem(itemDetails){
    const itemForm = {
      itemName: itemDetails.get("itemName").value,
      itemCatagory: itemDetails.get("itemCatagory").value,
      itemCourse: itemDetails.get("itemCourse").value,
      itemType: itemDetails.get("itemType").value,
      price: itemDetails.get("price").value,
    };
    return this.http.request('POST', `${this.baseURL}/item`, {
      body: itemForm,
    }).pipe(

    );
  }


  updateItem(itemId, itemDetails){
    const itemForm = {
      itemName: itemDetails.get("itemName").value,
      itemCatagory: itemDetails.get("itemCatagory").value,
      itemCourse: itemDetails.get("itemCourse").value,
      itemType: itemDetails.get("itemType").value,
      price: itemDetails.get("price").value,
    };
    return this.http.request('PUT', `${this.baseURL}/item/${itemId}`, {
      body: itemForm,
    }).pipe(

    );
  }

  getAllItems(){
    return this.http.get(`${this.baseURL}/items`);
  }

  searchItems(searchForm){//itemCatagory, itemCourse, itemType
    let itemCatagory=searchForm.controls["itemCatagory"].value;
    let itemCourse = searchForm.controls["itemCourse"].value;
    let itemType = searchForm.controls["itemType"].value;
    // let data = `${this.baseURL}/items/filter?itemCatagory=${itemCatagory}&itemCourse=${itemCourse}&itemType=${itemType}`;
    return this.http.get(`${this.baseURL}/item/filter?itemCatagory=${itemCatagory}&itemCourse=${itemCourse}&itemType=${itemType}`);
  }

  deleteItems(id){
    return this.http.request('DELETE', `${this.baseURL}/item/${id}`);
  }
}
