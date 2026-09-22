import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ProblemService } from '../../services/problem'; // Import the service

@Component({
  selector: 'app-workspace',
  imports: [RouterLink],
  templateUrl: './workspace.html',
  styleUrl: './workspace.css'
})
export class Workspace implements OnInit {
  private route = inject(ActivatedRoute);
  private problemService = inject(ProblemService); // Inject the service

  problemId: string | null = null;
  problem: any = null; // Create a variable to hold the fetched problem

  ngOnInit() {
    this.problemId = this.route.snapshot.paramMap.get('id');
    
    if (this.problemId) {
      // Call the backend to fetch the specific problem details!
      this.problemService.getProblemById(this.problemId).subscribe({
        next: (data) => {
          this.problem = data;
        },
        error: (err) => {
          console.error("Failed to load problem", err);
        }
      });
    }
  }
}
