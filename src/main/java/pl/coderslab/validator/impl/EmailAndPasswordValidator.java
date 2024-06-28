package pl.coderslab.validator.impl;

import pl.coderslab.validator.EmailAndPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAndPasswordValidator implements ConstraintValidator<EmailAndPassword, Object> {
    private String firstField;
    private String secondField;

    public void initialize(EmailAndPassword constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField =  constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("test");
//        Object id = new BeanWrapperImpl(value).getPropertyValue(userId);
//        Object email = new BeanWrapperImpl(value).getPropertyValue(userEmail);
        return true;
    }


//    @Override
//    public boolean isValid(Object user, ConstraintValidatorContext constraintValidatorContext) {
//        new BeanWrapperImpl

//        if (user == null) {
//            return true;
//        }
//        Long id = user.getId();
//        String userEmail = user.getEmail();
//
//        User existingUser;
//        if (id == null) {
//            existingUser = userService.findByEmail(userEmail);
//            return existingUser == null;
//        }
//        existingUser = userService.findById(id);
//        String existingUserEmail = existingUser.getEmail();
//        if (existingUserEmail.equals(userEmail)) {
//            return true;
//        }
//        return userService.findByEmail(userEmail) == null;
//    }
//
//    public boolean isValid2(User user, ConstraintValidatorContext constraintValidatorContext) {
//        if (user == null) {
//            return true;
//        }
//        User exisitngUser = userService.findByEmail(user.getEmail());
//        return exisitngUser == null;
//    }

}
