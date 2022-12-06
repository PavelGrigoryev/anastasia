package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.NewPollAnswer;
import by.grigoryev.anastasia.model.NewPollQuestion;
import by.grigoryev.anastasia.model.NewPollTitle;
import by.grigoryev.anastasia.repository.NewPollAnswerRepository;
import by.grigoryev.anastasia.repository.NewPollQuestionRepository;
import by.grigoryev.anastasia.repository.NewPollTitleRepository;
import by.grigoryev.anastasia.service.NewPollExcelService;
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
public class NewPollExcelServiceImpl implements NewPollExcelService {

    private final NewPollTitleRepository newPollTitleRepository;

    private final NewPollQuestionRepository newPollQuestionRepository;

    private final NewPollAnswerRepository newPollAnswerRepository;

    @Override
    public void createSheet(Long telegramId) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            NewPollTitle newPollTitle = newPollTitleRepository.findFirstByTelegramIdOrderByTimeOfCreationDesc(telegramId);

            addSheet(workbook, newPollTitle);

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + newPollTitle.getTitle() + ".xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            log.info("NewPollExcelServiceImpl createSheet " + workbook);
            outputStream.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void addSheet(XSSFWorkbook workbook, NewPollTitle newPollTitle) {
        Sheet sheet = workbook.createSheet(newPollTitle.getTitle());
        sheet.setDefaultColumnWidth(5);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 3000);

        CellStyle columnStyle = workbook.createCellStyle();
        columnStyle.setBorderLeft(BorderStyle.THICK);

        CellStyle answerRowStyle = workbook.createCellStyle();
        answerRowStyle.setRotation((short) 90);
        answerRowStyle.setBorderLeft(BorderStyle.THICK);

        sheet.setDefaultColumnStyle(2, columnStyle);

        Row questionRow = sheet.createRow(0);

        Row answerRow = sheet.createRow(1);
        answerRow.setHeight((short) 1200);

        Cell answerCell = answerRow.createCell(0);
        answerCell.setCellValue("Имя");

        answerCell = answerRow.createCell(1);
        answerCell.setCellValue("Телеграм Id");

        int i = 0;
        int j = 2;

        for (NewPollQuestion newPollQuestion : newPollQuestionRepository.findAll()) {
            List<NewPollAnswer> answers = newPollAnswerRepository.findAllByNewPollQuestionId(newPollQuestion.getId());
            sheet.setDefaultColumnStyle(answers.size() + 2 + i, columnStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, i + 2, i + answers.size() + 1));

            Cell questionCell = questionRow.createCell(i + 2);
            questionCell.setCellValue(newPollQuestion.getQuestion());

            for (NewPollAnswer newPollAnswer : answers) {
                answerCell = answerRow.createCell(j);
                answerCell.setCellValue(newPollAnswer.getAnswer());
                answerCell.setCellStyle(answerRowStyle);
                j++;
            }

            i = answers.size() + i;
        }

        sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, newPollAnswerRepository.findAll().size() + 1));

    }

}
