import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {UserData} from "./domain/user-data";
import {Router} from "@angular/router";
import {Notification} from "./domain/notification";

@Injectable({
  providedIn: 'root'
})
export class NotificationViewerService {
  constructor(
    private router: Router,
  ) {
  }

  public setNotification(userDataObservable: Observable<UserData>, notification: Notification): void {
    userDataObservable.subscribe(
      () => {
        notification.type = 'success';
        notification.message = 'Saved';
        setTimeout(() => {
          this.router.navigate(['../']);
        }, 1000);
      },
      () => {
        notification.type = 'warning';
        notification.message = 'Error saving';
      }
    );
  }
}
