import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserContextService {

  constructor(private http: HttpClient) {}

  getUserInfo(userId: string) {
    const url = environment.apiBaseUrl + `/api/users/${userId}`;
    return this.http.get<any>(url);
  }

  banUser(userId: string, reason: string){
    const url = environment.apiBaseUrl + '/moderation/ban/' + userId;
    return this.http.post<any>(url, {reason});
  }

  timeoutUser(userId: string, duration: string, reason: string){
    const url = environment.apiBaseUrl + '/moderation/timeout/' + userId;
    return this.http.post<any>(url, {duration, reason});
  }
}