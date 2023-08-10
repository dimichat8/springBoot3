var previousButtonsContainer = null;

function toggleButtons(customerId) {
    var buttonsContainer = document.getElementById('buttons-' + customerId);

    if (previousButtonsContainer !== null && previousButtonsContainer !== buttonsContainer) {
        previousButtonsContainer.classList.add('hidden');
    }

    buttonsContainer.classList.toggle('hidden');
    previousButtonsContainer = buttonsContainer;
}


$(document).ready(function () {
    // Initialize Select2 for the food options select dropdown
    $('#foodIds').select2({
        placeholder: 'Select Food options',
        dropdownParent: $('#foodIds').parent()
    });

    // Function to handle the change event of the food options select dropdown
    function handleFoodOptionsChange() {
        var selectedMeals = $('#foodIds').find(':selected').map(function () {
            return $(this).text(); // Get the text (food.name) of the selected options
        }).get();

        // Set the selected meals as the value of each meal input field
        $('#breakfast').val(selectedMeals.join(', '));
        $('#desert').val(selectedMeals.join(', '));
        $('#lunch').val(selectedMeals.join(', '));
        $('#snack').val(selectedMeals.join(', '));
        $('#dinner').val(selectedMeals.join(', '));

        // Update the input fields for each selected meal type
        updateInputFields(selectedMeals);
    }

    // Attach the handleFoodOptionsChange function to the change event of the food options select dropdown
    $('#foodIds').on('change', handleFoodOptionsChange);

    // Function to update the input fields for each selected meal type
    function updateInputFields(selectedMeals) {
        // Clear previous input fields
        $('#inputFieldsContainer').empty();

        // Add input fields for each selected meal type
        selectedMeals.forEach(function (mealType) {
            var inputField = '<div class="input-group mb-2 input-group">' +
                '<label class="input-group-text" for="' + mealType.toLowerCase() + '">' + mealType + ':</label>' +
                '<input type="text" class="form-control" id="' + mealType.toLowerCase() + '" name="' + mealType.toLowerCase() + '" placeholder="' + mealType + ' grams">' +
                '</div>';
            $('#inputFieldsContainer').append(inputField);
        });
    }

    // Fetch the selected meal options and set them as the initial input fields
    var selectedMeals = $('#foodIds').find(':selected').map(function () {
        return $(this).text(); // Get the text (food.name) of the selected options
    }).get();

    // Set the initial input fields
    updateInputFields(selectedMeals);
});