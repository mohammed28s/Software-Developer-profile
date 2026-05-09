import { Routes } from '@angular/router';
import { HomeComponent } from './components/public/home.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'admin', loadComponent: () => import('./components/admin/admin-dashboard.component').then(m => m.AdminDashboardComponent) }
];
