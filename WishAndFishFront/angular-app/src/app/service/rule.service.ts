import { Injectable } from '@angular/core';
import { ApiService, ConfigService } from '.';
import {map} from 'rxjs/operators';   

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

    findRules(id) {
      return this.apiService.get(this.config.rule_url + `/getAllByCottage/${id}`, id)
      .pipe(map(rules => {
        return rules;
      }));   
    }

 

}
