import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  //public baseURL = 'http://localhost:9898/api/auth';
  //public baseURL = 'http://kitchenstory-env.eba-sptqgsam.us-east-2.elasticbeanstalk.com/api/auth';
  public baseURL = 'http://kstory-env.eba-beiz4uqk.us-east-2.elasticbeanstalk.com/items/api/auth';
  private token: string;
  private isAuthenticated = false;
  private customTimer: any;
  private authSatatusListener = new Subject<boolean>();


  public getToken(): string{
      return this.token;
  }

  public getAuthentication(): boolean{
    return this.isAuthenticated;
  }

  public getAuthStatusListener(){
    return this.authSatatusListener.asObservable();
  }

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://kstory-env.eba-beiz4uqk.us-east-2.elasticbeanstalk.com' , 'Access-Control-Allow-Credentials': 'true' }),
    //headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://kitchenstory-env.eba-sptqgsam.us-east-2.elasticbeanstalk.com' , 'Access-Control-Allow-Credentials': 'true' }),
    // headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:9898' , 'Access-Control-Allow-Credentials': 'true' }),
  };

  constructor(private http: HttpClient) { }

  createUser(user){
    const userForm = {
      username: user.get("email").value,
      password: user.get("password").value,
      address1: user.get("address1").value,
      address2: user.get("address2").value,
      city: user.get("city").value,
      state: user.get("state").value,
      pincode: user.get("pincode").value,
    };
    return this.http.request('POST', `${this.baseURL}/registration`, {
      body: userForm,
    }).pipe(

    );
  }


  updatePassword(oldpassword, newpassword, user){
    return this.http.request('PUT', `${this.baseURL}/updatePassword?oldPassword=${oldpassword}&newPassword=${newpassword}`, {
      body: user,
    }).pipe(

    );
  }

  // login(username: string, password: string){
  //   return this.http.get(`${this.baseURL}/login?username=${username}&password=${password}`);
  // }

  login(user): any{
    const userForm = {
      username: user.controls['emailId'].value,
      password: user.controls["password"].value,
    };
    return this.http.request('POST', `${this.baseURL}/login`, {
      body: userForm,
    })
    .pipe(
      map((data: { key: string, expiresIn: any }) => {
        console.log(data);
        this.token = data["key"];
        if (this.token) {
          this.isAuthenticated = true;
          this.saveAuthData(this.token);
          return this.isAuthenticated;

        }else{
          this.isAuthenticated = false;
          return this.isAuthenticated;
        }
      })
    );
  }

  clearToken(){
    this.token = null;
    this.isAuthenticated = false;
    clearTimeout(this.customTimer);
    this.clearAuthData();
  }

  autoAuthUser(): void {
    const authInformation = this.getAuthData();
    if (!authInformation) {
      return;
    }
      this.token = authInformation.token;
      this.isAuthenticated = true;

  }

  private saveAuthData(token: string): void {
    localStorage.setItem('token', token);
  }
  private clearAuthData(): void {
    localStorage.removeItem('token');

  }

  private getAuthData(): any {
    const token = localStorage.getItem('token');
    if (!token) {
      return;
    }
    return {
      token,
    };
  }
}
