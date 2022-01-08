import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url =  'http://localhost:8080/api';
  private _auth_url = 'http://localhost:8080/api/auth';
  private _user_url = this._api_url + '/users';
  private _cottage_url = this._api_url + '/cottages';
  private _cottage_owner_url = this._api_url + '/cottageOwner';
  private _boat_url = this._api_url + '/boats';
  private _instructor_url = this._api_url + '/instructors';

  get user_url(): string {
    return this._user_url;
  }

  get cottage_url(): string {
    return this._cottage_url;
  }

  get instructor_url(): string {
    return this._instructor_url;
  }

  get boat_url(): string {
    return this._boat_url;
  }

  get cottage_owner_url(): string {
    return this._cottage_owner_url;
  }

  private _login_url = this._auth_url + '/login';

  get login_url(): string {
    return this._login_url;
  }

  private _whoami_url = this._api_url + '/whoami';

  get whoami_url(): string {
    return this._whoami_url;
  }

  private _users_url = this._user_url;

  get users_url(): string {
    return this._users_url;
  }

  private _signup_url = this._auth_url + '/signup';

  get signup_url(): string {
    return this._signup_url;
  }

}
