package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.Answer;
import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.AnswerRepository;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl {

    private final AnswerRepository answerRepository;

    private final TelegramUserRepository telegramUserRepository;

    public void createSheet() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("NewYearTest");
            CellStyle columnStyle = workbook.createCellStyle();
            sheet.setDefaultColumnWidth(10);

            columnStyle.setBorderLeft(BorderStyle.THICK);
            sheet.setDefaultColumnStyle(2, columnStyle);
            sheet.setDefaultColumnStyle(7, columnStyle);
            sheet.setDefaultColumnStyle(16, columnStyle);

            sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, 15));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 6));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 15));

            Row questionRow = sheet.createRow(0);

            Cell questionCell = questionRow.createCell(2);
            questionCell.setCellValue("Как вы считаете, сотрудникам компаний нужны Новогодние корпоративы?");

            questionCell = questionRow.createCell(7);
            questionCell.setCellValue("Для чего сотрудникам компаний нужны Новогодние корпоративы?");

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

            answerCell = answerRow.createCell(3);
            answerCell.setCellValue("Скорее, да");

            answerCell = answerRow.createCell(4);
            answerCell.setCellValue("Не знаю");

            answerCell = answerRow.createCell(5);
            answerCell.setCellValue("Скорее, нет");

            answerCell = answerRow.createCell(6);
            answerCell.setCellValue("Нет");

            answerCell = answerRow.createCell(7);
            answerCell.setCellValue("Они сближают сотрудников");
            answerCell.setCellStyle(thickBorderLeftStyle);

            answerCell = answerRow.createCell(8);
            answerCell.setCellValue("Улучшают их коммуникацию в дальнейшем");

            answerCell = answerRow.createCell(9);
            answerCell.setCellValue("Повышают лояльность сотрудников к компании");

            answerCell = answerRow.createCell(10);
            answerCell.setCellValue("Позволяют чувствовать себя единой командой");

            answerCell = answerRow.createCell(11);
            answerCell.setCellValue("Позволяют пообщаться в неформальной обстановке");

            answerCell = answerRow.createCell(12);
            answerCell.setCellValue("Это награда от компании за хорошую работу");

            answerCell = answerRow.createCell(13);
            answerCell.setCellValue("Неформально подвести итоги года");

            answerCell = answerRow.createCell(14);
            answerCell.setCellValue("Чисто побухать и оттянуться по полной на халяву");

            answerCell = answerRow.createCell(15);
            answerCell.setCellValue("Сотрудникам не нужны Новогодние корпоративы");

            answerCell = answerRow.createCell(16);
            answerCell.setCellStyle(thickBorderLeftStyle);

            for (TelegramUser telegramUser : telegramUserRepository.findAll()) {
                Row informationRow = sheet.createRow(Math.toIntExact(telegramUser.getId() + 1));

                Cell informationCell = informationRow.createCell(0);
                informationCell.setCellValue(telegramUser.getFirstName());

                informationCell = informationRow.createCell(1);
                informationCell.setCellValue(telegramUser.getTelegramId());

                for (Answer answer : answerRepository.findAllByForeignKeyIdOrderById(telegramUser.getId())) {
                    switch (answer.getMessage()) {
                        case "1/1" -> {
                            informationCell = informationRow.createCell(2);
                            informationCell.setCellValue(1);
                        }
                        case "1/2" -> {
                            informationCell = informationRow.createCell(3);
                            informationCell.setCellValue(1);
                        }
                        case "1/3" -> {
                            informationCell = informationRow.createCell(4);
                            informationCell.setCellValue(1);
                        }
                        case "1/4" -> {
                            informationCell = informationRow.createCell(5);
                            informationCell.setCellValue(1);
                        }
                        case "1/5" -> {
                            informationCell = informationRow.createCell(6);
                            informationCell.setCellValue(1);
                        }
                        case "2/1" -> {
                            informationCell = informationRow.createCell(7);
                            informationCell.setCellValue(1);
                        }
                        case "2/2" -> {
                            informationCell = informationRow.createCell(8);
                            informationCell.setCellValue(1);
                        }
                        case "2/3" -> {
                            informationCell = informationRow.createCell(9);
                            informationCell.setCellValue(1);
                        }
                        case "2/4" -> {
                            informationCell = informationRow.createCell(10);
                            informationCell.setCellValue(1);
                        }
                        case "2/5" -> {
                            informationCell = informationRow.createCell(11);
                            informationCell.setCellValue(1);
                        }
                        case "2/6" -> {
                            informationCell = informationRow.createCell(12);
                            informationCell.setCellValue(1);
                        }
                        case "2/7" -> {
                            informationCell = informationRow.createCell(13);
                            informationCell.setCellValue(1);
                        }
                        case "2/8" -> {
                            informationCell = informationRow.createCell(14);
                            informationCell.setCellValue(1);
                        }
                        case "2/9" -> {
                            informationCell = informationRow.createCell(15);
                            informationCell.setCellValue(1);
                        }
                        default -> log.warn("Unexpected value: " + answer.getMessage());
                    }
                }

            }

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "newYearTest.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation, true);
            workbook.write(outputStream);
            log.info("ExcelServiceImpl createSheet " + workbook);
            outputStream.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
