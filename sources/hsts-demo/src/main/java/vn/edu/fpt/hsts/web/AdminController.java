package vn.edu.fpt.hsts.web;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.AccountModel;
import vn.edu.fpt.hsts.bizlogic.service.AccountService;
import vn.edu.fpt.hsts.bizlogic.service.AuthenService;
import vn.edu.fpt.hsts.bizlogic.service.MailService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Role;
import vn.edu.fpt.hsts.persistence.repo.RoleRepo;

import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Aking on 10/25/2015.
 */
@Controller
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;

    /**
     * The {@link AuthenService}.
     */
    @Autowired
    private AuthenService authenService;

    /**
     * The {@link RoleRepo}.
     */
    @Autowired
    private RoleRepo roleRepo;

    /**
     * Create main page admin.
     * @return
     */
    @RequestMapping(value = "adminAccs", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("adminlistuser");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "accountList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AccountModel> findAccounts() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            List<Account> list = accountService.findAccounts();
            if (null != list && !list.isEmpty()) {
                List<AccountModel> listModel = new ArrayList<AccountModel>();
                for (Account a: list) {
                    final AccountModel model = new AccountModel();
                    model.fromEntity(a);
                    listModel.add(model);
                }
                return listModel;
            }
            return Collections.emptyList();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public ModelAndView findAccount(@RequestParam("id") String id) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            Account account = accountService.findExactlyAccount(Integer.parseInt(id));
            if(null!=account){
                mav.addObject("account",account);
                mav.setViewName("adminaccount");
                return mav;
            } else {
                mav.setViewName("500");
                return mav;
            }
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "adminReg", method = RequestMethod.POST)
    public ModelAndView addAccounts(@RequestParam("excel") MultipartFile file){
        ModelAndView mav = new ModelAndView();
        try {
            LOGGER.info("filename[{}]", file.getBytes());
            final int index = file.getOriginalFilename().lastIndexOf(".");
            List<Account> list = new ArrayList<Account>();
            String ext = file.getOriginalFilename().substring(index + 1);
            FileInputStream fis = new FileInputStream(convert(file));
            Iterator<Row> rowIterator = null;
            if(ext.equals("xls")){
                HSSFWorkbook workbook = new HSSFWorkbook(fis);
                HSSFSheet sheet = workbook.getSheetAt(0);
                rowIterator = sheet.iterator();
            }
            if(ext.equals("xlsx")){
                XSSFWorkbook workbook = new XSSFWorkbook (fis);
                XSSFSheet sheet = workbook.getSheetAt(0);
                rowIterator = sheet.iterator();
            }
            int count = 1;
            boolean flag = false;
            while(rowIterator.hasNext()) {
                if(flag) break;
                Row row = rowIterator.next();
                if(count <3){
                    count++;
                    continue;
                }
                Account account = new Account();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()){
                        case 0: {
                            account.setEmail(cell.getStringCellValue());
                            break;
                        }
                        case 1: {
                            if(cell.getStringCellValue().equals("")) {
                                flag = true;
                                break;
                            }
                            String fullname = cell.getStringCellValue();
                            LOGGER.info("KhuongMHHH : " + fullname);
                            account.setFullName(fullname);
                            account.setUsername(accountService.buildUniqueUsername(fullname));
                            account.setPassword(authenService.randomPassword());
                            break;
                        }
                        case 2: {
                            account.setDateOfBirth(DateUtils.parseDate(cell.getStringCellValue(),DateUtils.DATE_PATTERN_3));
                            break;
                        }
                        case 3: {
                            final byte value = (byte) cell.getNumericCellValue();
                            account.setGender(value);
                            break;
                        }
                        case 4: {
                            final byte value = (byte) cell.getNumericCellValue();
                            Role role = roleRepo.findOne(( int) value);
                            account.setRole(role);
                            break;
                        }
                    }
                }
                if(!flag) list.add(account);
            }
            accountService.regAccounts(list);
            mav.setViewName("adminlistuser");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            LOGGER.info(IConsts.END_METHOD);
        }
        return mav;
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
