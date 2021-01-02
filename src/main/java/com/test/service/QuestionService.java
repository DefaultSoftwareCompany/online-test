package com.test.service;

import com.test.model.Option;
import com.test.model.Question;
import com.test.model.Test;
import com.test.repository.TestRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class QuestionService {
    private final TestRepository testRepository;

    public QuestionService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test saveQuestions(MultipartFile file, Integer testId) throws Exception {
        Test test = testRepository.getOne(testId);
        if (file.isEmpty() || file == null || test == null) {
            throw new Exception("File not found");
        } else {
            String extension = getExtension(file.getOriginalFilename());
            if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);
                ArrayList<Question> questions = new ArrayList<>();
                for (Row row : sheet) {
                    Question question = new Question();
                    question.setQuestionText(row.getCell(0).toString());
                    ArrayList<Option> options = new ArrayList<>();
                    Option option = new Option();
                    option.setQuestion(question);
                    option.setIsTrue(true);
                    option.setOptionValue(row.getCell(1).toString());
                    options.add(option);
                    for (int i = 2; i < test.getNumberOfOptions() + 1; i++) {
                        option = new Option();
                        option.setQuestion(question);
                        option.setOptionValue(row.getCell(i).toString());
                        option.setIsTrue(false);
                        options.add(option);
                    }
                    question.setOptions(options);
                    question.setTest(test);
                    questions.add(question);
                }
                test.setQuestions(questions);
                return testRepository.save(test);
            } else throw new Exception("The file extension must be xls or xlsx");
        }
    }

    private static String getExtension(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}
