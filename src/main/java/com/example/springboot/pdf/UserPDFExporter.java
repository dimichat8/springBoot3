package com.example.springboot.pdf;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.Meal;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
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

        for (String mealName : mealNames) {
            table.addCell(mealName);
            for (String dayOfWeek : daysOfWeek) {
                Meal meal = findMeal(dayOfWeek, mealName);
                if (meal != null) {
                    table.addCell(getValidCellValue(getMealValue(meal, mealName)));
                } else {
                    table.addCell("");
                }
            }
        }
    }

    private Meal findMeal(String dayOfWeek, String mealName) {
        for (Meal meal : mealList) {
            if (meal.getDayOfWeek().equalsIgnoreCase(dayOfWeek) && meal.getMealName().equalsIgnoreCase(mealName)) {
                return meal;
            }
        }
        return null;
    }

    private String getMealValue(Meal meal, String mealName) {
        switch (mealName) {
            case "Breakfast":
                return meal.getBreakfast().toString();
            case "Desert":
                return meal.getDesert().toString();
            case "Lunch":
                return meal.getLunch().toString();
            case "Snack":
                return meal.getSnack().toString();
            case "Dinner":
                return meal.getDinner().toString();
            default:
                return "";
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
        /*font.setColor(Color.BLUE);*/

        Paragraph p = new Paragraph("Diet Program", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.0f, 3.0f, 3.0f, 3.5f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
