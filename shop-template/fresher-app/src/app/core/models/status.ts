
export enum StatusCode {
    OK = "OK", 
    ENTITY_DOESNT_EXIST = "ENTITY_DOESNT_EXIST", 
    WRONG_PASSWORD = "WRONG_PASSWORD", 
    ALREADY_EXISTS = "ALREADY_EXISTS", 
    OPERATION_FAILED = "OPERATION_FAILED"
}

export class Status {
    message: string;
    statusCode: StatusCode;
}