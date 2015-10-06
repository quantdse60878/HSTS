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

    public interface IAccountGender {
        /**
         * Male.
         */
        byte MALE = 1;

        /**
         * Female.
         */
        byte FEMALE = 2;
    }

    public interface IAccountStatus {
        /**
         * Active.
         */
        byte IN_ACTIVE = 1;

        /**
         * Inactive.
         */
        byte ACTIVE = 2;

        /**
         * BLOCKED.
         */
        byte BLOCKED = 3;
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
        byte WAITING_FOR_EXAMINATION = 1;

        /**
         * No illness.
         */
        byte NO_ILLNESS = 2;

        /**
         * On treating.
         */
        byte ON_TREATING = 3;

        /**
         * Done.
         */
        byte FINISHED = 4;

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

    public interface INotifyType {
        /**
         *
         */
        byte PATIENT_DOCTOR = 1;

        /**
         *
         */
        byte DOCTOR_PATIENT_PRESCRIPTION = 2;

        /**
         *
         */
        byte DOCTOR_PATIENT_APPOINTMENT = 3;

        /**
         *
         */
        byte NURSE_DOCTOR = 4;
    }

    public interface INotifyStatus {
        /**
         *
         */
        byte UNCOMPLETED = 1;

        /**
         *
         */
        byte COMPLETED = 2;
    }


}
