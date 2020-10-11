import { Input, Directive } from '@angular/core';
import { AbstractControl, ValidationErrors, Validator, NG_VALIDATORS, ValidatorFn, FormGroup } from '@angular/forms';

@Directive({
    selector: '[passwordValidator]',
    providers: [{ provide: NG_VALIDATORS, useExisting: PasswordValidator, multi: true}]
})
export class PasswordValidator implements Validator {
    
    validate(control: AbstractControl): ValidationErrors {
        return this.passwordValid(control);
    }
    registerOnValidatorChange?(fn: () => void): void {
        throw new Error("Method not implemented.");
    }

    passwordValid: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
        const password = control.get('password').value;
        const passwordRegexp = new RegExp('[a-z]+');
        var passwordValid = password != null
                            ? passwordRegexp.test(password)
                            : null;
        return !passwordValid 
               ? { 'passwordinvalid': true } 
               : null;
    };
}