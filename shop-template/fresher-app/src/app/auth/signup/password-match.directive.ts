import { Input, Directive } from '@angular/core';
import { AbstractControl, ValidationErrors, Validator, NG_VALIDATORS, ValidatorFn, FormGroup } from '@angular/forms';

@Directive({
    selector: '[passwordsMatchValidator]',
    providers: [{ provide: NG_VALIDATORS, useExisting: PasswordsMatchValidator, multi: true}]
})
export class PasswordsMatchValidator implements Validator {
    
    validate(control: AbstractControl): ValidationErrors {
        return this.matchPasswords(control);
    }
    
    registerOnValidatorChange?(fn: () => void): void {
        throw new Error("Method not implemented.");
    }

    matchPasswords: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
        const password = control.get('password');
        const passwordRepeat = control.get('password-repeat');
      
        return password && passwordRepeat && 
               password.value === passwordRepeat.value 
               ? null 
               : { 'passwordsnotmatch': true };
      };
}