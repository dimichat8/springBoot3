package com.example.springboot.pdf;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.Meal;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserPDFExporter {

    private List<Meal> mealList;
    private Customer customer;

    public UserPDFExporter(List<Meal> mealList) {
        this.mealList = mealList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        /*cell.setBackgroundColor(Color.BLUE);*/
        cell.setPadding(7);

        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        /*font.setColor(Color.WHITE);*/
        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Monday", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Tuesday", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Wednesday", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Thursday", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Friday", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Saturday", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sunday", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        String[] mealNames = { "Breakfast", "Desert", "Lunch", "Snack", "Dinner" };

        // Create a map to store the meal values for each day of the week and meal type
        Map<String, List<String>> mealMap = new HashMap<>();

        // Initialize the map with empty lists for each combination of day of the week and meal type
        for (String day : daysOfWeek) {
            for (String mealName : mealNames) {
                mealMap.put(day + "-" + mealName, new ArrayList<>());
            }
        }

        // Populate the map with the meal values
        for (Meal meal : mealList) {
            String dayOfWeek = meal.getDayOfWeek().toString();
            String mealName = meal.getMealName();
            List<String> mealValues = getMealValue(meal, mealName);

            // Update the meal values for the specific day and meal type in the map
            String key = dayOfWeek + "-" + mealName;
            mealMap.get(key).addAll(mealValues);
        }

        // Add the map values to the PDF table
        for (String mealName : mealNames) {
            table.addCell(mealName);
            for (String day : daysOfWeek) {
                String key = day + "-" + mealName;
                List<String> mealValues = mealMap.get(key);
                String mealValue = getValidCellValue(String.join(", ", mealValues));
                table.addCell(mealValue);
            }
        }

        /*for (Map.Entry<String, List<String>> entry : mealMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/
    }

    private List<String> getMealValue(Meal meal, String mealName) {
        if (meal == null) {
            return Collections.emptyList();
        }

        switch (mealName) {
            case "Breakfast":
                List<String> breakfast = meal.getBreakfast();
                return (breakfast != null) ? breakfast : Collections.emptyList();
            case "Desert":
                List<String> desert = meal.getDesert();
                return (desert != null) ? desert : Collections.emptyList();
            case "Lunch":
                List<String> lunch = meal.getLunch();
                return (lunch != null) ? lunch : Collections.emptyList();
            case "Snack":
                List<String> snack = meal.getSnack();
                return (snack != null) ? snack : Collections.emptyList();
            case "Dinner":
                List<String> dinner = meal.getDinner();
                return (dinner != null) ? dinner : Collections.emptyList();
            default:
                return Collections.emptyList();
        }
    }

    private String getValidCellValue(String value) {
        return (value != null) ? value : "-";
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(BaseColor.BLACK);
        Customer customer = new Customer();
        String fullname = customer.getFirstName() + customer.getLastName();
        Paragraph p = new Paragraph(" Diet Program for " + fullname, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.0f, 3.0f, 3.0f, 4.0f, 3.5f, 3.0f, 3.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();

    }
}
