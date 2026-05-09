import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PortfolioService } from '../../services/portfolio.service';
import { Project, Skill, Experience } from '../../models/portfolio.models';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {
  private portfolioService = inject(PortfolioService);

  projects: Project[] = [];
  skills: Skill[] = [];
  experience: Experience[] = [];

  newProject: Project = this.resetProject();
  newSkill: Skill = this.resetSkill();
  newExperience: Experience = this.resetExperience();

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.portfolioService.getProjects().subscribe(data => this.projects = data);
    this.portfolioService.getSkills().subscribe(data => this.skills = data);
    this.portfolioService.getExperience().subscribe(data => this.experience = data);
  }

  // Projects
  saveProject(): void {
    if (this.newProject.id) {
      this.portfolioService.updateProject(this.newProject.id, this.newProject).subscribe(() => {
        this.loadData();
        this.newProject = this.resetProject();
      });
    } else {
      this.portfolioService.addProject(this.newProject).subscribe(() => {
        this.loadData();
        this.newProject = this.resetProject();
      });
    }
  }

  editProject(project: Project): void {
    this.newProject = { ...project };
  }

  deleteProject(id: number | undefined): void {
    if (id && confirm('Are you sure?')) {
      this.portfolioService.deleteProject(id).subscribe(() => this.loadData());
    }
  }

  // Skills
  saveSkill(): void {
    if (this.newSkill.id) {
      this.portfolioService.updateSkill(this.newSkill.id, this.newSkill).subscribe(() => {
        this.loadData();
        this.newSkill = this.resetSkill();
      });
    } else {
      this.portfolioService.addSkill(this.newSkill).subscribe(() => {
        this.loadData();
        this.newSkill = this.resetSkill();
      });
    }
  }

  editSkill(skill: Skill): void {
    this.newSkill = { ...skill };
  }

  deleteSkill(id: number | undefined): void {
    if (id && confirm('Are you sure?')) {
      this.portfolioService.deleteSkill(id).subscribe(() => this.loadData());
    }
  }

  // Experience
  saveExperience(): void {
    if (this.newExperience.id) {
      this.portfolioService.updateExperience(this.newExperience.id, this.newExperience).subscribe(() => {
        this.loadData();
        this.newExperience = this.resetExperience();
      });
    } else {
      this.portfolioService.addExperience(this.newExperience).subscribe(() => {
        this.loadData();
        this.newExperience = this.resetExperience();
      });
    }
  }

  editExperience(exp: Experience): void {
    this.newExperience = { ...exp };
  }

  deleteExperience(id: number | undefined): void {
    if (id && confirm('Are you sure?')) {
      this.portfolioService.deleteExperience(id).subscribe(() => this.loadData());
    }
  }

  // Assets
  onFileSelected(event: any, type: 'cv' | 'profile'): void {
    const file: File = event.target.files[0];
    if (file) {
      if (type === 'cv') {
        this.portfolioService.uploadCv(file).subscribe(() => alert('CV uploaded successfully'));
      } else {
        this.portfolioService.uploadProfileImage(file).subscribe(() => alert('Profile image uploaded successfully'));
      }
    }
  }

  private resetProject(): Project {
    return { title: '', description: '', techStack: '', githubUrl: '', demoUrl: '', imageUrl: '' };
  }

  private resetSkill(): Skill {
    return { skillName: '', category: '', proficiencyLevel: 0 };
  }

  private resetExperience(): Experience {
    return { companyName: '', jobTitle: '', startDate: '', endDate: '', achievements: '', currentJob: false };
  }
}
