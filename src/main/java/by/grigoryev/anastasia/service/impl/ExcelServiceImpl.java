package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.Answer;
import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.AnswerRepository;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final AnswerRepository answerRepository;

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public void createSheet() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = addSheet(workbook);

            addQuestions(sheet);

            addAnswers(workbook, sheet);

            addResults(sheet);

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "newYearTest.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            log.info("ExcelServiceImpl createSheet " + workbook);
            outputStream.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private static Sheet addSheet(XSSFWorkbook workbook) {
        Sheet sheet = workbook.createSheet("NewYearTest");
        sheet.setDefaultColumnWidth(3);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 3000);

        CellStyle columnStyle = workbook.createCellStyle();
        columnStyle.setBorderLeft(BorderStyle.THICK);

        sheet.setDefaultColumnStyle(2, columnStyle);
        sheet.setDefaultColumnStyle(7, columnStyle);
        sheet.setDefaultColumnStyle(16, columnStyle);
        sheet.setDefaultColumnStyle(21, columnStyle);
        sheet.setDefaultColumnStyle(35, columnStyle);

        sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, 34));

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 6));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 15));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 16, 20));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 21, 34));
        return sheet;
    }

    private static void addQuestions(Sheet sheet) {
        Row questionRow = sheet.createRow(0);

        Cell questionCell = questionRow.createCell(2);
        questionCell.setCellValue("Как вы считаете, сотрудникам компаний нужны Новогодние корпоративы?");

        questionCell = questionRow.createCell(7);
        questionCell.setCellValue("Для чего сотрудникам компаний нужны Новогодние корпоративы?");

        questionCell = questionRow.createCell(16);
        questionCell.setCellValue("Будет ли в этом году в вашей компании празднование Нового года?");

        questionCell = questionRow.createCell(21);
        questionCell.setCellValue("Что вам больше всего не нравится на новогодних корпоративах?");
    }

    private static void addAnswers(XSSFWorkbook workbook, Sheet sheet) {
        CellStyle answerRowStyle = workbook.createCellStyle();
        answerRowStyle.setRotation((short) 90);

        CellStyle defaultStyle = workbook.createCellStyle();

        CellStyle thickBorderLeftStyle = workbook.createCellStyle();
        thickBorderLeftStyle.setBorderLeft(BorderStyle.THICK);
        thickBorderLeftStyle.setRotation((short) 90);

        Row answerRow = sheet.createRow(1);
        answerRow.setHeight((short) 1200);
        answerRow.setRowStyle(answerRowStyle);

        Cell answerCell = answerRow.createCell(0);
        answerCell.setCellValue("Имя");
        answerCell.setCellStyle(defaultStyle);

        answerCell = answerRow.createCell(1);
        answerCell.setCellValue("Телеграм Id");
        answerCell.setCellStyle(defaultStyle);

        answerCell = answerRow.createCell(2);
        answerCell.setCellValue("Да");
        answerCell.setCellStyle(thickBorderLeftStyle);

        int i = 3;
        for (String answer : List.of("Скорее, да", "Не знаю", "Скорее, нет", "Нет")) {
            answerCell = answerRow.createCell(i);
            answerCell.setCellValue(answer);
            i++;
        }

        answerCell = answerRow.createCell(7);
        answerCell.setCellValue("Они сближают сотрудников");
        answerCell.setCellStyle(thickBorderLeftStyle);

        for (String answer : List.of(
                "Улучшают их коммуникацию в дальнейшем",
                "Повышают лояльность сотрудников к компании", "Позволяют чувствовать себя единой командой",
                "Позволяют пообщаться в неформальной обстановке", "Это награда от компании за хорошую работу",
                "Неформально подвести итоги года", "Чисто побухать и оттянуться по полной на халяву",
                "Сотрудникам не нужны Новогодние корпоративы"
        )) {
            answerCell = answerRow.createCell(i + 1);
            answerCell.setCellValue(answer);
            i++;
        }

        answerCell = answerRow.createCell(16);
        answerCell.setCellValue("Да");
        answerCell.setCellStyle(thickBorderLeftStyle);

        for (String answer : List.of("Скорее, да", "Не знаю", "Скорее, нет", "Нет")) {
            answerCell = answerRow.createCell(i + 2);
            answerCell.setCellValue(answer);
            i++;
        }

        answerCell = answerRow.createCell(21);
        answerCell.setCellValue("Мне всё не нравится на корпоративах");
        answerCell.setCellStyle(thickBorderLeftStyle);

        for (String answer : List.of(
                "Не нравится сдавать деньги на корпоративы", "Не нравится принимать участие в конкурсах",
                "Не люблю говорить тосты", "Не трезвые коллеги", "Невозможно как следует расслабиться",
                "Невозможность прийти со второй половинкой", "Беспокойство о том, как я выгляжу и что надеть",
                "Мне всегда скучно на таких праздниках", "Отсутствие возможности отказаться от участия в корпоративе",
                "Слишком много людей", "Нетрезвый начальник", "Недвусмысленные приставания коллег", "Другое"
        )) {
            answerCell = answerRow.createCell(i + 3);
            answerCell.setCellValue(answer);
            i++;
        }

        answerCell = answerRow.createCell(35);
        answerCell.setCellStyle(thickBorderLeftStyle);
    }

    private void addResults(Sheet sheet) {
        for (TelegramUser telegramUser : telegramUserRepository.findAll()) {
            Row resultRow = sheet.createRow(Math.toIntExact(telegramUser.getId() + 1));

            Cell resultCell = resultRow.createCell(0);
            resultCell.setCellValue(telegramUser.getFirstName());

            resultCell = resultRow.createCell(1);
            resultCell.setCellValue(telegramUser.getTelegramId());

            for (Answer answer : answerRepository.findAllByForeignKeyIdOrderById(telegramUser.getId())) {
                String action = answer.getMessage();
                switch (action) {
                    case "1/1", "1/2", "1/3", "1/4", "1/5" -> {
                        resultCell = resultRow.createCell(Integer.parseInt(action.substring(2)) + 1);
                        resultCell.setCellValue(1);
                    }
                    case "2/1", "2/2", "2/3", "2/4", "2/5", "2/6", "2/7", "2/8", "2/9" -> {
                        resultCell = resultRow.createCell(Integer.parseInt(action.substring(2)) + 6);
                        resultCell.setCellValue(1);
                    }
                    case "3/1", "3/2", "3/3", "3/4", "3/5" -> {
                        resultCell = resultRow.createCell(Integer.parseInt(action.substring(2)) + 15);
                        resultCell.setCellValue(1);
                    }
                    case "4/1", "4/2", "4/3", "4/4", "4/5", "4/6", "4/7", "4/8", "4/9",
                            "4/10", "4/11", "4/12", "4/13", "4/14" -> {
                        resultCell = resultRow.createCell(Integer.parseInt(action.substring(2)) + 20);
                        resultCell.setCellValue(1);
                    }
                    default -> log.warn("Unexpected value: " + answer.getMessage());
                }
            }
        }
    }

}
