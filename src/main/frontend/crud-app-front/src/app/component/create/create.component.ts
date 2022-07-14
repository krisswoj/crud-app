import {Component, OnInit} from '@angular/core';
import {RequestService} from '../../service/request.service';
import {UserData} from "../../domain/user-data";
import {NotificationViewerService} from "../../notification-viewer.service";
import {Notification} from "../../domain/notification";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  userData!: UserData;
  notification!: Notification

  constructor(
    private requestService: RequestService,
    private notificationViewerService: NotificationViewerService,
  ) {
  }

  ngOnInit(): void {
    this.userData = new UserData();
    this.notification = new Notification();
  }

  save(userData: UserData) {
    let userDataObservable = this.requestService.create(userData);

    this.notificationViewerService.setNotification(userDataObservable, this.notification)
  }
}
