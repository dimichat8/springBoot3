package com.example.springboot.Controller;

import com.example.springboot.DTO.MealDataDto;
import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Entity.MealPlan;
import com.example.springboot.Repository.CustomerRepository;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Repository.MealPlanRepository;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.CustomerService;
import com.example.springboot.Service.MealPlanService;
import com.example.springboot.Service.MealService;
import com.example.springboot.pdf.UserPDFExporter;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MealController {


    @Autowired
    MealRepository mealRepository;
    @Autowired
    MealService mealService;
    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private MealPlanService mealPlanService;
    @Autowired
    private MealPlanRepository mealPlanRepository;


    @GetMapping("/programForAllCustomers")
    public String layout(Model model) {
        List<MealDataDto> allMealData = mealService.getAllMealData();
        model.addAttribute("allMealData", allMealData);
        return "Customer/programForAllCustomers";
    }

    @GetMapping("/{customer_id}/programForCustomer")
    public String programForEachCustomer(@PathVariable("customer_id") Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        List<MealDataDto> allMealDataForEachCustomer = mealService.allMealDataForEachCustomer(customerId);
        model.addAttribute("allMealDataForEachCustomer", allMealDataForEachCustomer);
        return "Customer/programForCustomer";
    }

    @GetMapping("/{customer_id}/tableAccordingToDates")
    public String tableAccordingToDates(@PathVariable("customer_id") Long customerId,
                                        @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                        @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                        @ModelAttribute("dayOfWeek") String dayOfWeek, @ModelAttribute("mealName") String type,
                                        Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        String[] mealNames = {"Breakfast", "Desert", "Lunch", "Snack", "Dinner"};
        List<String> combinations = mealService.generateCombinations(mealNames);
        List<Meal> listOfMeals = customer.getMeals();
        model.addAttribute("meals", listOfMeals);
        model.addAttribute("mealCombinations", combinations);
        model.addAttribute("customer", customer);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "Meal/tableAccordingToDates";
    }


    @GetMapping("/{customer_id}/mealtable")
    public String listOfMeals(@PathVariable("customer_id") Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        String[] mealNames = {"Breakfast", "Desert", "Lunch", "Snack", "Dinner"};
        List<String> combinations = mealService.generateCombinations(mealNames);
        model.addAttribute("mealCombinations", combinations);
        Meal meal = new Meal();
        List<Meal> listOfMeals = customer.getMeals();
        model.addAttribute("meals", listOfMeals);
        model.addAttribute("meal", meal);
        model.addAttribute("customer", customer);
        return "Meal/table";
    }

    @GetMapping("/{customer_id}/addmeal")
    public String createMealForm(@PathVariable(value = "customer_id") Customer customerId,
                                 Model model) {
        //Crate model attribute to bind from data
        Meal meal = new Meal();
        List<Food> foods = foodRepository.findAll();
        meal.setCustomer(customerId);
        model.addAttribute("foods", foods);
        model.addAttribute("meal", meal);
        model.addAttribute("customer", customerId);
        return "/Meal/addmeal";
    }

    @PostMapping("/{customer_id}/saveMeal/foodIds")
    public String saveMeal(@PathVariable(value = "customer_id") Long customerId,
                           @RequestParam("foodIds") List<Long> foodIds,
                           @RequestParam("dayOfWeek") List<String> daysOfWeek,
                           @RequestParam("mealName") List<String> mealNames,
                           @RequestParam Map<String, Integer> mapFoodGrams,
                           @ModelAttribute("breakfast") String breakfast,
                           @ModelAttribute("desert") String desert,
                           @ModelAttribute("lunch") String lunch,
                           @ModelAttribute("snack") String snack,
                           @ModelAttribute("dinner") String dinner,
                           @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                           @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        List<Food> selectedFoods = foodRepository.findAllById(foodIds);
        List<Object[]> result = mealPlanRepository.findMealPlanByCustomerAndDateRange(customer, dateFrom, dateTo);

        MealPlan mealPlan = null;
        boolean foundMatch = false;
        for (Object[] row : result) {
            MealPlan tempMealPlan = (MealPlan) row[0];
            LocalDate mealPlanDateFrom = (LocalDate) row[1];
            LocalDate mealPlanDateTo = (LocalDate) row[2];
            Long mealPlanCustomerId = (Long) row[3];

            if (mealPlanDateFrom.equals(dateFrom) && mealPlanDateTo.equals(dateTo) && mealPlanCustomerId.equals(customerId)) {
                mealPlan = tempMealPlan; // Found matching meal plan
                foundMatch = true;
                break;
            }
        }

        if (!foundMatch) {
            // If a match is not found, create a new MealPlan
            mealPlan = new MealPlan();
            mealPlan.setCustomer(customer);
            mealPlan.setDateFrom(dateFrom);
            mealPlan.setDateTo(dateTo);
            mealPlan = mealPlanService.save(mealPlan);
        }

        for (String dayOfWeek : daysOfWeek) {
            Meal meal = new Meal();
            List<String> mealNameList = new ArrayList<>();
            List<Integer> gramsList = new ArrayList<>();

            for (String mealName: mealNames) {
                meal.setCustomer(customer);
                meal.setDayOfWeek(dayOfWeek);
                mealNameList.add(mealName);
                meal.setFoods(selectedFoods);
                meal.setDateFrom(dateFrom);
                meal.setDateTo(dateTo);

                List<String> mealItemGrams = new ArrayList<>();

                switch (mealName) {
                        case "Breakfast":
                            String[] itemsForBreakFast = breakfast.split(",");
                            for (String item : itemsForBreakFast) {
                                String lowercaseBreakfast = item.trim().toLowerCase();
                                String gramsAsStringForBreakfast = String.valueOf((mapFoodGrams.getOrDefault(lowercaseBreakfast, 0)));
                                int breakfastGrams = Integer.parseInt(gramsAsStringForBreakfast);
                                gramsList.add(breakfastGrams);
                                mealItemGrams.add(item + ": " + breakfastGrams + " gr");
                                meal.setBreakfast(mealItemGrams);
                            }
                            break;
                        case "Desert":
                            String[] itemsForDesert = desert.split(",");
                            for (String item : itemsForDesert) {
                                String lowercaseDesert = item.trim().toLowerCase();
                                String gramsAsStringForDesert = String.valueOf((mapFoodGrams.getOrDefault(lowercaseDesert, 0)));
                                int desertGrams = Integer.parseInt(gramsAsStringForDesert);
                                gramsList.add(desertGrams);
                                mealItemGrams.add(item + ": " + desertGrams + " gr");
                                meal.setDesert(mealItemGrams);
                            }
                            break;
                        case "Lunch":
                            String[] itemsForLunch = lunch.split(",");
                            for (String item : itemsForLunch) {
                                String lowercaseLunch = item.trim().toLowerCase();
                                String gramsAsStringForLunch = String.valueOf((mapFoodGrams.getOrDefault(lowercaseLunch, 0)));
                                int lunchGrams = Integer.parseInt(gramsAsStringForLunch);
                                gramsList.add(lunchGrams);
                                mealItemGrams.add(item + ": " + lunchGrams + " gr");
                                meal.setLunch(mealItemGrams);
                            }
                            break;
                        case "Snack":
                            String[] itemsForSnack = snack.split(",");
                            for (String item : itemsForSnack) {
                                String lowercaseSnack = item.trim().toLowerCase();
                                String gramsAsStringForSnack = String.valueOf((mapFoodGrams.getOrDefault(lowercaseSnack, 0)));
                                int snackGrams = Integer.parseInt(gramsAsStringForSnack);
                                gramsList.add(snackGrams);
                                mealItemGrams.add(item + ": " + snackGrams + " gr");
                                meal.setSnack(mealItemGrams);
                            }
                            break;
                        case "Dinner":

                            String[] itemsForDinner = dinner.split(",");
                            for (String item : itemsForDinner) {
                                String lowercaseDinner = item.trim().toLowerCase();
                                String gramsAsStringForDinner = String.valueOf((mapFoodGrams.getOrDefault(lowercaseDinner, 0)));
                                int dinnerGrams = Integer.parseInt(gramsAsStringForDinner);
                                gramsList.add(dinnerGrams);
                                mealItemGrams.add(item + ": " + dinnerGrams + " gr");
                                meal.setDinner(mealItemGrams);
                            }
                            break;
                }
            }

            meal.setMealName(String.join(", ", mealNameList));
            meal.setMealGrams(gramsList);
            meal.setMealPlan(mealPlan);
            meal = mealService.saveMeal(meal);
            mealPlan.getMeals().add(meal);
            customer.getMeal().add(meal);
        }

        return "redirect:/{customer_id}/tableAccordingToDates?dateFrom=" + dateFrom + "&dateTo=" + dateTo;
    }

    @GetMapping("/updateMealForm/{meal_id}")
    public String updateMealForm(@PathVariable(value = "meal_id")Long meal_id, Model model) {

        Meal meal = mealService.getMealById(meal_id);
        model.addAttribute("meal", meal);
        return "/Meal/updatemeal";
    }

    @PostMapping("/updateMeal/{meal_id}")
    public String updateMeal(@PathVariable(value = "meal_id") Long meal_id,
                                 @ModelAttribute("meal") Meal meal) {
        Meal existingMeal = mealService.getMealById(meal_id);
        existingMeal.setMealName(meal.getMealName());
        mealService.updateMeal(existingMeal);
        return "redirect:/mealtable";
    }

    @GetMapping("/deleteMeal/{meal_id}")
    public String deleteMeal(@PathVariable(value = "meal_id")Long meal_id){

        //Call delete meal method
        mealService.deleteMealById(meal_id);
        return "redirect:/mealtable";
    }

    @GetMapping("/{customer_id}/dietProgram/export/pdf")
    public void exportToPDF(@PathVariable(value = "customer_id") Long customerId,
                            @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                            @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                            HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Customer customer = customerService.getCustomerById(customerId);
        String fullΝame = customer.getFirstName() + " " + customer.getLastName();
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=diet_for_" + fullΝame + "_" + currentDateTime +  ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Meal> mealList = mealService.getMealByCustomerId(customerId);

        // Filter the mealList by the selected date range
        List<Meal> filteredMealList = filterMealsByDateRange(mealList, dateFrom, dateTo);

        // Create the PDF using the filtered meal data
        UserPDFExporter exporter = new UserPDFExporter(filteredMealList, customerService);
        exporter.export(response, customerId);
    }

    private List<Meal> filterMealsByDateRange(List<Meal> allMeals, LocalDate dateFrom, LocalDate dateTo) {
        return allMeals.stream()
                .filter(meal -> isWithinDateRange(meal, dateFrom, dateTo))
                .collect(Collectors.toList());
    }

    private boolean isWithinDateRange(Meal meal, LocalDate dateFrom, LocalDate dateTo) {
        LocalDate mealDateFrom = meal.getDateFrom();
        LocalDate mealDateTo = meal.getDateTo();
        // Change this to the actual method to get the meal date

        return !mealDateFrom.isBefore(dateFrom) && !mealDateTo.isAfter(dateTo);
    }

}




