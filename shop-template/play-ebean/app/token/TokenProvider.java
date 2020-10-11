package token;


public class TokenProvider {

    public String getToken(String username, String password) {
        return username + ":" + password + ":" + "valid";
    }

    // TODO token for a while - username:password:valid
    public String getUsernameFromToken(String token) {
        return token.split(":")[0];
    }

    public boolean checkIfTokenIsValid(String token) {
        return true;
    }

}
