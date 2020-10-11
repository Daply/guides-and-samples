import { HttpHeaders } from "@angular/common/http";

export class Header {
    baseUrl: string = "http://localhost:4200/api";
    headersValue: any;
    options: {};

    init() {
        this.headersValue = new HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': this.baseUrl});
        this.options = { headers: this.headersValue };
    }

    addHeader(name: string, value: any) {
        this.init();
        this.headersValue = this.headersValue.set(name, value);
        this.options = { headers: this.headersValue };
    }
    
    hasToken() {
        return sessionStorage.getItem("X-AUTH-TOKEN") != null;
    }

    addAuthTokenHeader() {
        this.addHeader("X-AUTH-TOKEN", sessionStorage.getItem("X-AUTH-TOKEN"));
    }

    cleanToken() {
        sessionStorage.setItem("X-AUTH-TOKEN", "");
    }

}
