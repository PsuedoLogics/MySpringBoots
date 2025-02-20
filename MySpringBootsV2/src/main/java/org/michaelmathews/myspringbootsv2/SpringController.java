package org.michaelmathews.myspringbootsv2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class SpringController {

    private String userText;
    private int userRating;
    private String filePath = "reviewData.csv";

    @GetMapping("/")
    public String ShowForm(Model model)
    {
        model.addAttribute("userText", userText);
        model.addAttribute("userRating", userRating);
        return "index";
    }

    @PostMapping("/submit")
    public String SubmitForm(@RequestParam String userText, @RequestParam int userRating, Model model)
    {
        this.userText = userText;
        this.userRating = userRating;
        model.addAttribute("userText", userText);
        model.addAttribute("userRating", userRating);
        WriteToCSV();
        return "index";
    }

    public void WriteToCSV()
    {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if(fileExists)
                writer.write("," + userText + "," + userRating);
            if(!fileExists)
                writer.write(userText + "," + userRating);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }

    }



}
