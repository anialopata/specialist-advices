import { Specialist } from './specialist.model';
import { Patient } from './patient.model';
import { Category } from './category.model';

export class Visit {
    id: number;
    note: string;
    date: Date;
    category: Category;
    specialist: Specialist;
    patient: Patient;
}
