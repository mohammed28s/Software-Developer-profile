export interface Project {
    id?: number;
    title: string;
    description: string;
    techStack: string;
    githubUrl: string;
    demoUrl: string;
    imageUrl: string;
}

export interface Skill {
    id?: number;
    skillName: string;
    category: string;
    proficiencyLevel: number;
}

export interface Experience {
    id?: number;
    companyName: string;
    jobTitle: string;
    startDate: string;
    endDate: string;
    achievements: string;
    currentJob: boolean;
}

export interface AssetResponse {
    fileName: string;
    fileUrl: string;
    fileType: string;
}
