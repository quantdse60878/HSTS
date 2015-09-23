/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence;

/**
 * The Db constanst.
 */
public interface IDbConsts {

    public interface IUserGender {
        /**
         * Male.
         */
        byte MALE = 0;

        /**
         * Female.
         */
        byte FEMALE = 1;
    }

    public interface IUserStatus {
        /**
         * Active.
         */
        byte ACTIVE = 0;

        /**
         * Inactive.
         */
        byte INACTIVE = 1;

        /**
         * BLOCKED.
         */
        byte BLOCKED = 2;
    }

    public interface IAppointmentStatus {
        /**
         * Entry.
         */
        byte ENTRY = 0;

        /**
         * On going.
         */
        byte ON_GOING = 1;

        /**
         * Done.
         */
        byte DONE = 2;

        /**
         * Cancel.
         */
        byte CANCEL = 3;
    }

    public interface IMedicalRecordStatus {
        /**
         * Entry.
         */
        byte ENTRY = 0;

        /**
         * On treating.
         */
        byte ON_TREATING = 1;

        /**
         * Done.
         */
        byte FINISHED = 2;

        /**
         * No illness.
         */
        byte NO_ILLNESS = 3;
    }

    public interface IPhaseFoodTime {
        /**
         * Breakfast.
         */
        byte BREAKFAST = 0;

        /**
         * Lunch.
         */
        byte LUNCH = 1;

        /**
         * Dinner.
         */
        byte DINNER = 2;
    }

    public interface IMedicineType {
        /**
         * TABLET.
         */
        byte TABLET = 0;

        /**
         * BOTTLE.
         */
        byte BOTTLE = 1;
    }

    public interface ITreatmentStatus {
        /**
         * APPROVED.
         */
        byte APPROVED = 0;

        /**
         * On going.
         */
        byte ON_GOING = 1;

        /**
         *
         */
        byte FINISHED = 2;

        /**
         *
         */
        byte CANCEL = 3;

    }

    public interface IFoodTreatmentTime {
        /**
         * Breakfast.
         */
        byte BREAKFAST = 0;

        /**
         * Lunch.
         */
        byte LUNCH = 1;

        /**
         * Dinner.
         */
        byte DINNER = 2;
    }
}
