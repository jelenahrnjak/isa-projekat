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

    findRulesBoat(id) {
      return this.apiService.get(this.config.rule_url + `/getAllByBoat/${id}`, id)
      .pipe(map(rules => {
        return rules;
      }));   
    }

    deleteRule(id){
      return this.apiService.delete(this.config.rule_url + `/deleteRule/${id}`, id)
    .pipe(map(() => {
      console.log('Deleting rule success');
    }));   
  }

  
   addRule(ruleDto){
    return this.apiService.post(this.config.rule_url + `/addRule`, ruleDto)
    .pipe(map(path => {
      console.log('Adding rule success: ' + path);
    }));;
  }

  addRuleBoat(ruleDto){
    return this.apiService.post(this.config.rule_url + `/addRuleBoat`, ruleDto)
    .pipe(map(path => {
      console.log('Adding rule success: ' + path);
    }));;
  }

}
