package com.test.service;

import com.test.model.Option;
import com.test.model.Question;
import com.test.model.QuestionFiles;
import com.test.model.Test;
import com.test.repository.QuestionFilesRepository;
import com.test.repository.SubjectRepository;
import com.test.repository.TestRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

@Service
public class QuestionService {
    private final Hashids hashids;
    private final TestRepository testRepository;
    private final QuestionFilesRepository filesRepository;

    public QuestionService(TestRepository testRepository, QuestionFilesRepository filesRepository) {
        this.filesRepository = filesRepository;
        this.hashids = new Hashids(getClass().getName(), 7);
        this.testRepository = testRepository;
    }

    public Test saveQuestions(MultipartFile file, Integer testId) throws Exception {
        Test test = testRepository.getOne(testId);
        if (file.isEmpty() || file == null || test == null) {
            throw new Exception("File not found");
        } else {
            String extension = getExtension(file.getOriginalFilename());
            if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
                String filePath = "D:/question-files/" + hashids.encode(testId) + "." + extension;
                File questionFile = new File(filePath);
                file.transferTo(questionFile);
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(questionFile));
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
                QuestionFiles questionFiles = new QuestionFiles();
                questionFiles.setFilePath(filePath);
                questionFiles.setExtension(extension);
                questionFiles.setTest(test);
                filesRepository.save(questionFiles);
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
