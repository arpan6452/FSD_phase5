import { UserServiceService } from './common/user-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'kitchenstory';
  constructor(private userService: UserServiceService){}

  ngOnInit(): void {
    this.userService.autoAuthUser();
  }
}
