package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.configuration.TestAnswers;
import by.grigoryev.anastasia.configuration.TestQuestions;
import by.grigoryev.anastasia.model.TelegramAnswer;
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

    private final TestQuestions testQuestions;

    private final TestAnswers testAnswers;

    @Override
    public void createSheet() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = addSheet(workbook);

            addQuestions(sheet);

            addAnswers(workbook, sheet);

            addResults(sheet);

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "NewYearTestResults.xlsx";

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
        sheet.setColumnWidth(2, 950);
        sheet.setColumnWidth(7, 950);
        sheet.setColumnWidth(16, 950);
        sheet.setColumnWidth(21, 950);
        sheet.setColumnWidth(35, 950);

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

    private void addQuestions(Sheet sheet) {
        Row questionRow = sheet.createRow(0);

        Cell questionCell = questionRow.createCell(2);
        questionCell.setCellValue(testQuestions.getQuestion1());

        questionCell = questionRow.createCell(7);
        questionCell.setCellValue(testQuestions.getQuestion2());

        questionCell = questionRow.createCell(16);
        questionCell.setCellValue(testQuestions.getQuestion3());

        questionCell = questionRow.createCell(21);
        questionCell.setCellValue(testQuestions.getQuestion4());
    }

    private void addAnswers(XSSFWorkbook workbook, Sheet sheet) {
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
        answerCell.setCellValue(testAnswers.getYes());
        answerCell.setCellStyle(thickBorderLeftStyle);

        int i = 3;
        for (String answer : List.of(testAnswers.getProbablyYes(), testAnswers.getDontKnow(),
                testAnswers.getProbablyNO(), testAnswers.getNot())) {
            answerCell = answerRow.createCell(i);
            answerCell.setCellValue(answer);
            i++;
        }

        answerCell = answerRow.createCell(7);
        answerCell.setCellValue(testAnswers.getAnswer2n1());
        answerCell.setCellStyle(thickBorderLeftStyle);

        for (String answer : List.of(
                testAnswers.getAnswer2n2(), testAnswers.getAnswer2n3(), testAnswers.getAnswer2n4(),
                testAnswers.getAnswer2n5(), testAnswers.getAnswer2n6(), testAnswers.getAnswer2n7(),
                testAnswers.getAnswer2n8(), testAnswers.getAnswer2n9()
        )) {
            answerCell = answerRow.createCell(i + 1);
            answerCell.setCellValue(answer);
            i++;
        }

        answerCell = answerRow.createCell(16);
        answerCell.setCellValue(testAnswers.getYes());
        answerCell.setCellStyle(thickBorderLeftStyle);

        for (String answer : List.of(testAnswers.getProbablyYes(), testAnswers.getDontKnow(),
                testAnswers.getProbablyNO(), testAnswers.getNot())) {
            answerCell = answerRow.createCell(i + 2);
            answerCell.setCellValue(answer);
            i++;
        }

        answerCell = answerRow.createCell(21);
        answerCell.setCellValue(testAnswers.getAnswer4n1());
        answerCell.setCellStyle(thickBorderLeftStyle);

        for (String answer : List.of(
                testAnswers.getAnswer4n2(), testAnswers.getAnswer4n3(), testAnswers.getAnswer4n4(),
                testAnswers.getAnswer4n5(), testAnswers.getAnswer4n6(), testAnswers.getAnswer4n7(),
                testAnswers.getAnswer4n8(), testAnswers.getAnswer4n9(), testAnswers.getAnswer4n10(),
                testAnswers.getAnswer4n11(), testAnswers.getAnswer4n12(), testAnswers.getAnswer4n13(),
                testAnswers.getAnswer4n14()
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

            for (TelegramAnswer telegramAnswer : answerRepository.findAllByForeignKeyIdOrderById(telegramUser.getId())) {
                String action = telegramAnswer.getMessage();
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
                    default -> log.warn("Unexpected value: " + telegramAnswer.getMessage());
                }
            }
        }
    }

}
