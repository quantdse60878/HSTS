package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.service.FormulaService;
import vn.edu.fpt.hsts.common.util.AnalyticDataTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/25/15.
 */
@Controller
public class StaffController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private FormulaService formulaService;

    @RequestMapping(value = "setupNewFormula", method = RequestMethod.POST)
    public ModelAndView setupNewFormula(@RequestParam("newDistanceFormula") final String newDistanceFormula,
                                        @RequestParam("newCaloriesFormula") final String newCaloriesFormula,
                                        @RequestParam("listNewVariable") final String listNewVariableString,
                                        @RequestParam("listNewValueOfVariable") final String listNewValueOfVariableString) {
        ModelAndView mav = new ModelAndView();

        String[] listVariable = listNewVariableString.split(",");
        String[] listValue = listNewValueOfVariableString.split(",");

        List<String> listPrevention1 = formulaService.getListFieldOfPreventionCheck();
        List<String> listPrevention = new ArrayList<String>();
        List<String> listMedicalRecordData1 = formulaService.getListFieldOfMedicalRecordData();
        List<String> listMedicalRecordData = new ArrayList<String>();
        for (String p : listPrevention1) {
            if(!p.equals("appointment")) {
                listPrevention.add(p.substring(0, 1).toUpperCase() + p.substring(1));
            }
        }
        for (String m : listMedicalRecordData1) {
            if(!m.equals("appointment")) {
                listMedicalRecordData.add(m.substring(0, 1).toUpperCase() + m.substring(1));
            }
        }

        AnalyticDataTask.FORMULA_CALCULATE_CALORIES = newCaloriesFormula;
        AnalyticDataTask.FORMULA_CALCULATE_DISTANCE = newDistanceFormula;
        AnalyticDataTask.variable = new ArrayList<String>();
        AnalyticDataTask.valueVariable = new ArrayList<String>();
        for(int i = 0; i < listVariable.length; i++) {
            AnalyticDataTask.variable.add(listVariable[i]);
            for(int j = 0; j < listPrevention.size(); j++) {
                if(listValue[i].equals(listPrevention.get(j))) {
                    AnalyticDataTask.valueVariable.add("1," + listValue[i]);
                    break;
                }
            }
            for(int j = 0; j < listMedicalRecordData.size(); j++) {
                if(listValue[i].equals(listMedicalRecordData.get(j))) {
                    AnalyticDataTask.valueVariable.add("2," + listValue[i]);
                    break;
                }
            }
        }
        mav.setViewName("staffFormula");
        List<Variable> listVariables = new ArrayList<Variable>();
        for(int i = 0; i < AnalyticDataTask.variable.size(); i++) {
            listVariables.add(new Variable(AnalyticDataTask.variable.get(i), AnalyticDataTask.valueVariable.get(i).split(",")[1]));
        }
        formulaService.saveNewFormula();
        mav.addObject("DISTANCEFORMULA", AnalyticDataTask.FORMULA_CALCULATE_DISTANCE);
        mav.addObject("CALORIESFORMULA", AnalyticDataTask.FORMULA_CALCULATE_CALORIES);
        mav.addObject("LISTVARIABLE", listVariables);
        mav.addObject("LISTPREVENTION", listPrevention);
        mav.addObject("LISTMEDICALRECORDDATA", listMedicalRecordData);

        return mav;
    }

    @RequestMapping(value = "openFormula", method = RequestMethod.POST)
    public ModelAndView openFormula() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("staffFormula");
        List<String> listPrevention1 = formulaService.getListFieldOfPreventionCheck();
        List<String> listPrevention = new ArrayList<String>();
        List<String> listMedicalRecordData1 = formulaService.getListFieldOfMedicalRecordData();
        List<String> listMedicalRecordData = new ArrayList<String>();
        for (String p : listPrevention1) {
            if(!p.equals("appointment")) {
                listPrevention.add(p.substring(0, 1).toUpperCase() + p.substring(1));
            }
        }
        for (String m : listMedicalRecordData1) {
            if(!m.equals("appointment")) {
                listMedicalRecordData.add(m.substring(0, 1).toUpperCase() + m.substring(1));
            }
        }
        List<Variable> listVariables = new ArrayList<Variable>();
        for(int i = 0; i < AnalyticDataTask.variable.size(); i++) {
            listVariables.add(new Variable(AnalyticDataTask.variable.get(i), AnalyticDataTask.valueVariable.get(i).split(",")[1]));
        }
        mav.addObject("DISTANCEFORMULA", AnalyticDataTask.FORMULA_CALCULATE_DISTANCE);
        mav.addObject("CALORIESFORMULA", AnalyticDataTask.FORMULA_CALCULATE_CALORIES);
        mav.addObject("LISTVARIABLE", listVariables);
        mav.addObject("LISTPREVENTION", listPrevention);
        mav.addObject("LISTMEDICALRECORDDATA", listMedicalRecordData);
        return mav;
    }

    public class Variable {
        private String key;
        private String value;

        private Variable(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }




}
