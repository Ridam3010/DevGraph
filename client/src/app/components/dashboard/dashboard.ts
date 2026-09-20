import { Component, inject, OnInit } from '@angular/core';
import { ProblemService } from '../../services/problem';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  private problemService = inject(ProblemService);
  
  // This will hold the data from the backend
  problems: any[] = [];

  // This runs automatically when the dashboard loads!
  ngOnInit() {
    this.problemService.getProblems().subscribe({
      next: (data) => {
        this.problems = data; 
        console.log("Fetched problems:", data);
      },
      error: (err) => {
        console.error("Failed to fetch problems", err);
      }
    });
  }
}
