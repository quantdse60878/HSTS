/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 12/18/2015.
 */
$("#appointmentSelect").select2({
    placeholder: "Choose an appointment",
    theme: "classic",
    width: "400px",
    ajax: {
        url: "/appointmentsByMR",
        dataType: 'json',
        delay: 250,
        data:{
            medicalRecordId: $("#medicalRecordId").val()
        },
        processResults: function (data, params) {
            var names = data.map(function (obj) {
                return {
                    id: obj.id,
                    text: obj.meetingDate
                }
            });
            return {results: names,
                pagination: {
                    more: false
                }
            };
        },
        cache: false
    },
    escapeMarkup: function (markup) {
        return markup; // let our custom formatter work
    }
});