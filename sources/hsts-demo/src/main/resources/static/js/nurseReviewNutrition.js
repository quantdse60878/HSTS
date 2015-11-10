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
    document.getElementById("microphone").src = "/static/image/microphone_active.png";
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

        if((listFinalString.split(" ")[1] == "ăn") || (listFinalString.split(" ")[0] == "ăn")) {
            listFinalString = listFinalString.replace(" ăn ", "");
            listFinalString = listFinalString.replace("ăn ", "");
            listFinalString = replaceAll(listFinalString,' ăn ', ',');
            listFinalString = replaceAll(listFinalString,'ăn ', ',');
            listFinalString = replaceAll(listFinalString,' anh ', ',');
            listFinalString = replaceAll(listFinalString,'anh ', ',');
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
        if((listFinalString.split(" ")[1] == "anh") || (listFinalString.split(" ")[0] == "anh")) {
            listFinalString = listFinalString.replace(" anh ", "");
            listFinalString = listFinalString.replace("anh ", "");
            listFinalString = replaceAll(listFinalString,' ăn ', ',');
            listFinalString = replaceAll(listFinalString,' anh ', ',');
            listFinalString = replaceAll(listFinalString,'anh ', ',');
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
        if((listFinalString.split(" ")[1] == "uống") || (listFinalString.split(" ")[0] == "uống")) {
            listFinalString = listFinalString.replace(" uống ", "");
            listFinalString = replaceAll(listFinalString,' uống ', ',');
            listFinalString = replaceAll(listFinalString,'uống ', ',');
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

function startRecognizer() {
    recognizer();
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
            if(itemBreakfast.toLowerCase().indexOf(listFoodDatabase[j].foodName.toLowerCase()) > -1) {
                foodItemValue.foodName = listFoodDatabase[j].foodName;
                if(listFoodDatabase[j].foodUnit == null) continue;
                if(itemBreakfast.toLowerCase().indexOf(listFoodDatabase[j].foodUnit.toLowerCase()) > -1) {
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
                    numberOfQuantity = numberOfQuantity / 100;
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
    bf_result : 0,
    btm_result : 0,
    l_result : 0,
    bta_result : 0,
    dinner_result : 0,
    en_result : 0,
    lipid:0,
    starch:0,
    protein:0,
    fiber:0
};

function changeValueOnFood(target){
    var foodValueChange = target.value;
    if(target.id == "bf") {
        breakfast = foodValueChange;
        listFood = breakfast;
    }if(target.id == "break_time_morning") {
        breaktimeMorning = foodValueChange;
        listFood = breaktimeMorning;
    }if(target.id == "lunch") {
        lunch = foodValueChange;
        listFood = lunch;
    }if(target.id == "bta") {
        breaktimeAfternoon = foodValueChange;
        listFood = breaktimeAfternoon;
    }if(target.id == "dinner") {
        dinner = foodValueChange;
        listFood = dinner;
    }if(target.id == "en") {
        lateAtNight = foodValueChange;
        listFood = lateAtNight;
    }
}

function validation1(){
    var bf_result = document.getElementById("bf_result").innerHTML;
    var btm_result = document.getElementById("btm_result").innerHTML;
    var l_result = document.getElementById("l_result").innerHTML;
    var bta_result = document.getElementById("bta_result").innerHTML;
    var dinner_result = document.getElementById("dinner_result").innerHTML;
    var en_result = document.getElementById("en_result").innerHTML;
    var json = JSON.stringify(foodingredient);
    $.ajax({
        method: "GET",
        url: "sendNutrition",
        data: {"foodingredient" : json}
    }).done(function (data) {
        alert("KHUONGGNGNGNGNG");
    });
}

function createNutrition(){

    var listBreakfast = breakfast.split(",");
    var listBreaktimeMorning = breaktimeMorning.split(",");
    var listLunch = lunch.split(",");
    var listBreaktimeAfternoon = breaktimeAfternoon.split(",");
    var listDinner = dinner.split(",");
    var listLateAtNight = lateAtNight.split(",");
    var listBreakfastAnalytic = analyticMeal(listBreakfast);
    var listBraktimeMorningAnalytic = analyticMeal(listBreaktimeMorning);
    var listLunchAnalytic = analyticMeal(listLunch);
    var listBreaktimeAfternoonAnalytic = analyticMeal(listBreaktimeAfternoon);
    var listDinnerAnalytic = analyticMeal(listDinner);
    var listLateAtNightAnalytic = analyticMeal(listLateAtNight);
    var a = inputCaloriesEstimate(listBreakfastAnalytic);
    foodingredient.bf_result = a;
    var b = inputCaloriesEstimate(listBraktimeMorningAnalytic);
    foodingredient.btm_result = b;
    var c = inputCaloriesEstimate(listLunchAnalytic);
    foodingredient.l_result = c;
    var d = inputCaloriesEstimate(listBreaktimeAfternoonAnalytic);
    foodingredient.bta_result = d;
    var e = inputCaloriesEstimate(listDinnerAnalytic);
    foodingredient.dinner_result = e;
    var f = inputCaloriesEstimate(listLateAtNightAnalytic);
    foodingredient.en_result = f;

    var accept = document.getElementById("accept");
    accept.href = "createNutritionWithVoice?" +
                  "patientID=" + document.getElementById("patientId").value +
                  "&bf=" + foodingredient.bf_result +
                  "&btm=" + foodingredient.btm_result +
                  "&lunch=" + foodingredient.l_result +
                  "&bta=" + foodingredient.bta_result +
                  "&dinner=" + foodingredient.dinner_result +
                  "&en=" + foodingredient.en_result +
                  "&lipid=" + foodingredient.lipid +
                  "&starch=" + foodingredient.starch +
                  "&protein=" + foodingredient.protein +
                  "&fiber=" + foodingredient.fiber;
    simulate(accept,"click");
}

function inputCaloriesEstimate(list){
    var totalCaloriesEstimate = 0;
    if(list!= undefined){
        for(var i=0;i<list.length;i++){
            var item = list[i];
            totalCaloriesEstimate += item.caloriesEstimate;
            foodingredient.lipid += item.foodNutritionValue[0];
            foodingredient.starch += item.foodNutritionValue[1];
            foodingredient.protein += item.foodNutritionValue[2];
            foodingredient.fiber += item.foodNutritionValue[3];
        }
    }
    return totalCaloriesEstimate;
}

function changeColorTab(target){
    var panel1 = document.getElementById("tab1");
    var panel2 = document.getElementById("tab2");
    var panel3 = document.getElementById("tab3");
    var panel4 = document.getElementById("tab4");
    var panel5 = document.getElementById("tab5");
    var panel6 = document.getElementById("tab6");
    panel1.style.color = "";
    panel1.style.fontSize = "";
    panel1.style.fontWeight = "";
    panel2.style.color = "";
    panel2.style.fontSize = "";
    panel2.style.fontWeight = "";
    panel3.style.color = "";
    panel3.style.fontSize = "";
    panel3.style.fontWeight = "";
    panel4.style.color = "";
    panel4.style.fontSize = "";
    panel4.style.fontWeight = "";
    panel5.style.color = "";
    panel5.style.fontSize = "";
    panel5.style.fontWeight = "";
    panel6.style.color = "";
    panel6.style.fontSize = "";
    panel6.style.fontWeight = "";
    target.style.color = "green"
    target.style.fontSize = "large";
    target.style.fontWeight = "850";
}

function accepted(){

}