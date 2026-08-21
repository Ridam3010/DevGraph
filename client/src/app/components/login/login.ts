import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  username = '';
  password = '';

  private authService = inject(Auth);
  private router = inject(Router);

  onSubmit() {
    const credentials = { username: this.username, password: this.password };

    this.authService.login(credentials).subscribe({
      next: (token) => {
        console.log("Login Success, Token saved");

        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.log("Login failed", err);
        alert("Invalid username or password");
      }
    });
  }
}
