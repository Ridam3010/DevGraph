import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Dashboard } from './components/dashboard/dashboard';
import { authGuard } from './guards/auth-guard'; // 1. Import the Bouncer

export const routes: Routes = [
  { path: 'login', component: Login },
  // 2. Put the Bouncer in front of the dashboard!
  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] }, 
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
