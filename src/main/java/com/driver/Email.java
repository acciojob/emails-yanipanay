package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
        MailRepository mailRepository= new MailRepository();
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if (oldPassword.compareTo(password)==0){
            if(validatePassword(newPassword)) password = newPassword;
        }
    }
    public boolean validatePassword(String passWord){
        if(passWord.length()<8) return false;

        boolean UC = false;
        boolean LC = false;
        boolean digit = false;
        boolean SC = false;

        for(int i =0;i<passWord.length();i++){
            int ch = (int) passWord.charAt(i);

            if(ch>=(int)'a' && ch<=(int)'z') LC = true;
            else if(ch>=(int)'A' && ch<=(int)'Z') UC = true;
            else if(ch>=(int)'0' && ch<=(int)'9') digit = true;
            else SC = true;
        }

        return (UC && LC && digit && SC);
    }
}
