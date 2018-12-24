import { Person } from './person.model';
import { Visit } from './visit.model';

export class Patient extends Person {
    visits: Visit[];
    isActive: Boolean;
}
