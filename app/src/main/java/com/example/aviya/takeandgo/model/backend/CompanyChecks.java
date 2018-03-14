package com.example.aviya.takeandgo.model.backend;

/**
 * Created by aviya on 01/12/2017.
 */

public class CompanyChecks {
    public static class User{
        public static Boolean checkPassword(String password) throws Exception {
            if (password.length() < 8)
                throw new Exception("the password must be at least 8 characters long");
            return true;
        }
    }
    public static class Car{
        /*
        check the car number
        check car exist
        return all the cars related to a specific model
        return all the cars related to a specific branch
        */
        public static Boolean checkCarNumber(String number) throws Exception {
            if (number.length() > 9 || number.length()<6)
                throw new Exception("car number must be between 9 to 6 numbers");
            else {
                for (Character c:number.toCharArray()) {
                    try{Integer.parseInt(c.toString());}
                    catch (Exception e) {throw new Exception("car number must contain only numbers");}
                }
            }
            return true;
        }
    }
    public static class Branch{
        /*
         check for duplicates
         return all the branch for a specific city
         */
    }
    public static class CarModel {
        /*
        check for duplicates
        seats not greater tan 8
        doors not greater than 4
        code exactly 4 characters
         */
        public static Boolean checkSeatsNum(String seats) throws Exception {
            int s;
            try{s = Integer.parseInt(seats);}
            catch (Exception e){throw new Exception("please enter a number of seats");}
            if (s > 8 || s < 2)
                throw new Exception("number of seats must be between 2 to 8");
            return true;
         }
        public static Boolean checkDoorsNum(String doors) throws Exception {
            int s;
            try{s = Integer.parseInt(doors);}
            catch (Exception e){throw new Exception("please enter a number of doors");}
            if (s != 4 || s!=2)
                throw new Exception("number of seats must be 2 or 4");
            return true;
        }
    }

}
