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
  
  problems: any[] = [];
  
  // 1. Create a variable to track if we are waiting for the server
  isLoading: boolean = true; 

  ngOnInit() {
    this.problemService.getProblems().subscribe({
      next: (data) => {
        this.problems = data; 
        this.isLoading = false; // 2. Turn off the loading spinner!
      },
      error: (err) => {
        console.error("Failed to fetch problems", err);
        this.isLoading = false; // Turn it off even if it fails, so it doesn't spin forever
      }
    });
  }
}
