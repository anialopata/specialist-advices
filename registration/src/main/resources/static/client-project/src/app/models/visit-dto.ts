import { Category } from './category.model';

import { Specialist } from './specialist.model';

import { Patient } from './patient.model';

export class VisitDto {
    id: number;
    note: string;
    date: String;
    category: Category;
    specialist: Specialist;
    patient: Patient;
}
