import { Visit } from './visit.model';
import { Specialist } from './specialist.model';

export class Category {
    id: number;
    name: string;
    description: string;
    visits: Visit[];
    specialists: Specialist[];
    isActive: Boolean;
}
