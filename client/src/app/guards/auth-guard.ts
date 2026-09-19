import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(Auth); // Grab our Auth service
  const router = inject(Router);    // Grab the Router to redirect people

  // Check if the user is logged in
  if (authService.isLoggedIn()) {
    return true; // Let them in!
  } else {
    // Kick them out to the login page!
    router.navigate(['/login']);
    return false;
  }
};
