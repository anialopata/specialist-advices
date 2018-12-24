import { SimpleVisit } from './simple-visit';
import { Visit } from './visit.model';

export class Week {
    MONDAY: Array<Visit>;
    TUESDAY: Array<Visit>;
    WEDNESDAY: Array<Visit>;
    THURSDAY: Array<Visit>;
    FRIDAY: Array<Visit>;
    SATURDAY: Array<Visit>;
    SUNDAY: Array<Visit>;

    constructor() {
        this.MONDAY = [];
        this.TUESDAY = [];
        this.WEDNESDAY = [];
        this.THURSDAY = [];
        this.FRIDAY = [];
        this.SATURDAY = [];
        this.SUNDAY = [];

    }
}
