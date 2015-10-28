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
         * The entry appointment.
         */
        byte ENTRY = 1;

        /**
         *
         */
        byte FINISHED = 2;

        /**
         *
         */
        byte WATTING = 3;
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



    public interface ITreatmentStatus {
        /**
         * ON_TREATING
         */
        byte ON_TREATING = 1;

        /**
         *
         */
        byte FINISHED = 2;

        /**
         *
         */
        byte HISTORY = 3;
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

    public interface IMedicalRecordDataType {
        /**
         *
         */
        byte NURSE_INPUT = 1;

        /*
         *
         */
        byte UN_CALCULATED = 2;

        /**
         *
         */
        byte CALCULATED = 3;

    }

    public interface IRoleType {
        /**
         *
         */
        int DOCTOR = 1;

        /**
         *
         */
        int PATIENT = 2;

        /**
         *
         */
        int NURSE = 3;

        /**
         *
         */
        int DOCTOR_MANAGER = 4;

        /**
         *
         */
        int STAFF = 5;

        /**
         *
         */
        int ADMIN = 6;

    }
}
