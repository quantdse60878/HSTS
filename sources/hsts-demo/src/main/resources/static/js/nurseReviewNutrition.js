/**
 * Created by Man Huynh Khuong on 11/6/2015.
 */
var listNumberWord = [{name:'một ', value:'1 '}, {name:'hai ', value:'2 '}, {name:'ba ', value:'3 '}, {name:'bốn ', value:'4 '}, {name:'năm ', value:'5 '}, {name:'sáu ', value:'6 '}, {name:'bảy ', value:'7 '}, {name:'tám ', value:'8 '}, {name:'chín ', value:'9 '}, {name:'mười ', value:'10 '}];
var listFood = "";
//var listFoodDatabase = [{foodName:"cơm", foodUnit:"bát", foodNutritionName:"lipid,starch,protein,fiber", foodNutritionValue:"0.6,44.2,4.6,0.23", caloriesEstimate:200}];
var listFoodDatabase = listUnitOfFood;
var mealValue = "";
var breakfast = "";
var breaktimeMorning = "";
var lunch = "";
var breaktimeAfternoon = "";
var dinner = "";
var lateAtNight = "";
function foodItemAnalytic(foodName, foodUnit, foodNutritionName, foodNutritionValue, caloriesEstimate) {
    this.foodName = foodName;
    this.foodUnit = foodUnit;
    this.foodNutritionName = foodNutritionName;
    this.foodNutritionValue = foodNutritionValue;
    this.caloriesEstimate = caloriesEstimate;
}
var allFoodHadAnalytic = new Array();
var countFood = 0;



function escapeRegExp(string) {
    return string.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
}
function replaceAll(string, find, replace) {
    return string.replace(new RegExp(escapeRegExp(find), 'g'), replace);
}
function isInt(n){
    return Number(n) === n && n % 1 === 0;
}



function recognizer(){
    var recognition = new webkitSpeechRecognition();
    recognition.continuous = true;
    recognition.interimResults = true;
    recognition.onresult = function(event) {
        var transcript = "";
        var final_transcript = "";
        for (var i = event.resultIndex; i < event.results.length; ++i) {
            transcript += event.results[i][0].transcript;
            if (event.results[i].isFinal) {
                final_transcript += event.results[i][0].transcript;
            }
        }
        console.log(final_transcript);
        document.getElementById("result").value = "--" + transcript;
        document.getElementById("finalResult").value = final_transcript;
        var listFinalString11 = document.getElementById("finalResult").value.split(" ");
        var listFinalString = document.getElementById("finalResult").value;
//            var a = "ăn cơm ăn cá ăn bún thịt nướng";
//            var listFinalString11 = a.split(" ");
        var meal = document.getElementById("meal");
        var foodValue = document.getElementById("foodValue");
        for(var i = 0; i < listFinalString11.length; i++) {
            var item = listFinalString11[i];
            if(item == "buổi" || item == "bữa") {
                var nextItem = listFinalString11[i + 1];
                if(nextItem == "trưa" || nextItem == "sáng" || nextItem == "chiều" || nextItem == "tối" || nextItem == "khuya") {
                    meal.value = "bữa " + nextItem;
                    listFood = "";
                    foodValue.value = "";
                    mealValue = "";
                    mealValue = "bữa " + nextItem;
                }
            } else if(item == "giữa" || item == "nữa" || item == "nếu") {
                var nextItem = listFinalString11[i + 1];
                if(nextItem == "buổi" || nextItem == "bữa") {
                    var nextNextItem = listFinalString11[i + 2];
                    console.log(item + nextItem + nextNextItem);
                    meal.value = "giữa buổi " + nextNextItem;
                    listFood = "";
                    foodValue.value = "";
                    mealValue = "";
                    mealValue = "giữa buổi " + nextNextItem;

                }
                break;
            }
        }

        if(listFinalString.split(" ")[1] == "ăn") {
            listFinalString = listFinalString.replace(" ăn ", "");
            listFinalString = replaceAll(listFinalString,' ăn ', ',');
            listFinalString = replaceAll(listFinalString,' anh ', ',');
            var listFinal = listFinalString.split(",");
            for(var i = 0; i < listFinal.length; i++) {
                for(var j = 0; j < listNumberWord.length; j++) {
                    listFinal[i] = replaceAll(listFinal[i], listNumberWord[j].name, listNumberWord[j].value);
                }
                listFinal[i] = replaceAll(listFinal[i], ' gam', 'g');
                if(listFood == "") {
                    listFood = listFinal[i];
                } else {
                    listFood = listFood + "," + listFinal[i];
                }
            }
        }
        if(listFinalString.split(" ")[1] == "anh") {
            listFinalString = listFinalString.replace(" anh ", "");
            listFinalString = replaceAll(listFinalString,' ăn ', ',');
            listFinalString = replaceAll(listFinalString,' anh ', ',');
            var listFinal = listFinalString.split(",");
            for(var i = 0; i < listFinal.length; i++) {
                for(var j = 0; j < listNumberWord.length; j++) {
                    listFinal[i] = replaceAll(listFinal[i], listNumberWord[j].name, listNumberWord[j].value);
                }
                listFinal[i] = replaceAll(listFinal[i], ' gam', 'g');
                if(listFood == "") {
                    listFood = listFinal[i];
                } else {
                    listFood = listFood + "," + listFinal[i];
                }
            }
        }
        if(listFinalString.split(" ")[1] == "uống") {
            listFinalString = listFinalString.replace(" uống ", "");
            listFinalString = replaceAll(listFinalString,' uống ', ',');
            var listFinal = listFinalString.split(",");
            for(var i = 0; i < listFinal.length; i++) {
                for(var j = 0; j < listNumberWord.length; j++) {
                    listFinal[i] = replaceAll(listFinal[i], listNumberWord[j].name, listNumberWord[j].value);
                }
                listFinal[i] = replaceAll(listFinal[i], ' gam', 'g');
                if(listFood == "") {
                    listFood = listFinal[i];
                } else {
                    listFood = listFood + "," + listFinal[i];
                }
            }
        }
        foodValue.value = listFood;
        var panel1 = document.getElementById("tab1");
        var panel2 = document.getElementById("tab2");
        var panel3 = document.getElementById("tab3");
        var panel4 = document.getElementById("tab4");
        var panel5 = document.getElementById("tab5");
        var panel6 = document.getElementById("tab6");
        console.log("Meal Value: " + mealValue);
        if(mealValue == "bữa sáng") {
            simulate(panel1,"click");
            breakfast = foodValue.value;
            document.getElementById("bf").value = breakfast;
        } else if(mealValue == "giữa buổi sáng") {
            simulate(panel2,"click");
            breaktimeMorning = foodValue.value;
            document.getElementById("break_time_morning").value = breaktimeMorning;
        } else if(mealValue == "bữa trưa") {
            simulate(panel3,"click");
            lunch = foodValue.value;
            document.getElementById("lunch").value = lunch;
        } else if(mealValue == "giữa buổi chiều") {
            simulate(panel4,"click");
            breaktimeAfternoon = foodValue.value;
            document.getElementById("bta").value = breaktimeAfternoon;
        } else if(mealValue == "bữa tối") {
            simulate(panel5,"click");
            dinner = foodValue.value;
            document.getElementById("dinner").value = dinner;
        } else if(mealValue == "bữa khuya") {
            simulate(panel6,"click");
            lateAtNight = foodValue.value;
            document.getElementById("en").value = lateAtNight;
        }

    }
    recognition.lang = "vi-VN";
    recognition.textIn;
    recognition.start();
};

