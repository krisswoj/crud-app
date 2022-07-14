import {Component, OnInit} from '@angular/core';
import {RequestService} from '../../service/request.service';
import {UserData} from "../../domain/user-data";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  userDatas: UserData[] = [];

  constructor(public postService: RequestService) {
  }

  ngOnInit(): void {
    this.postService.getAll().subscribe((result: UserData[]) => {
      this.userDatas = result
    }, error => {
      console.log(error)
    });
  }


  deletePost(id: number) {
    this.postService.delete(id).subscribe(res => {
      this.userDatas = this.userDatas.filter(item => item.id !== id);
      console.log('Post deleted successfully!');
    })
  }

}
