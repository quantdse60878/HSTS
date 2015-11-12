/**
 * Created by Aking on 11/4/2015.
 */
// Validator
var validator = $("#mainForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        // compound rule
        breakfast: {
            required: true,
            remote: {
                // Explicit
                url: "/validateData",
                type: "POST",
            }
        },
        breakTimeMorning: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        lunch: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        breakTimeAfternoon: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        dinner: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        eatLateAtNight: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        starch: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        iron: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        protein: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        zinc: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        fat: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        vitaminB1: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        animalProtein: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        vitaminC: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        animalFat: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        vitaminB2: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        calcium: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        vitaminPP: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        sodium: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        fiber: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        }
    },
    messages: {
        fiber: {
            remote: "Invalid fiber out of range, please fix"
        },
        sodium: {
            remote: "Invalid sodium out of range, please fix"
        },
        vitaminPP: {
            remote: "Invalid vitaminPP out of range, please fix"
        },
        calcium: {
            remote: "Invalid calcium out of range, please fix"
        },
        vitaminB2: {
            remote: "Invalid vitaminB2 out of range, please fix"
        },
        animalFat: {
            remote: "Invalid animalFat out of range, please fix"
        },
        vitaminC: {
            remote: "Invalid vitaminC out of range, please fix"
        },
        vitaminB1: {
            remote: "Invalid vitaminB1 out of range, please fix"
        },
        fat: {
            remote: "Invalid fat out of range, please fix"
        },
        zinc: {
            remote: "Invalid zinc out of range, please fix"
        },
        protein: {
            remote: "Invalid protein out of range, please fix"
        },
        iron: {
            remote: "Invalid iron out of range, please fix"
        },
        starch: {
            remote: "Invalid starch out of range, please fix"
        },
        eatLateAtNight: {
            remote: "Invalid eat late at night out of range, please fix"
        },
        dinner: {
            remote: "Invalid dinner out of range, please fix"
        },
        breakTimeAfternoon: {
            remote: "Invalid break time afternoon out of range, please fix"
        },
        lunch: {
            remote: "Invalid lunch out of range, please fix"
        },
        breakTimeMorning: {
            remote: "Invalid break time morning out of range, please fix"
        },
        breakfast: {
            remote: "Invalid breakfast out of range, please fix"
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "breakfast"){
            error.appendTo($('#invalidbreakfast'));
        }  else if (element.attr("name") == "breakTimeMorning") {
            error.appendTo($('#invalidbreakTimeMorning'));
        } else if (element.attr("name") == "lunch") {
            error.appendTo($('#invalidlunch'));
        } else if (element.attr("name") == "breakTimeAfternoon") {
            error.appendTo($('#invalidbreakTimeAfternoon'));
        } else if (element.attr("name") == "dinner") {
            error.appendTo($('#invaliddinner'));
        } else if (element.attr("name") == "eatLateAtNight") {
            error.appendTo($('#invalideatLateAtNight'));
        } else if (element.attr("name") == "starch") {
            error.appendTo($('#invalidstarch'));
        } else if (element.attr("name") == "iron") {
            error.appendTo($('#invalidiron'));
        } else if (element.attr("name") == "protein") {
            error.appendTo($('#invalidprotein'));
        } else if (element.attr("name") == "zinc") {
            error.appendTo($('#invalidzinc'));
        } else if (element.attr("name") == "fat") {
            error.appendTo($('#invalidfat'));
        } else if (element.attr("name") == "vitaminB1") {
            error.appendTo($('#invalidvitaminB1'));
        } else if (element.attr("name") == "animalProtein") {
            error.appendTo($('#invalidanimalProtein'));
        } else if (element.attr("name") == "vitaminC") {
            error.appendTo($('#invalidvitaminC'));
        } else if (element.attr("name") == "animalFat") {
            error.appendTo($('#invalidanimalFat'));
        } else if (element.attr("name") == "vitaminB2") {
            error.appendTo($('#invalidvitaminB2'));
        } else if (element.attr("name") == "calcium") {
            error.appendTo($('#invalidcalcium'));
        } else if (element.attr("name") == "vitaminPP") {
            error.appendTo($('#invalidvitaminPP'));
        } else if (element.attr("name") == "sodium") {
            error.appendTo($('#invalidsodium'));
        } else if (element.attr("name") == "fiber") {
            error.appendTo($('#invalidfiber'));
        }

        // Default
        else {
            error.appendTo( element.parent().next() );
        }
    },
    submitHandler: function(form) {
        form.submit();
    },
    invalidHandler: function(e, validator) {
        if (validator.errorList.length > 0) {
            console.log("Change to first tab has error");
            var targetTab = jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id');
            changeTab('#' + targetTab, '#li_' + targetTab);
            console.log("end change");
        }
    }
});

function changeTab (a, li) {
    console.log("a: " + a);
    console.log("li: " + li);
    $('.nav-tabs > li.active').removeClass('active');
    $(li).addClass('active');
    $('.tab-content > .tab-pane.active').removeClass('active');
    $(a).addClass('active');
};

function validateAndChangeTab(targetTab, targetLi) {
    var valid = true;
    var $fields = $('.tab-pane.active').find('input');
    console.log($fields);
    $fields.each (function() {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });

    $fields = $('.tab-pane.active').find('select');
    $fields.each (function() {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });
    console.log("Valid: " + valid);
    if (valid) {
        changeTab(targetTab, targetLi);
    }

};

function validateAndOpenModal(m) {
    var valid = true;
    var $fields = $('.tab-pane.active').find('input');
    console.log($fields);
    $fields.each (function() {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });

    $fields = $('.tab-pane.active').find('select');
    $fields.each (function() {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });
    console.log("Valid: " + valid);
    if (valid) {
        $(m).modal('show')
    }

};

(function(){
    document.getElementById("iron").value = "0.0";
    document.getElementById("zinc").value = "0.0";
    document.getElementById("vitaminB1").value = "0.0";
    document.getElementById("animalProtein").value = "0.0";
    document.getElementById("vitaminC").value = "0.0";
    document.getElementById("animalFat").value = "0.0";
    document.getElementById("vitaminB2").value = "0.0";
    document.getElementById("calcium").value = "0.0";
    document.getElementById("vitaminPP").value = "0.0";
    document.getElementById("sodium").value = "0.0";
    document.getElementById("starch").value = "0.0";
    document.getElementById("protein").value = "0.0";
    document.getElementById("fat").value = "0.0";
    document.getElementById("fiber").value = "0.0";
    if(voiceModel != undefined){
        document.getElementById("breakfast").value = voiceModel.breakfast;
        document.getElementById("breakTimeMorning").value = voiceModel.breakTimeMorning;
        document.getElementById("lunch").value = voiceModel.lunch;
        document.getElementById("breakTimeAfternoon").value = voiceModel.breakTimeAfternoon;
        document.getElementById("dinner").value = voiceModel.dinner;
        document.getElementById("eatLateAtNight").value = voiceModel.eatLateAtNight;
        document.getElementById("starch").value = voiceModel.starch;
        document.getElementById("protein").value = voiceModel.protein;
        document.getElementById("fat").value = voiceModel.fat;
        document.getElementById("fiber").value = voiceModel.fiber;
    }
})();