function analytic() {
    var listBreakfast = breakfast.split(",");
    var listBreaktimeMorning = breaktimeMorning.split(",");
    var listLunch = lunch.split(",");
    var listBreaktimeAfternoon = breaktimeAfternoon.split(",");
    var listDinner = dinner.split(",");
    var listLateAtNight = lateAtNight.split(",");
    analyticMeal(listBreakfast);
    analyticMeal(listBreaktimeMorning);
    analyticMeal(listLunch);
    analyticMeal(listBreaktimeAfternoon);
    analyticMeal(listDinner);
    analyticMeal(listLateAtNight);
}

function choosePanel(element){
    document.getElementById("li_tab_1").class = "";
    document.getElementById("li_tab_2").class = "";
    document.getElementById("li_tab_3").class = "";
    document.getElementById("li_tab_4").class = "";
    document.getElementById("li_tab_5").class = "";
    document.getElementById("li_tab_6").class = "";
    element.class = "active";
}

function analyticMeal(listMeal) {
    if(listMeal[0] == "") return;
    var returnValue = new Array();
    var countMeal = 0;
    console.log("--" + listMeal.length + "--");
    for(var i = 0; i < listMeal.length; i++) {
        var itemBreakfast = listMeal[i];
        var foodItemValue = new foodItemAnalytic();
        for(var j = 0; j < listFoodDatabase.length; j++) {
            if(itemBreakfast.indexOf(listFoodDatabase[j].foodName) > -1) {
                foodItemValue.foodName = listFoodDatabase[j].foodName;
                if(itemBreakfast.indexOf(listFoodDatabase[j].foodUnit) > -1) {
                    foodItemValue.foodUnit = listFoodDatabase[j].foodUnit;
                    var listNutritionNameDatabase = listFoodDatabase[j].foodNutritionName.split(",");
                    var listNutritionName = new Array();
                    for(var k = 0; k < listNutritionNameDatabase.length; k++) {
                        listNutritionName[k] = listNutritionNameDatabase[k];
                    }
                    foodItemValue.foodNutritionName = listNutritionName;
                    itemBreakfast = replaceAll(itemBreakfast, foodItemValue.foodName, "");
                    itemBreakfast = replaceAll(itemBreakfast, foodItemValue.foodUnit, "");
                    var numberOfQuantity = itemBreakfast.match(/\d/g);
                    numberOfQuantity = parseInt(numberOfQuantity.join(""));
                    foodItemValue.caloriesEstimate = numberOfQuantity * listFoodDatabase[j].caloriesEstimate;
                    var listNutritionValueDatabase = listFoodDatabase[j].foodNutritionValue.split(",");
                    var listNutritionValue = new Array();
                    for(var k = 0; k < listNutritionValueDatabase.length; k++) {
                        listNutritionValue[k] = parseFloat(listNutritionValueDatabase[k]) * numberOfQuantity;
                    }
                    foodItemValue.foodNutritionValue = listNutritionValue;
                }
            }
        }
        returnValue[countMeal] = foodItemValue;
        countMeal++;
        allFoodHadAnalytic[countFood] = foodItemValue;
        countFood++;
        console.log(foodItemValue);
    }
    return returnValue;
}




