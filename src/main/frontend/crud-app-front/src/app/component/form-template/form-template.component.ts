import {Component, Input, Output, EventEmitter} from '@angular/core';
import {UserData} from "../../domain/user-data";
import {Router} from "@angular/router";
import {Notification} from "../../domain/notification";

@Component({
  selector: 'app-form-template',
  templateUrl: './form-template.component.html',
  styleUrls: ['./form-template.component.css']
})
export class FormTemplateComponent {
  @Input() userData!: UserData;
  @Input() notification!: Notification
  @Output() updatedUserData = new EventEmitter<UserData>();
  @Input() pageTitle!: String;

  constructor(
    private router: Router,
  ) {
  }

  updateUserData(): void {
    this.updatedUserData.emit(this.userData);
  }

  cancel() {
    this.router.navigate(['../']);
  }

}
