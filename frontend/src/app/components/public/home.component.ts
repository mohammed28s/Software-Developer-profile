import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PortfolioService } from '../../services/portfolio.service';
import { Project, Skill, Experience } from '../../models/portfolio.models';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  private portfolioService = inject(PortfolioService);

  projects: Project[] = [];
  skills: Skill[] = [];
  experience: Experience[] = [];
  cvUrl: string = '';
  profileImageUrl: string = '';

  ngOnInit(): void {
    this.portfolioService.getProjects().subscribe(data => this.projects = data);
    this.portfolioService.getSkills().subscribe(data => this.skills = data);
    this.portfolioService.getExperience().subscribe(data => this.experience = data);
    
    this.portfolioService.getCvMetadata().subscribe({
      next: data => this.cvUrl = `http://localhost:8080${data.fileUrl}`,
      error: () => {} // Fallback if no CV
    });
    
    this.portfolioService.getProfileImageMetadata().subscribe({
      next: data => this.profileImageUrl = `http://localhost:8080${data.fileUrl}`,
      error: () => {} // Fallback handled by onerror in HTML
    });
  }

  downloadCv(): void {
    if (this.cvUrl) {
      window.open(this.cvUrl, '_blank');
    } else {
      alert('CV not available yet.');
    }
  }

  getCategorizedSkills() {
    const categories = Array.from(new Set(this.skills.map(s => s.category)));
    return categories.map(cat => ({
      name: cat,
      skills: this.skills.filter(s => s.category === cat)
    }));
  }
}
