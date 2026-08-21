import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private http = inject(HttpClient);
  login(credentials: any) {
    return this.http.post('http://localhost:8080/api/v1/auth/login', credentials, { responseType: 'text' }).pipe(tap(token => {
      localStorage.setItem('jwt_token', token);
    })
    );
  }
  getToken(){
    return localStorage.getItem('jwt_token');
  }
  isLoggedIn(){
    return !!this.getToken();
  }
}
