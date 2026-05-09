import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project, Skill, Experience, AssetResponse } from '../models/portfolio.models';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Public Methods
  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/projects`);
  }

  getSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(`${this.apiUrl}/skills`);
  }

  getExperience(): Observable<Experience[]> {
    return this.http.get<Experience[]>(`${this.apiUrl}/experience`);
  }

  getCvMetadata(): Observable<AssetResponse> {
    return this.http.get<AssetResponse>(`${this.apiUrl}/assets/cv`);
  }

  getProfileImageMetadata(): Observable<AssetResponse> {
    return this.http.get<AssetResponse>(`${this.apiUrl}/assets/profile-image`);
  }

  // Admin Methods (Require Auth)
  private getAuthHeaders() {
    // In a real app, this would come from an AuthService
    const credentials = btoa('admin:admin123');
    return new HttpHeaders({
      'Authorization': `Basic ${credentials}`
    });
  }

  addProject(project: Project): Observable<Project> {
    return this.http.post<Project>(`${this.apiUrl}/admin/projects`, project, { headers: this.getAuthHeaders() });
  }

  updateProject(id: number, project: Project): Observable<Project> {
    return this.http.put<Project>(`${this.apiUrl}/admin/projects/${id}`, project, { headers: this.getAuthHeaders() });
  }

  deleteProject(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/projects/${id}`, { headers: this.getAuthHeaders() });
  }

  addSkill(skill: Skill): Observable<Skill> {
    return this.http.post<Skill>(`${this.apiUrl}/admin/skills`, skill, { headers: this.getAuthHeaders() });
  }

  updateSkill(id: number, skill: Skill): Observable<Skill> {
    return this.http.put<Skill>(`${this.apiUrl}/admin/skills/${id}`, skill, { headers: this.getAuthHeaders() });
  }

  deleteSkill(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/skills/${id}`, { headers: this.getAuthHeaders() });
  }

  addExperience(experience: Experience): Observable<Experience> {
    return this.http.post<Experience>(`${this.apiUrl}/admin/experience`, experience, { headers: this.getAuthHeaders() });
  }

  updateExperience(id: number, experience: Experience): Observable<Experience> {
    return this.http.put<Experience>(`${this.apiUrl}/admin/experience/${id}`, experience, { headers: this.getAuthHeaders() });
  }

  deleteExperience(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/experience/${id}`, { headers: this.getAuthHeaders() });
  }

  uploadCv(file: File): Observable<AssetResponse> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<AssetResponse>(`${this.apiUrl}/admin/upload-cv`, formData, { headers: this.getAuthHeaders() });
  }

  uploadProfileImage(file: File): Observable<AssetResponse> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.put<AssetResponse>(`${this.apiUrl}/admin/profile-image`, formData, { headers: this.getAuthHeaders() });
  }
}
