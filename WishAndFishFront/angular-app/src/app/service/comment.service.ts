import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';  

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

  addComment(comment) {
    console.log(comment)
    return this.apiService.post(this.config.comment_url + `/addCommentToClient`, comment)
    .pipe(map(() => {
      console.log('Adding comment success');
    }));
  }
}
