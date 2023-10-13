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

    $('#foodIds').select2({
        placeholder: 'Select Food options',
        dropdownParent: $('#foodIds').parent()
    });


    function handleFoodOptionsChange() {
        var selectedMeals = $('#foodIds').find(':selected').map(function () {
            return $(this).text();
        }).get();

        $('#breakfast').val(selectedMeals.join(', '));
        $('#desert').val(selectedMeals.join(', '));
        $('#lunch').val(selectedMeals.join(', '));
        $('#snack').val(selectedMeals.join(', '));
        $('#dinner').val(selectedMeals.join(', '));


        updateInputFields(selectedMeals);
    }


    $('#foodIds').on('change', handleFoodOptionsChange);


    function updateInputFields(selectedMeals) {

        $('#inputFieldsContainer').empty();


        selectedMeals.forEach(function (mealType) {
            var inputField = '<div class="input-group mb-2 input-group">' +
                '<label class="input-group-text" for="' + mealType.toLowerCase() + '">' + mealType + ':</label>' +
                '<input type="text" class="form-control" id="' + mealType.toLowerCase() + '" name="' + mealType.toLowerCase() + '" placeholder="' + mealType + ' grams">' +
                '</div>';
            $('#inputFieldsContainer').append(inputField);
        });
    }


    var selectedMeals = $('#foodIds').find(':selected').map(function () {
        return $(this).text();
    }).get();


    updateInputFields(selectedMeals);
});