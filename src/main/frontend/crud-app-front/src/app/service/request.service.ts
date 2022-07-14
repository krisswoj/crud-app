import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserData} from "../domain/user-data";

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private apiURL = "http://localhost:8080";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<UserData[]> {
    return this.httpClient.get<UserData[]>(this.apiURL + '/all')
  }

  create(userData: UserData): Observable<UserData> {
    return this.httpClient.post<UserData>(this.apiURL + '/add', userData, this.httpOptions)
  }

  update(userData: UserData): Observable<UserData> {
    return this.httpClient.put<UserData>(this.apiURL + '/update', userData, this.httpOptions)
  }

  delete(id: number) {
    return this.httpClient.delete<UserData>(this.apiURL + '/delete/' + id, this.httpOptions)
  }
}
