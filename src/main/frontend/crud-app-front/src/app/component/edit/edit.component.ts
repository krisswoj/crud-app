import {Component, OnInit} from '@angular/core';
import {RequestService} from '../../service/request.service';
import {ActivatedRoute} from '@angular/router';
import {UserData} from "../../domain/user-data";
import {NotificationViewerService} from "../../notification-viewer.service";
import {Notification} from "../../domain/notification";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  userData!: UserData;
  notification!: Notification

  constructor(
    private route: ActivatedRoute,
    private requestService: RequestService,
    private notificationViewerService: NotificationViewerService,
  ) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.userData = JSON.parse(params['user']) as UserData
    });

    this.notification = new Notification();
  }

  save(userData: UserData) {
    let userDataObservable = this.requestService.update(userData);

    this.notificationViewerService.setNotification(userDataObservable, this.notification)
  }
}