var foodingredient = {
    bf_result : "",
    btm_result : "",
    l_result : "",
    bta_result : "",
    dinner_result : "",
    en_result : "",
    bf_starch : "",
    bf_lipid : "",
    bf_protein : "",
    bf_fiber : "",
    btm_starch : "",
    btm_lipid : "",
    btm_protein : "",
    btm_fiber : "",
    l_starch : "",
    l_lipid : "",
    l_protein : "",
    l_fiber : "",
    bta_starch : "",
    bta_lipid : "",
    bta_protein : "",
    bta_fiber : "",
    d_starch : "",
    d_lipid : "",
    d_protein : "",
    d_fiber : "",
    en_starch : "",
    en_lipid : "",
    en_protein : "",
    en_fiber : ""
};

function changeValueOnFood(target){
    var foodValueChange = target.value;
    if(target.id == "bf") {
        breakfast = foodValueChange;
    }if(target.id == "break_time_morning") {
        breaktimeMorning = foodValueChange;
    }if(target.id == "lunch") {
        lunch = foodValueChange;
    }if(target.id == "bta") {
        breaktimeAfternoon = foodValueChange;
    }if(target.id == "dinner") {
        dinner = foodValueChange;
    }if(target.id == "en") {
        lateAtNight = foodValueChange;
    }
}

function validation1(){
    alert("OK");
    var bf_result = document.getElementById("bf_result").innerHTML;
    var btm_result = document.getElementById("btm_result").innerHTML;
    var l_result = document.getElementById("l_result").innerHTML;
    var bta_result = document.getElementById("bta_result").innerHTML;
    var dinner_result = document.getElementById("dinner_result").innerHTML;
    var en_result = document.getElementById("en_result").innerHTML;
    var bf_starch = document.getElementById("bf_starch").value;
    var bf_lipid = document.getElementById("bf_lipid").value;
    var bf_protein = document.getElementById("bf_protein").value;
    var bf_fiber = document.getElementById("bf_fiber").value;
    var btm_starch = document.getElementById("btm_starch").value;
    var btm_lipid = document.getElementById("btm_lipid").value;
    var btm_protein = document.getElementById("btm_protein").value;
    var btm_fiber = document.getElementById("btm_fiber").value;
    var l_starch = document.getElementById("l_starch").value;
    var l_lipid = document.getElementById("l_lipid").value;
    var l_protein = document.getElementById("l_protein").value;
    var l_fiber = document.getElementById("l_fiber").value;
    var bta_starch = document.getElementById("bta_starch").value;
    var bta_lipid = document.getElementById("bta_lipid").value;
    var bta_protein = document.getElementById("bta_protein").value;
    var bta_fiber = document.getElementById("bta_fiber").value;
    var dinner_starch = document.getElementById("dinner_starch").value;
    var dinner_lipid = document.getElementById("dinner_lipid").value;
    var dinner_protein = document.getElementById("dinner_protein").value;
    var dinner_fiber = document.getElementById("dinner_fiber").value;
    var en_starch = document.getElementById("en_starch").value;
    var en_lipid = document.getElementById("en_lipid").value;
    var en_protein = document.getElementById("en_protein").value;
    var en_fiber = document.getElementById("en_fiber").value;
    var json = JSON.stringify(foodingredient);
    $.ajax({
        method: "GET",
        url: "sendNutrition",
        data: {"foodingredient" : json}
    }).done(function (data) {
        alert("KHUONGGNGNGNGNG");
    });
}