import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { 

  }

  getAllUsers(): Observable<any> {
    return this.http.get('//localhost:8080/getAllUsers');
  }

  postUser(name: String){
    this.http.post('//localhost:8080/postUser', {
      name: name
    }).subscribe(
    )
  }

  deleteUser(user: any){
    this.http.post('//localhost:8080/deleteUser', {
      id: user.id,
      name: user.name
    }).subscribe(
    )
  }
}
