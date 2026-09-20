import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProblemService {
  private http = inject(HttpClient);

  // Fetches the array of problems from the API Gateway
  getProblems() {
    return this.http.get<any[]>('http://localhost:8080/api/v1/problems');
  }
}
