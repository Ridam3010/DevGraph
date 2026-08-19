import { Routes } from '@angular/router';
import { Login } from './components/login/login';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: '', redirectTo: '/login', pathMatch: 'full' } // If they visit the root URL, send them to login
];
