import { Visit } from './visit.model';
import { Category } from './category.model';
import { Person } from './person.model';

export class Specialist extends Person {
    degree: string;
    description: string;
    visits: Visit[];
    category: Category;
    isActive: Boolean;
}
